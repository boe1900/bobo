package com.bobo.upms.admin.controller;

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
    @RequestMapping("/index")
    @Permission(action = Action.Skip)
    @Login(action = Action.Skip)
    public String index(Model model) {
        System.out.println("IndexController >>>>> index");
        return "redirect:/manage/index";
    }
}
