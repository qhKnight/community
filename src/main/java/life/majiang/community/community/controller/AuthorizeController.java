package life.majiang.community.community.controller;

import life.majiang.community.community.dto.AccessTokenDto;
import life.majiang.community.community.dto.GithubUser;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.User;
import life.majiang.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLOutput;
import java.util.UUID;

/**
 * Created by qhKnight on 2020/2/16
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.Client.id}")
    private String ClientId;
    @Value("${github.Client.secret}")
    private String ClientSecret;
    @Value("${github.Redirect.uri}")
    private String RedirectUri;

    @GetMapping("/callback")
    public String index(@RequestParam(name = "code") String code,
                        @RequestParam(name = "state") String state,
                        HttpServletResponse response) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);
        accessTokenDto.setClient_id(ClientId);
        accessTokenDto.setClient_secret(ClientSecret);
        accessTokenDto.setRedirect_uri(RedirectUri);
        String accessToken = githubProvider.getAccessTokenDto(accessTokenDto);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            String accountId = String.valueOf(githubUser.getId());
            user.setAccountId(accountId);
            user.setName(githubUser.getName());
            // user.setToken(UUID.randomUUID().toString());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
           /* User user1 = new User();
            user1 = userMapper.findByAccountId(accountId);*/
            /*  if (user1 == null) {*/
            userMapper.insert(user);
            /*}*/

            response.addCookie(new Cookie("token", token));
            /*requext.getSession().setAttribute("user", githubUser)*/
            ;
            return "redirect:/";
        } else {
            return "redirect:/";
        }


    }
}
