package com.thunv.controller;

import com.thunv.controller.output.login.RandomStuff;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestAPI {

    @GetMapping("/random")
    public RandomStuff randomStuff(){
        return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này <GET>");
    }

    @PostMapping("/random")
    public RandomStuff randomStuffPost(){
        return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này <POST>");
    }

    @PutMapping("/random")
    public RandomStuff randomStuffPut(){
        return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này <PUT>");
    }
    @DeleteMapping("/random")
    public RandomStuff randomStuffDelete(){
        return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này <DELETE>");
    }
}
