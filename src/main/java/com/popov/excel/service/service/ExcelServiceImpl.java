package com.popov.excel.service.service;

import com.popov.excel.service.properties.YAMLProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExcelServiceImpl {
    private final YAMLProperties properties;

    private final String tableToParse = "table.waffle.no-grid tr";
    private final String tableRowElement = "tr";
    private final String cellOfRowElement = "tr";
    private int numberOfCell = 0;
    private int numberOfRow = 0;

    @SneakyThrows
    public void getRows(Document doc, Sheet sheet) {
        for (Element table : doc.select(tableToParse)) {
            for (Element row : table.select(tableRowElement)) {
                Elements tds = row.select(cellOfRowElement);
                if (tds.size() > 6) {

                    System.out.println(tds.get(0).text() + ":" + tds.get(1).text() + ":" + tds.get(2).text());
                    tds.forEach(x -> setupSheets(x.text(), sheet));


                }
                numberOfCell = 0;
                numberOfRow++;
            }
        }
        numberOfRow = 0;
    }

    private void setupSheets(String value, Sheet sheet) {
        Row row = sheet.getRow(numberOfRow);
        Cell cell = row.createCell(numberOfCell);
        cell.setCellValue(value);
        numberOfCell++;
        log.info("\nROW IS " + numberOfRow);
        log.info("\nROW IS " + numberOfCell);
    }

    private void setupRows(Sheet sheet) {
        for (int i = 0; i < 100; i++) {
            sheet.createRow(i);
        }

    }

    @SneakyThrows
    public void init() {
        Document challenger = Jsoup.connect(properties.getSpreadSheet().getUrlOfCHALLENGER()).get();
        createWorkBook(challenger.select("span.name").text() + ".xlsx", challenger);

        Document nba = Jsoup.connect(properties.getSpreadSheet().getUrlOfNBA()).get();
        createWorkBook(nba.select("span.name").text() + ".xlsx", nba);

        Document siap = Jsoup.connect(properties.getSpreadSheet().getUrlOfSIAP()).get();
        createWorkBook(siap.select("span.name").text() + ".xlsx", siap);

        Document spe = Jsoup.connect(properties.getSpreadSheet().getUrlOfSPE()).get();
        createWorkBook(spe.select("span.name").text() + ".xlsx", spe);

    }


    @SneakyThrows
    private void createWorkBook(String filename, Document document) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Tables");
        sheet.setColumnWidth(0, 500);
        sheet.setColumnWidth(1, 500);

        setupRows(sheet);

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + filename;

        FileOutputStream outputStream = new FileOutputStream(fileLocation);

        getRows(document, sheet);
        workbook.write(outputStream);
        workbook.close();
    }
}
