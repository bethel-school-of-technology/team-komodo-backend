//Hans added this from Front-End Integration Lesson 8

package com.komodo.auth;

import com.komodo.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private MySQLUserDetailsService userService;

  @PostMapping("/register")
  public void register(@RequestBody User newUser) {
    userService.Save(newUser);
  }
}
