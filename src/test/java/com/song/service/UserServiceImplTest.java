package com.song.service;

import com.song.domain.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Song on 2017/7/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/*.xml"})
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    public void testFindUserByUserName() {
        SysUser admin = userService.findByUsername("admin");
        System.out.println("admin:" + admin.toString());
        SysUser admin_1 = userService.findByUsername("admin");
        System.out.println("admin:" + admin_1.toString());
    }
}
