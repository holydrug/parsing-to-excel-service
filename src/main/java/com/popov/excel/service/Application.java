package com.popov.excel.service;

import com.popov.excel.service.properties.YAMLProperties;
import com.popov.excel.service.service.ExcelServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {
	private final YAMLProperties properties;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


}
