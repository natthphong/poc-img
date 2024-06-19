package com.bay.poc2;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ocr")
public class OcrController {



    private final  ITesseract tesseract;

    public OcrController(ITesseract tesseract) {
        this.tesseract = tesseract;
    }


    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file) {
        Map<String, String> response = new HashMap<>();
        try {

            BufferedImage image = ImageIO.read(file.getInputStream());

            String receiptNo = tesseract.doOCR(image, new Rectangle(779, 153, 354, 30));
            String total = tesseract.doOCR(image, new Rectangle(947, 433, 112, 30));
            receiptNo = cleanText(receiptNo);
            total = cleanText(total);

            response.put("receiptNo", receiptNo);
            response.put("total", total);



            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException | TesseractException e) {
            e.printStackTrace();
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private String cleanText(String text) {
        if (text == null) {
            return null;
        }
        return text.replace("\n", "").replace("\\", "").replace("—", "").trim();
    }
}
