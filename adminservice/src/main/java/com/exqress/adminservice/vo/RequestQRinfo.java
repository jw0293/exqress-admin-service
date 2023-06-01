package com.exqress.adminservice.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestQRinfo {

    @NotBlank(message = "상품명을 입력해주세요")
    private String productName;
    @NotBlank(message = "운송장 번호를 입력해주세요")
    private String invoiceNo;
    @NotBlank(message = "회사명을 입력해주세요")
    private String company;
    @NotBlank(message = "주소를 입력해주세요")
    private String address;
}
