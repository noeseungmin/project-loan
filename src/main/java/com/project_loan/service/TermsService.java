package com.project_loan.service;

import com.project_loan.dto.TermsDto;
import com.project_loan.dto.TermsDto.Request;
import com.project_loan.dto.TermsDto.Response;

import java.util.List;

public interface TermsService {

    Response create(Request request);

    List<Response> getAll();
}
