package mine.learn.springboot.projectspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mine.learn.springboot.projectspring.datatransobject.AccessTokenDTO;
import mine.learn.springboot.projectspring.provider.GithubProvider;

/**
 * AuthController
 */
@Controller
public class AuthController {
    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callBack(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id("94e59a71a91ebd1e8b8d");
        accessTokenDTO.setClinet_secret("45db3fe7143db6538ce7ce8cbc7948a809485a0e");
        GithubProvider.getAccessProvider(accessTokenDTO);
        return "index";
    }
}