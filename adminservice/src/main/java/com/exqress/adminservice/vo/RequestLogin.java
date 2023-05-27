package com.exqress.adminservice.vo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RequestLogin {
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
}
