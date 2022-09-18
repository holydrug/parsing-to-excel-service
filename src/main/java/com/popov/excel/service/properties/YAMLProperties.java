package com.popov.excel.service.properties;

import com.popov.excel.service.properties.etc.mailstructure.MailStructure;
import com.popov.excel.service.properties.etc.spreadsheet.SpreadSheet;
import com.popov.excel.service.properties.etc.spring.Spring;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class YAMLProperties {
    private SpreadSheet spreadSheet;
    private MailStructure mailStructure;
    private Spring spring;
}
