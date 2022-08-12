package com.samueljavaspringboot.bankng_system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AccountDTO {


    @Size(max = 255)
    private String accountName;

    @Size(max = 225)
    private String accountNumber;

    @Size(max = 255)
    private String accountType;



}
