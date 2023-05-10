package com.project_loan.service;

import com.project_loan.dto.BalanceDto;
import com.project_loan.dto.BalanceDto.RepaymentRequest;
import com.project_loan.dto.BalanceDto.Request;
import com.project_loan.dto.BalanceDto.Response;
import com.project_loan.dto.BalanceDto.UpdateRequest;

public interface BalanceService {

    Response create(Long applicationId, Request request);

    Response update(Long applicationId, UpdateRequest request);

    Response repaymentUpdate(Long applicationId, RepaymentRequest request);
}
