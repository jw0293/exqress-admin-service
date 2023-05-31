package com.exqress.adminservice.kafkadto;

import lombok.Data;

@Data
public class KafkaQRinfoToDelivery {
    private String qrId;
    private String invoiceNo;
    private String productName;
    private String curState;
    private String address;
    private String company;
    private String receiverName;
    private String receiverPhoneNumber;
}
