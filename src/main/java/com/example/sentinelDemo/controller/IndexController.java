package com.example.sentinelDemo.controller;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Slf4j
@RestController
public class IndexController {

    @GetMapping("/index.html")
    @ResponseBody
    public String index() {
        return "hello world";
    }

    @PostMapping("/addRule")
    @ResponseBody
    public String addSentinelDegradeRule() {
        DegradeRule degradeRule = new DegradeRule();
        degradeRule.setResource("index.html");
        degradeRule.setGrade(0);
        degradeRule.setCount(1000);
        degradeRule.setTimeWindow(10);
        degradeRule.setStatIntervalMs(60*1000);
        DegradeRuleManager.loadRules(Arrays.asList(degradeRule));
        return "addRule";
    }



}
