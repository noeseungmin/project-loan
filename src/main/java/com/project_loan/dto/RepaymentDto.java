package com.project_loan.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RepaymentDto implements Serializable {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class Request {

        private BigDecimal repaymentAmount;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class Response{

        private Long applicationId;
        private BigDecimal repaymentAmount;
        private BigDecimal balance;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class ListResponse{

        private Long repaymentId;
        private BigDecimal repaymentAmount;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class UpdateResponse{

        private Long applicationId;
        private BigDecimal beforeRepaymentAmount;
        private BigDecimal afterRepaymentAmount;
        private BigDecimal balance;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
