package com.project_loan.service;

import com.project_loan.domain.Application;
import com.project_loan.domain.Entry;
import com.project_loan.domain.Repayment;
import com.project_loan.dto.BalanceDto;
import com.project_loan.dto.BalanceDto.RepaymentRequest;
import com.project_loan.dto.BalanceDto.RepaymentRequest.RepaymentType;
import com.project_loan.dto.RepaymentDto;
import com.project_loan.dto.RepaymentDto.Request;
import com.project_loan.dto.RepaymentDto.Response;
import com.project_loan.dto.RepaymentDto.UpdateResponse;
import com.project_loan.exception.BaseException;
import com.project_loan.exception.ResultType;
import com.project_loan.repository.ApplicationRepository;
import com.project_loan.repository.EntryRepository;
import com.project_loan.repository.RepaymentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepaymentServiceImpl implements RepaymentService{

    private final RepaymentRepository repaymentRepository;

    private final ApplicationRepository applicationRepository;

    private final EntryRepository entryRepository;

    private final BalanceService balanceService;

    private final ModelMapper modelMapper;

    @Override
    public Response create(Long applicationId, Request request) {

        if (!isRepayableApplication(applicationId)){
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }

        Repayment repayment = modelMapper.map(request, Repayment.class);
        repayment.setApplicationId(applicationId);

        repaymentRepository.save(repayment);

        BalanceDto.Response updatedBalance = balanceService.repaymentUpdate(applicationId,
                RepaymentRequest.builder()
                        .repaymentAmount(request.getRepaymentAmount())
                        .type(RepaymentType.REMOVE)
                        .build());

        Response response = modelMapper.map(repayment, Response.class);
        response.setBalance(updatedBalance.getBalance());
        return response;
    }

    @Override
    public List<RepaymentDto.ListResponse> get(Long applicationId) {
        List<Repayment> repayments = repaymentRepository.findAllByApplicationId(applicationId);

        return repayments.stream().map(r -> modelMapper.map(r, RepaymentDto.ListResponse.class)).collect(Collectors.toList());
    }

    @Override
    public UpdateResponse update(Long repaymentId, Request request) {
        Repayment repayment = repaymentRepository.findById(repaymentId).orElseThrow(() -> {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        });

        Long applicationId = repayment.getApplicationId();
        BigDecimal beforeRepaymentAmount = repayment.getRepaymentAmount();

        // 500 - 100 = 400
        // 400 + 100 = 500
        // 500 - 200 = 300

        balanceService.repaymentUpdate(applicationId, RepaymentRequest.builder()
                .repaymentAmount(beforeRepaymentAmount)
                .type(RepaymentType.ADD)
                .build());

        repayment.setRepaymentAmount(request.getRepaymentAmount());
        repaymentRepository.save(repayment);

        BalanceDto.Response updatedBalance = balanceService.repaymentUpdate(applicationId, RepaymentRequest.builder()
                .repaymentAmount(request.getRepaymentAmount())
                .type(RepaymentType.REMOVE)
                .build());

        return UpdateResponse.builder()
                .applicationId(applicationId)
                .beforeRepaymentAmount(beforeRepaymentAmount)
                .afterRepaymentAmount(request.getRepaymentAmount())
                .balance(updatedBalance.getBalance())
                .createdAt(repayment.getCreatedAt())
                .updatedAt(repayment.getUpdatedAt())
                .build();
    }

    @Override
    public void delete(Long repaymentId) {
        Repayment repayment = repaymentRepository.findById(repaymentId).orElseThrow(() -> {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        });

        Long applicationId = repayment.getApplicationId();
        BigDecimal removeRepaymentAccount = repayment.getRepaymentAmount();

        balanceService.repaymentUpdate(applicationId, RepaymentRequest.builder()
                .repaymentAmount(removeRepaymentAccount)
                .type(RepaymentType.ADD)
                .build());

        repayment.setIsDeleted(true);
        repaymentRepository.save(repayment);
    }

    private boolean isRepayableApplication(Long applicationId){
        Optional<Application> existApplication = applicationRepository.findById(applicationId);
        if (existApplication.isEmpty()){
            return false;
        }
        if (existApplication.get().getContractedAt() == null){
            return false;
        }
        Optional<Entry> existEntry = entryRepository.findByApplicationId(applicationId);
        return existEntry.isPresent();
    }
}
