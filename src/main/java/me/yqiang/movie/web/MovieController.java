package me.yqiang.movie.web;


import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.utils.ObjectUtils;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import io.jsonwebtoken.Claims;
import me.yqiang.movie.domain.Movie;
import me.yqiang.movie.service.MovieService;
import me.yqiang.movie.service.impl.RecommendServiceImpl;
import me.yqiang.movie.utils.ResultVoUtil;
import me.yqiang.movie.vo.ResultVo;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
public class MovieController {
    private MovieService movieService;
    @Value("${qcloud.cos.secretId}")
    private String secretId;
    @Value("${qcloud.cos.secretKey}")
    private String secretKey;
    @Value("${qcloud.cos.region}")
    private String region;
    @Value("${qcloud.cos.bucketName}")
    private String bucketName;
    @Value("${cos.server}")
    private Boolean cosServer;
    @Value("${tx.server}")
    private Boolean txServer;
    @Value("${cloudinary.server}")
    private Boolean cloudServer;
    @Value("${img.address}")
    private String imgAddress;
    @Value("${cos.address}")
    private String cosAddress;

    @Value("${recommend.size}")
    private Integer recommendSize;
    private RecommendServiceImpl recommendService;
    private FastFileStorageClient fastFileStorageClient;
    @Autowired
    public MovieController(MovieService movieService, RecommendServiceImpl recommendService,FastFileStorageClient fastFileStorageClient) {
        this.movieService = movieService;
        this.recommendService = recommendService;
        this.fastFileStorageClient = fastFileStorageClient;
    }

    @RequestMapping("/api/jwt/movie/upload.do")
    public ResultVo upload(MultipartFile file,String movieName) throws IOException {
        if(file.isEmpty()){
            return ResultVoUtil.error(400,"error");
        }
        String imgName = "";
        if(txServer){
            String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);
            System.out.println(storePath.getFullPath());
            imgName = imgAddress+storePath.getFullPath();
        }


        if(cloudServer){

            String suffix ="."+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
            File tempFile = File.createTempFile("tem",suffix);
            file.transferTo(tempFile);
            Cloudinary cloudinary = Singleton.getCloudinary();
            Map uploadResult = cloudinary.uploader().upload(tempFile, ObjectUtils.emptyMap());
            imgName = (String) uploadResult.get("secure_url");

        }
        if(cosServer){
            long millis = System.currentTimeMillis();
            //加上三位随机数
            Random random = new Random();
            int end3 = random.nextInt(999);
            //如果不足三位前面补0
            String str = millis + String.format("%03d", end3);
            String suffix ="."+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
            imgName = cosAddress+str+suffix;
            // 1 初始化用户身份信息(secretId, secretKey)
            COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
            // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
            ClientConfig clientConfig = new ClientConfig(new Region(region));
            // 3 生成cos客户端
            COSClient cosclient = new COSClient(cred, clientConfig);

            String key = "/"+str+suffix;
            File tempFile = File.createTempFile(str, suffix);
            file.transferTo(tempFile);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, tempFile);
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
            cosclient.shutdown();
        }
        Movie movie = new Movie();
        movie.setMovieName(movieName);
        movie.setImgName(imgName);
        movieService.save(movie);
        return ResultVoUtil.success(imgName);

    }

    @RequestMapping("/api/movie/list.do")
    public ResultVo list(){
        List<Movie> movieList = movieService.findAll();
        return ResultVoUtil.success(movieList);
    }
    @RequestMapping("/api/movie/listPage.do")
    public ResultVo list(Integer page,Integer size){
        Page<Movie> movieList = movieService.findAll(page,size);
        return ResultVoUtil.success(movieList);
    }
    @RequestMapping("/api/movie/info.do")
    public ResultVo list(Long id){
        Movie movie  = movieService.getOne(id);
        return ResultVoUtil.success(movie);
    }
    @RequestMapping("/api/jwt/movie/recommend.do")
    public ResultVo recommend(HttpServletRequest request) throws TasteException {
        Claims claims = (Claims) request.getAttribute("claims");
        List<RecommendedItem> recommendedItems = recommendService.recommenderItem(Long.parseLong(String.valueOf(claims.get("userid"))), recommendSize);
        return ResultVoUtil.success(recommendedItems);
    }
}
