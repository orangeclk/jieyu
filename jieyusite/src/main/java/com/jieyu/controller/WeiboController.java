package com.jieyu.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import weibo4j.Account;
import weibo4j.Oauth;
import weibo4j.Users;
import weibo4j.http.AccessToken;
import weibo4j.model.User;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;

/**
 * Created by orangeclk on 8/10/16.
 */
@Controller
public class WeiboController {

    @RequestMapping("/weibo")
    public String weibo() throws WeiboException {
        Oauth oauth = new Oauth();
        String url = oauth.authorize("code");
        return "redirect:" + url;
    }

    @RequestMapping("/login")
    public String login(@RequestParam String code, Model model) throws WeiboException, JSONException {
        Oauth oauth = new Oauth();
        AccessToken accessToken = oauth.getAccessTokenByCode(code);
        Account account = new Account(accessToken.getAccessToken());
        String uid = account.getUid().get("uid").toString();
        Users users = new Users(accessToken.getAccessToken());
        User user = users.showUserById(uid);
        model.addAttribute(user);
        return "test";
    }
}
