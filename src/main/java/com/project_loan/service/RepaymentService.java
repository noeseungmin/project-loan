package com.project_loan.service;

import com.project_loan.dto.RepaymentDto;
import com.project_loan.dto.RepaymentDto.ListResponse;
import com.project_loan.dto.RepaymentDto.Request;
import com.project_loan.dto.RepaymentDto.Response;
import com.project_loan.dto.RepaymentDto.UpdateResponse;

import java.util.List;

public interface RepaymentService {

    Response create(Long applicationId, Request request);

    List<ListResponse> get(Long applicationId);

    UpdateResponse update(Long repaymentId, Request request);

    void delete(Long repaymentId);
}
