package life.majiang.community.community.controller;

import life.majiang.community.community.dto.AccessTokenDto;
import life.majiang.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by qhKnight on 2020/2/16
 */
@Controller
public class AuthorizeController {
    @Autowired
    GithubProvider githubProvider;
    @GetMapping("/callback")
    public  String  index(@RequestParam(name ="code") String code,
                          @RequestParam(name = "state") String state) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);
        accessTokenDto.setClient_id("8085b33655ffaba0c283");
        accessTokenDto.setClient_secret("a23ebd33bb80676b1ce1134d1d21c5ec5428b8a4");
        accessTokenDto.setRedirect_uri("http://localhost:8080/callback");
        githubProvider.getAccessTokenDto(accessTokenDto);

        return  "index";
    }
}
