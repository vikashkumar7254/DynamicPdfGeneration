package com.example.Dynamic.PDF.Generation.service;

import com.example.Dynamic.PDF.Generation.model.InvoiceRequest;
import com.example.Dynamic.PDF.Generation.util.PdfGeneratorUtil;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class PdfService {

    private final TemplateEngine templateEngine;
    private final String pdfStorageDir = "pdf_storage"; // Configurable directory for storing PDFs

    public PdfService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public byte[] generatePdf(InvoiceRequest invoiceRequest) throws IOException {
        // Check if PDF already exists
        byte[] storedPdf = getStoredPdf(invoiceRequest);
        if (storedPdf != null) {
            return storedPdf;
        }

        Context context = new Context();
        context.setVariable("invoice", invoiceRequest);
        String htmlContent = templateEngine.process("invoice_template", context);

        // Log the generated HTML for debugging
        System.out.println("Generated HTML: " + htmlContent); // Log the generated HTML

        // Use iText to convert HTML to PDF
        byte[] pdfBytes = PdfGeneratorUtil.convertHtmlToPdf(htmlContent);
        storePdf(invoiceRequest, pdfBytes);
        return pdfBytes;
    }

    public void storePdf(InvoiceRequest invoiceRequest, byte[] pdfBytes) throws IOException {
        String filename = generateFileName(invoiceRequest);
        File directory = new File(pdfStorageDir);

        // Create the directory if it doesn't exist
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new IOException("Failed to create directory: " + pdfStorageDir);
            }
        }

        // Use try-with-resources to ensure FileOutputStream is closed properly
        try (FileOutputStream fos = new FileOutputStream(new File(directory, filename))) {
            fos.write(pdfBytes);
        } catch (IOException e) {
            throw new IOException("Error writing PDF to file: " + filename, e);
        }
    }

    public byte[] getStoredPdf(InvoiceRequest invoiceRequest) throws IOException {
        String filename = generateFileName(invoiceRequest);
        if (Files.exists(Paths.get(pdfStorageDir, filename))) {
            return Files.readAllBytes(Paths.get(pdfStorageDir, filename));
        }
        return null;
    }

    private String generateFileName(InvoiceRequest invoiceRequest) {
        // Retrieve the GSTIN values
        String sellerGstin = invoiceRequest.getSellerGstin();
        String buyerGstin = invoiceRequest.getBuyerGstin();

        // Validate GSTINs to ensure they are not null
        if (sellerGstin == null || sellerGstin.isEmpty()) {
            throw new IllegalArgumentException("Seller GSTIN must not be null or empty.");
        }

        if (buyerGstin == null || buyerGstin.isEmpty()) {
            throw new IllegalArgumentException("Buyer GSTIN must not be null or empty.");
        }

        // Return the formatted filename
        return sellerGstin + "-" + buyerGstin + ".pdf";
    }
}
