package com.project_loan.service;


import com.project_loan.domain.Application;
import com.project_loan.domain.Entry;
import com.project_loan.dto.BalanceDto;
import com.project_loan.dto.EntryDto;
import com.project_loan.dto.EntryDto.Request;
import com.project_loan.dto.EntryDto.Response;
import com.project_loan.dto.EntryDto.UpdateResponse;
import com.project_loan.exception.BaseException;
import com.project_loan.exception.ResultType;
import com.project_loan.repository.ApplicationRepository;
import com.project_loan.repository.EntryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EntryServiceImpl implements EntryService{

    private final BalanceService balanceService;

    private final EntryRepository entryRepository;

    private final ApplicationRepository applicationRepository;

    private final ModelMapper modelMapper;

    @Override
    public Response create(Long applicationId, Request request) {
        if(!isCreatedApplication(applicationId)){
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }
        Entry entry = modelMapper.map(request, Entry.class);
        entry.setApplicationId(applicationId);

        entryRepository.save(entry);

        balanceService.create(applicationId,
                BalanceDto.Request.builder()
                .entryAmount(request.getEntryAmount())
                .build());

        return modelMapper.map(entry, Response.class);
    }

    @Override
    public Response get(Long applicationId) {

        Optional<Entry> entry = entryRepository.findByApplicationId(applicationId);

        if(entry.isPresent()){
            return modelMapper.map(entry, Response.class);
        }else {
            return null;
        }
    }

    @Override
    public UpdateResponse update(Long entryId, Request request) {

        Entry entry = entryRepository.findById(entryId).orElseThrow(() -> {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        });

        BigDecimal beforeEntryAmount = entry.getEntryAmount();
        entry.setEntryAmount(request.getEntryAmount());

        entryRepository.save(entry);

        Long applicationId = entry.getApplicationId();
        balanceService.update(applicationId,
                BalanceDto.UpdateRequest.builder()
                        .beforeEntryAmount(beforeEntryAmount)
                        .afterEntryAmount(request.getEntryAmount())
                        .build());

        return UpdateResponse.builder().applicationId(applicationId)
                .entryId(entryId)
                .beforeEntryAmount(beforeEntryAmount)
                .afterEntryAmount(request.getEntryAmount())
                .build();
    }

    @Override
    public void delete(Long entryId) {
        Entry entry = entryRepository.findById(entryId).orElseThrow(() -> {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        });
        entry.setIsDeleted(true);

        entryRepository.save(entry);

        BigDecimal beforeEntryAmount = entry.getEntryAmount();

        Long applicationId = entry.getApplicationId();
        balanceService.update(applicationId,
                BalanceDto.UpdateRequest.builder()
                        .beforeEntryAmount(beforeEntryAmount)
                        .afterEntryAmount(BigDecimal.ZERO)
                        .build());
    }

    private boolean isCreatedApplication(Long applicationId){
        Optional<Application> existed = applicationRepository.findById(applicationId);
        if(existed.isEmpty()){
            return false;
        }
        return existed.get().getContractedAt() != null;
    }
}
