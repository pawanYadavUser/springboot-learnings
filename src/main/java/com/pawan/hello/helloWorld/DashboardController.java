package com.pawan.hello.helloWorld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DashboardController {

    @GetMapping("/dashboard")
    public List<Map<String, Object>> getDashboardData() {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> obj1 = new HashMap<>();
        obj1.put("id", 1);
        obj1.put("name", "Item 1");

        Map<String, Object> obj2 = new HashMap<>();
        obj2.put("id", 2);
        obj2.put("name", "Item 2");

        list.add(obj1);
        list.add(obj2);

        return list;
    }
}
