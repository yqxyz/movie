package me.yqiang.movie.web;

import me.yqiang.movie.domain.Audience;
import me.yqiang.movie.domain.User;
import me.yqiang.movie.service.UserService;
import me.yqiang.movie.utils.JwtHelper;
import me.yqiang.movie.utils.ResultVoUtil;
import me.yqiang.movie.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping
@RestController
public class UserController {
    private UserService userService;
    private Audience audience;
    @Autowired
    public UserController(UserService userService, Audience audience) {
        this.userService = userService;
        this.audience = audience;
    }

    @RequestMapping("/api/user/sign.do")
    @ResponseBody
    public ResultVo userAdd(User user){
        System.out.println(audience.toString());
        User user1 = userService.save(user);
        if(null!=user1 && user.getPassword().equals(user1.getPassword())){
            user1.setPassword(null);

            String jwtToken = JwtHelper.createJWT(user1.getUserName(),
                    String.valueOf(user1.getId()),
//                query_user.getRole().toString(),
                    "",
                    audience.getClientId(),
                    audience.getName(),
                    audience.getExpiresSecond()*1000,
                    audience.getBase64Secret());

            String resultStr = "bearer;" + jwtToken;
            return ResultVoUtil.success(resultStr);
        }
        return null;
    }

    @RequestMapping("/api/user/validate.do")
    @ResponseBody
    public boolean userValidate(String userName){
        User user = userService.findByUserName(userName);
        return user==null;
    }


    @PostMapping("/api/user/login.do")
    public ResultVo login(User user) {

        User queryUser = userService.findByUserName(user.getUserName());
        if (queryUser == null) {
            return ResultVoUtil.error(400, "用户名错误");
        }
        //验证密码
        if(!user.getPassword().equals(queryUser.getPassword())) {
            return ResultVoUtil.error(400, "密码错误");
        }
        String jwtToken = JwtHelper.createJWT(queryUser.getUserName(),
                String.valueOf(queryUser.getId()),
//                query_user.getRole().toString(),
                "",
                audience.getClientId(),
                audience.getName(),
                audience.getExpiresSecond()*1000,
                audience.getBase64Secret());

        String resultStr = "bearer;" + jwtToken;
        return ResultVoUtil.success(resultStr);
    }
}
