package com.popov.excel.service.controller;

import com.popov.excel.service.service.ExcelServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final ExcelServiceImpl excelService;

    @GetMapping
    public void init() {
        excelService.init();
    }
}
