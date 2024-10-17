package com.example.Dynamic.PDF.Generation.controller;

import com.example.Dynamic.PDF.Generation.model.InvoiceRequest;
import com.example.Dynamic.PDF.Generation.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @PostMapping(value = "/generate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> generatePdf(@RequestBody InvoiceRequest invoiceRequest) throws IOException {
        byte[] pdf = pdfService.generatePdf(invoiceRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "invoice.pdf");
        return ResponseEntity.ok().headers(headers).body(pdf);
    }

    @PostMapping(value = "/redownload", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> redownloadPdf(@RequestBody InvoiceRequest invoiceRequest) throws IOException {
        byte[] pdf = pdfService.getStoredPdf(invoiceRequest);
        if (pdf != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "invoice.pdf");
            return ResponseEntity.ok().headers(headers).body(pdf);
        }
        return ResponseEntity.notFound().build();
    }
}
