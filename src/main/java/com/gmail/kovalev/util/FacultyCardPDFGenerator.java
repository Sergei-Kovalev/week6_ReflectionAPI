package com.gmail.kovalev.util;

import com.gmail.kovalev.dto.FacultyInfoDTO;
import com.gmail.kovalev.exception.PDFTemplateNotFoundException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

public class FacultyCardPDFGenerator {
    private final static String FACULTY_CARD_TEMPLATE = """
            ---------------------------------------------------------------------
                               Карточка факультатива
                               
             id:                        | %s
             Название факультатива:     | %s
             Преподаватель:             | %s
             Эл. почта для связи:       | %s
             Количество свободных мест: | %s
             Стоимость 1-го занятия:    | %s
             ---------------------------------------------------------------------
            """;

    private static String generateFacultyCard(FacultyInfoDTO facultyInfoDTO) {
        return String.format(FACULTY_CARD_TEMPLATE,
                facultyInfoDTO.id(), facultyInfoDTO.name(), facultyInfoDTO.teacher(), facultyInfoDTO.email(),
                facultyInfoDTO.freePlaces(), facultyInfoDTO.pricePerDay() + " BYN");
    }

    public void facultyCardOutputInFile(FacultyInfoDTO facultyInfoDTO) {
        LocalDateTime timeCreated = LocalDateTime.now();
        String fileName = (
                facultyInfoDTO.name() + " card actualize " + timeCreated.getYear() + " "
                        + timeCreated.getMonth() + " " + timeCreated.getDayOfMonth() + " " +
                        timeCreated.getHour() + "h " + timeCreated.getMinute() + "m").replace(" ", "_");
        File filePath = new File("faculty_cards");
        filePath.mkdir();

        deleteOldFileVersion(facultyInfoDTO, filePath);

        File file = new File(filePath + "\\" + fileName + ".pdf");

        PDDocument document;
        try {
            document = Loader.loadPDF(new File("src/main/resources/Clevertec_Template.pdf"));
        } catch (IOException e) {
            throw new PDFTemplateNotFoundException();
        }
        try {
            PDPage page = document.getPage(0);

            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);

            contentStream.beginText();

            PDFont font = PDType0Font.load(document, new File("C:\\Windows\\Fonts\\consola.ttf"));

            contentStream.setFont(font, 13);
            contentStream.newLineAtOffset(65, 580);
            contentStream.setLeading(14.5f);

            String[] strings = generateFacultyCard(facultyInfoDTO).split("\n");

            for (String string : strings) {
                String replace = string.replace("\r", "");
                contentStream.showText(replace);
                contentStream.newLine();
            }

            contentStream.endText();
            contentStream.close();

            document.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteOldFileVersion(FacultyInfoDTO facultyInfoDTO, File filePath) {
        File[] files = filePath.listFiles();
        if (files != null) {
            Arrays.stream(files).forEach(file -> {
                if (file.getName().contains(facultyInfoDTO.name())) {
                    file.delete();
                }
            });
        }
    }
}
