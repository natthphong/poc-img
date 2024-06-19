package com.bay.poc2;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class Poc2Application {

    public static void main(String[] args) throws IOException {
        String path  = Poc2Application.class.getClassLoader().getResources("file/lib").nextElement().getPath();
        System.setProperty("jna.library.path", path);
        SpringApplication.run(Poc2Application.class, args);
    }
    @Bean
    public ITesseract initITesseract() throws IOException {
        String pathTestData = getClass().getClassLoader().getResources("file/tessdata").nextElement().getPath();
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath(pathTestData);
        tesseract.setLanguage("eng");
        return tesseract;
    }

}
