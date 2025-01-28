package com.tnn.component.user;

import com.tnn.component.RESTPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RESTPath.USER)
public class UserController {
    @Autowired
    private UserService service;
}