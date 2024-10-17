package com.example.Dynamic.PDF.Generation.util;

import com.itextpdf.html2pdf.HtmlConverter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PdfGeneratorUtil {

    public static byte[] convertHtmlToPdf(String htmlContent) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            // Convert HTML content to PDF
            HtmlConverter.convertToPdf(htmlContent, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }
}
