package com.popov.excel.service.controller;


import com.popov.excel.service.properties.YAMLProperties;
import com.popov.excel.service.properties.etc.common.structure.CommonStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class FormController {

    private final YAMLProperties properties;
    private final TestController controller;

    @GetMapping("/")
    public String index() {
        return "redirect:/form";
    }

    @GetMapping("/form")
    public String formGet() {
        return "form";
    }

    @PostMapping("/form")
    public String formPost(CommonStructure commonStructure, Model model) {
        model.addAttribute("commonStructure", commonStructure);
        properties.getSpring().getMail().setPassword(commonStructure.getPassword());
        properties.getSpring().getMail().setUsername(commonStructure.getUsername());

        properties.getSpreadSheet().setUrlOfCHALLENGER(commonStructure.getUrlOfCHALLENGER());
        properties.getSpreadSheet().setUrlOfSIAP(commonStructure.getUrlOfSIAP());
        properties.getSpreadSheet().setUrlOfNBA(commonStructure.getUrlOfNBA());
        properties.getSpreadSheet().setUrlOfSPE(commonStructure.getUrlOfSPE());

        properties.getMailStructure().setReceiver(commonStructure.getReceiver());

        controller.init();
        return "form";
    }
}

