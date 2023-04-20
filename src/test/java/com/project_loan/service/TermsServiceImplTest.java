package com.project_loan.service;

import com.project_loan.domain.Terms;
import com.project_loan.dto.TermsDto;
import com.project_loan.dto.TermsDto.Request;
import com.project_loan.dto.TermsDto.Response;
import com.project_loan.repository.TermsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TermsServiceImplTest {

    @InjectMocks
    TermsServiceImpl termsService;

    @Mock
    private TermsRepository termsRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void create(){

        Terms entity = Terms.builder()
                .name("대출 이용 약관")
                .termsDetailUrl("http://abc-storage.acc")
                .build();

        Request request = Request.builder()
                .name("대출 이용 약관")
                .termsDetailUrl("http://abc-storage.acc")
                .build();

        when(termsRepository.save(ArgumentMatchers.any(Terms.class))).thenReturn(entity);

        Response actual = termsService.create(request);
        assertThat(actual.getName()).isSameAs(entity.getName());
        assertThat(actual.getTermsDetailUrl()).isSameAs(entity.getTermsDetailUrl());
    }

    @Test
    void getAll(){
        Terms entityA = Terms.builder()
                .name("대출 이용약관 1")
                .termsDetailUrl("www.abcdefg.cde/sandjwk")
                .build();

        Terms entityB = Terms.builder()
                .name("대출 이용약관 2")
                .termsDetailUrl("www.abefg.cde/sabhdj")
                .build();

        ArrayList<Terms> list = new ArrayList<>(Arrays.asList(entityA, entityB));

        when(termsRepository.findAll()).thenReturn(list);

        List<Response> actual = termsService.getAll();

        assertThat(actual.size()).isSameAs(list.size());
    }
}