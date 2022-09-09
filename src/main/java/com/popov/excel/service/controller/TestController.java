package com.popov.excel.service.controller;

import com.popov.excel.service.service.excel.ExcelServiceImpl;
import com.popov.excel.service.service.mail.JavaMailSenderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@EnableScheduling
public class TestController {
    private final ExcelServiceImpl excelService;
    private final JavaMailSenderServiceImpl javaMailSenderService;

    @Scheduled(cron = "0 0 20 * * ?")
    @RequestMapping
    public void init() {
        excelService.init();
        javaMailSenderService.send();
    }
}
