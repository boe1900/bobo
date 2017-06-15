package com.bobo.cms.admin.controller;

import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.annotation.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by huabo on 2017/5/27.
 */
@Controller

public class IndexController {

    @Permission(action = Action.Skip)
    @Login(action = Action.Skip)
    @RequestMapping("/index")
    public String index(Model model) {
        return "redirect:/manage/index";
    }
}
