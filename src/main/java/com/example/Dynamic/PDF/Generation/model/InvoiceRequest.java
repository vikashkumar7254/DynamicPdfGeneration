package com.example.Dynamic.PDF.Generation.model;

import lombok.*;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InvoiceRequest {
    private String seller;
    private String sellerGstin;
    private String sellerAddress;
    private String buyer;
    private String buyerGstin;
    private String buyerAddress;
    private List<Item> items;
    public String getSellerGstin() {
        return sellerGstin;
    }

    public String getBuyerGstin() {
        return buyerGstin;
    }

}
