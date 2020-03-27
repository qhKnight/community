package life.majiang.community.community.controller;


import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * Created by qhKnight on 2020/2/16
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
               // System.out.println(token);
                User user = userMapper.findByToken(token);
                if(user != null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }

        }

        return "index";
    }
}
