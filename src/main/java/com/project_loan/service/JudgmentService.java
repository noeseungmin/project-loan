package com.project_loan.service;

import com.project_loan.dto.ApplicationDto;
import com.project_loan.dto.ApplicationDto.GrantAmount;
import com.project_loan.dto.JudgmentDto.Request;
import com.project_loan.dto.JudgmentDto.Response;

public interface JudgmentService {

    Response create(Request request);

    Response get(Long judgmentId);

    Response getJudgmentOfApplication(Long applicationId);

    Response update(Long judgmentId, Request request);

    void delete(Long judgmentId);

    GrantAmount grant(Long judgmentId);
}
