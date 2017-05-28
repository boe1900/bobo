package com.bobo.upms.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by huabo on 2017/5/27.
 */
@Controller

public class IndexController {
    @RequestMapping("/index")
    public String index(Model model) {
        System.out.println("IndexController >>>>> index");
        return "redirect:/manage/index";
    }
}
