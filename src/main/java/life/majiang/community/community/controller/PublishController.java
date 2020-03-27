package life.majiang.community.community.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by qhKnight on 2020/3/20
 */
@Controller
public class PublishController {
    @GetMapping("publish")
    public String  publish(){
        return "publish";
    }
}
