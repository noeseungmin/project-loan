package com.project_loan.controller;

import com.project_loan.dto.EntryDto;
import com.project_loan.dto.RepaymentDto;
import com.project_loan.dto.RepaymentDto.ListResponse;
import com.project_loan.dto.RepaymentDto.Request;
import com.project_loan.dto.RepaymentDto.Response;
import com.project_loan.dto.ResponseDto;
import com.project_loan.service.EntryService;
import com.project_loan.service.RepaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/internal/applications")
public class InternalController extends AbstractController{

    private final EntryService entryService;

    private final RepaymentService repaymentService;

   @PostMapping("/{applicationId}/entries")
    public ResponseDto<EntryDto.Response> create(@PathVariable Long applicationId, @RequestBody EntryDto.Request request){
        return ok(entryService.create(applicationId, request));
    }

    @GetMapping("/{applicationId}/entries")
    public ResponseDto<EntryDto.Response> get(@PathVariable Long applicationId){
       return ok(entryService.get(applicationId));
    }

    @PutMapping("/entries/{entryId}")
    public ResponseDto<EntryDto.UpdateResponse> update(@PathVariable Long entryId, @RequestBody EntryDto.Request request){
       return ok(entryService.update(entryId, request));
    }

    @DeleteMapping("/entries/{entryId}")
    public ResponseDto<Void> delete(@PathVariable Long entryId){
       entryService.delete(entryId);
       return ok();
    }

    @PostMapping("/{applicationId}/repayments")
    public ResponseDto<Response> create(@PathVariable Long applicationId, @RequestBody Request request){
    return ok(repaymentService.create(applicationId, request));
    }

    @GetMapping("/{applicationId}/repayments")
    public ResponseDto<List<ListResponse>> getRepayments(@PathVariable Long applicationId){
       return ok(repaymentService.get(applicationId));
    }

    @PutMapping("/repayments/{repaymentId}")
    public ResponseDto<RepaymentDto.UpdateResponse> update(@PathVariable Long repaymentId,
                                                           @RequestBody Request request){
       return ok(repaymentService.update(repaymentId, request));
    }

    @DeleteMapping("/repayments/{repaymentId}")
    public ResponseDto<Void> deleteRepayment(@PathVariable Long repaymentId){
       repaymentService.delete(repaymentId);
       return ok();
    }
}
