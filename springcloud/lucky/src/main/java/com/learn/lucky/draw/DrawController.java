package com.learn.lucky.draw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("san")
@RestController
public class DrawController {
    @Autowired
    ILuckyDrawService luckyDrawService;



    @PostMapping("save")
    public String save(@RequestBody List<String> date) {
        return luckyDrawService.saveDate(date);
    }

    @GetMapping("res")
    public String getLuckOne() {
        return luckyDrawService.getLuckyOne();
    }

    @GetMapping("all")
    public String addDate() {
        return luckyDrawService.allDate();
    }

    @GetMapping("clear")
    public String clear() {
        return luckyDrawService.clearDate();
    }
}
