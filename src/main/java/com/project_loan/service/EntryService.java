package com.project_loan.service;

import com.project_loan.dto.EntryDto.Request;
import com.project_loan.dto.EntryDto.Response;
import com.project_loan.dto.EntryDto.UpdateResponse;

public interface EntryService {

    Response create(Long applicationId, Request request);

    Response get(Long applicationId);

    UpdateResponse update(Long entryId, Request request);

    void delete(Long entryId);
}
