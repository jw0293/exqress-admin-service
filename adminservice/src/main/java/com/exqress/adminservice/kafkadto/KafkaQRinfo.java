package com.exqress.adminservice.kafkadto;

import lombok.Data;

@Data
public class KafkaQRinfo {
    private String userId;
    private String qrId;
    private String invoiceNo;
    private String productName;
    private String curState;
    private String address;
}
