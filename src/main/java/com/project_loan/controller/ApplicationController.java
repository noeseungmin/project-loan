package com.project_loan.controller;

import com.project_loan.dto.ApplicationDto;
import com.project_loan.dto.ApplicationDto.Request;
import com.project_loan.dto.ApplicationDto.Response;
import com.project_loan.dto.ResponseDto;
import com.project_loan.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/applications")
public class ApplicationController extends AbstractController{

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseDto<Response> create(@RequestBody Request request){
        return ok(applicationService.create(request));
    }

    @GetMapping("/{applicationId}")
    public ResponseDto<Response> get(@PathVariable Long applicationId){
        return ok(applicationService.get(applicationId));
    }

    @PutMapping("/{applicationId}")
    public ResponseDto<Response> update(@PathVariable Long applicationId, @RequestBody Request request){
        return ok(applicationService.update(applicationId, request));
    }

    @DeleteMapping("{applicationId}")
    public ResponseDto<Response> delete(@PathVariable Long applicationId){
        applicationService.delete(applicationId);
        return ok();
    }
}
