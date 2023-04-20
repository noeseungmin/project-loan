package com.project_loan.controller;

import com.project_loan.dto.ResponseDto;
import com.project_loan.dto.TermsDto.Request;
import com.project_loan.dto.TermsDto.Response;
import com.project_loan.service.TermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/terms")
public class TermsController extends AbstractController{

    private final TermsService termsService;

    @PostMapping
    public ResponseDto<Response> create(@RequestBody Request request){
        return ResponseDto.ok(termsService.create(request));
    }

    @GetMapping
    public ResponseDto<List<Response>> getAll(){
        return ok(termsService.getAll());
    }
}
