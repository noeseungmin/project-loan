package com.project_loan.controller;

import com.project_loan.dto.CounselDto;
import com.project_loan.dto.CounselDto.Request;
import com.project_loan.dto.CounselDto.Response;
import com.project_loan.dto.ResponseDto;
import com.project_loan.service.CounselService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/counsels")
public class CounselController extends AbstractController{

    private final CounselService counselService;

    @PostMapping
    public ResponseDto<Response> create(@RequestBody Request request){
        return ok(counselService.create(request));
    }

    @GetMapping("/{counselId}")
    public ResponseDto<Response> get(@PathVariable Long counselId){
        return ok(counselService.get(counselId));
    }

    @PutMapping("{counselId}")
    public ResponseDto<Response> update(@PathVariable Long counselId, @RequestBody Request request){
        return ok(counselService.update(counselId, request));
    }

    @DeleteMapping("{counselId}")
    public ResponseDto<Response> delete(@PathVariable Long counselId){
        counselService.delete(counselId);
        return ok();
    }
}
