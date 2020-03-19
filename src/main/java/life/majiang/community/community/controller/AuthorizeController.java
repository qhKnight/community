package life.majiang.community.community.controller;

import life.majiang.community.community.dto.AccessTokenDto;
import life.majiang.community.community.dto.GithubUser;
import life.majiang.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLOutput;

/**
 * Created by qhKnight on 2020/2/16
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.Client.id}")
    private String ClientId;
    @Value("${github.Client.secret}")
    private String ClientSecret;
    @Value("${github.Redirect.uri}")
    private String RedirectUri;

    @GetMapping("/callback")
    public String index(@RequestParam(name = "code") String code,
                        @RequestParam(name = "state") String state) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);
        accessTokenDto.setClient_id(ClientId);
        accessTokenDto.setClient_secret(ClientSecret);
        accessTokenDto.setRedirect_uri(RedirectUri);
        String accessToken = githubProvider.getAccessTokenDto(accessTokenDto);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println(githubUser.getName());
        //System.out.println(ClientId);
        return "index";
    }
}
