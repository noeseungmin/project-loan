package com.project_loan.controller;

import com.project_loan.dto.ApplicationDto;
import com.project_loan.dto.ApplicationDto.GrantAmount;
import com.project_loan.dto.JudgmentDto;
import com.project_loan.dto.JudgmentDto.Request;
import com.project_loan.dto.JudgmentDto.Response;
import com.project_loan.dto.ResponseDto;
import com.project_loan.service.JudgmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/judgments")
public class JudgmentController extends AbstractController{

    private final JudgmentService judgmentService;

    @PostMapping
    public ResponseDto<Response> create(@RequestBody Request request){
        return ok(judgmentService.create(request));
    }

    @GetMapping("/{judgmentId}")
    public ResponseDto<Response> get(@PathVariable Long judgmentId){
        return ok(judgmentService.get(judgmentId));
    }

    @GetMapping("/applications/{applicationId}")
    public ResponseDto<Response> getJudgmentOfApplication(@PathVariable Long applicationId){
        return ok(judgmentService.getJudgmentOfApplication(applicationId));
    }

    @PutMapping("/{judgmentId}")
    public ResponseDto<Response> update(@PathVariable Long judgmentId, @RequestBody Request request){
        return ok(judgmentService.update(judgmentId, request));
    }

    @DeleteMapping("/{judgmentId}")
    public ResponseDto<Void> delete(@PathVariable Long judgmentId){
        judgmentService.delete(judgmentId);
        return ok();
    }

    @PatchMapping("/{judgmentId}/grant")
    public ResponseDto<GrantAmount> grant(@PathVariable Long judgmentId){
        return ok(judgmentService.grant(judgmentId));
    }
}
