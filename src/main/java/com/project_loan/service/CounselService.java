package com.project_loan.service;


import com.project_loan.dto.CounselDto;
import com.project_loan.dto.CounselDto.Request;
import com.project_loan.dto.CounselDto.Response;

public interface CounselService {

    Response create(Request request);

    Response get(Long counselId);

    Response update(Long counselId, Request request);

    void delete(Long counselId);
}
