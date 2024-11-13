package com.tekup.staffmanagementapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class helloController {

    @GetMapping("admin")
    public String user() {
        return "Hello Admin";
    }

    @GetMapping("HR")
    public String hr() {
        return "Hello HR";
    }

    @GetMapping("employee")
    public String employee() {
        return "Hello Employee";
    }
}
