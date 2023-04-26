package com.project_loan.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

public class BalanceDto implements Serializable {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Request{
        private Long applicationId;
        private BigDecimal entryAmount;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class UpdateRequest{
        private Long applicationId;
        private BigDecimal beforeEntryAmount;
        private BigDecimal afterEntryAmount;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Response{
        private Long balanceId;
        private Long applicationId;
        private BigDecimal balance;
    }
}
