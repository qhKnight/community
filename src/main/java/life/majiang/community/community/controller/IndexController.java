package life.majiang.community.community.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by qhKnight on 2020/2/16
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public     String index() {
        return "index";
    }
}
