package com.popov.excel.service.service.mail;

import com.popov.excel.service.properties.YAMLProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
@Slf4j
public class JavaMailSenderServiceImpl implements JavaMailSenderService {

    @Lazy
    @Autowired
    private JavaMailSender mailSender;
    private final YAMLProperties properties;
    private final String fileExtension = "xlsx";

    private String title = "exported excels tables %s";

    @SneakyThrows
    @Override
    public void send() {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom(properties.getMailStructure().getSender());
        mimeMessageHelper.setText("");
        mimeMessageHelper.setTo(properties.getMailStructure().getReceiver());
        mimeMessageHelper.setSubject(String.format(title, LocalDate.now()));

        List<String> files = findFiles(Paths.get("."), fileExtension);

        files.forEach(x -> {
            FileSystemResource fileSystemResource = new FileSystemResource(new File(x));
            try {
                mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });


        mailSender.send(mimeMessage);

        log.info("mail sended?");
    }


    private static List<String> findFiles(Path path, String fileExtension)
            throws IOException {

        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path must be a directory!");
        }

        List<String> result;

        try (Stream<Path> walk = Files.walk(path)) {
            result = walk
                    .filter(p -> !Files.isDirectory(p))
                    .map(p -> p.toString())
                    .filter(f -> f.endsWith(fileExtension))
                    .collect(Collectors.toList());
        }

        return result;
    }


}
