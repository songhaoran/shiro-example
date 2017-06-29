package com.song.web.controller;

import com.song.domain.SysResource;
import com.song.domain.SysUser;
import com.song.service.ResourceService;
import com.song.service.UserService;
import com.song.web.annotation.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

/**
 * Created by Song on 2017/6/28.
 */
@Controller
public class IndexController extends BaseController{

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(@CurrentUser SysUser loginUser, Model model) throws Exception {
        Set<String> permissions = userService.findPermissions(loginUser.getUsername());
        List<SysResource> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus", menus);
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
}
