package net.blf2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by blf2 on 17-5-6.
 */
@Controller
public class AppController {
    @RequestMapping("/")
    public String toIndex(){
        return "index";
    }
}
