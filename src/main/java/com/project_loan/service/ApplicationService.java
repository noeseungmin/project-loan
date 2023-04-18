package com.project_loan.service;

import com.project_loan.dto.ApplicationDto.Request;
import com.project_loan.dto.ApplicationDto.Response;

public interface ApplicationService {

    Response create(Request request);

    Response get(Long applicationId);
    Response update(Long applicationId, Request request);

    void delete(Long applicationId);
}
