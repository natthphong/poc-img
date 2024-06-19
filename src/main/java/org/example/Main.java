package org.example;

import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        String imagePath = "file/img.png";
        System.setProperty("jna.library.path", "file/lib");

        String datapath = "file/tessdata";
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath(datapath);
        tesseract.setLanguage("eng");
//        Mat image = Imgcodecs.imread(imagePath);
//        Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2GRAY);
//        Imgproc.medianBlur(image, image, 3);


        // Tesseract Configuration
//        tesseract.setLanguage("tha");
//        tesseract.setPageSegMode(ITessAPI.TessPageSegMode.PSM_SINGLE_BLOCK);
//        tesseract.setOcrEngineMode(ITessAPI.TessOcrEngineMode.OEM_LSTM_ONLY);

//        tesseract.setPageSegMode(1);
//        tesseract.setOcrEngineMode(1);
        File imageFile = new File(imagePath) ;
        try {
//            BufferedImage img = ImageIO.read(imageFile);
            // Example: Segmenting the image into multiple rectangles (regions of interest)
            // Rectangle[] segments = {                 new Rectangle(100, 100, 200, 50),
            // x, y, width, height of the first segment
            // new Rectangle(150, 200, 150, 50)  // x, y, width, height of the second segment             };
            // for (Rectangle segment : segments) {
            // BufferedImage subImage = img.getSubimage(segment.x, segment.y, segment.width, segment.height);                 String result = tesseract.doOCR(subImage);                 System.out.println("OCR Result for segment " + segment + ": ");                 System.out.println(result);
            String text = tesseract.doOCR(imageFile);
            String  text2 = tesseract.doOCR(imageFile, new Rectangle(779, 153, 354, 30));
            String  total = tesseract.doOCR(imageFile, new Rectangle(947, 433, 112, 30));
//            System.out.println(text);
            System.out.println(text2);
            System.out.println(total);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }
}