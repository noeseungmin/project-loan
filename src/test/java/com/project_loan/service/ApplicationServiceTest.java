package com.project_loan.service;


import com.project_loan.domain.AcceptTerms;
import com.project_loan.domain.Application;
import com.project_loan.domain.Terms;
import com.project_loan.dto.ApplicationDto;
import com.project_loan.dto.ApplicationDto.Request;
import com.project_loan.dto.ApplicationDto.Response;
import com.project_loan.exception.BaseException;
import com.project_loan.repository.AcceptTermsRepository;
import com.project_loan.repository.ApplicationRepository;
import com.project_loan.repository.TermsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTest {

    @InjectMocks
    ApplicationServiceImpl applicationService;

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private TermsRepository termsRepository;

    @Mock
    private AcceptTermsRepository acceptTermsRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void createApplication(){
        Application entity = Application.builder()
                .name("Member")
                .cellPhone("010-1234-5678")
                .email("mail@abcd.com")
                .hopeAmount(BigDecimal.valueOf(50000000))
                .build();

        Request request = Request.builder()
                .name("Member")
                .cellPhone("010-1234-5678")
                .email("mail@abcd.com")
                .hopeAmount(BigDecimal.valueOf(50000000))
                .build();


        when(applicationRepository.save(any(Application.class))).thenReturn(entity);

        Response actual = applicationService.create(request);

        assertThat(actual.getHopeAmount()).isSameAs(entity.getHopeAmount());
        assertThat(actual.getName()).isSameAs(entity.getName());
    }

    @Test
    void applicationGet(){
        Long findId = 1L;

        Application entity = Application.builder()
                .applicationId(1L)
                .build();

        when(applicationRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = applicationService.get(findId);

        assertThat(actual.getApplicationId()).isSameAs(findId);
    }

    @Test
    void updatedResponse(){
        Long findId = 1L;
        Application entity = Application.builder()
                .applicationId(1L)
                .hopeAmount(BigDecimal.valueOf(50000000))
                .build();

        Request request = Request.builder()
                .hopeAmount(BigDecimal.valueOf(5000000))
                .build();

        when(applicationRepository.save(any(Application.class))).thenReturn(entity);
        when(applicationRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = applicationService.update(findId, request);

        assertThat(actual.getHopeAmount()).isSameAs(request.getHopeAmount());
    }

    @Test
    void delete(){
        Long targetId = 1L;

        Application entity = Application.builder()
                .applicationId(1L)
                .build();

        when(applicationRepository.save(any(Application.class))).thenReturn(entity);
        when(applicationRepository.findById(targetId)).thenReturn(Optional.ofNullable(entity));

        applicationService.delete(targetId);

        assertThat(entity.getIsDeleted()).isSameAs(true);
    }

    @Test
    void AcceptTermsOfApplication(){
        Terms entity1 = Terms.builder()
                .termsId(1L)
                .name("약관 1")
                .termsDetailUrl("https://abcdef.ghi")
                .build();

        Terms entity2 = Terms.builder()
                .termsId(2L)
                .name("약관 2")
                .termsDetailUrl("https://abcdef.ghi/dsanjdwh")
                .build();

        List<Long> acceptTerms = Arrays.asList(1L, 2L);

        ApplicationDto.AcceptTerms request = ApplicationDto.AcceptTerms.builder()
                .acceptTermsIds(acceptTerms)
                .build();

        Long findId = 1L;

        when(applicationRepository.findById(findId)).thenReturn(Optional.ofNullable(Application.builder().build()));
        when(termsRepository.findAll(Sort.by(Sort.Direction.ASC, "termsId"))).thenReturn(Arrays.asList(entity1, entity2));
        when(acceptTermsRepository.save(ArgumentMatchers.any(AcceptTerms.class))).thenReturn(AcceptTerms.builder().build());

       Boolean actual = applicationService.acceptTerms(findId, request);

       assertThat(actual).isTrue();
    }
    @Test
    void NotAllAcceptTermsOfApplication(){
        Terms entity1 = Terms.builder()
                .termsId(1L)
                .name("약관 1")
                .termsDetailUrl("https://abcdef.ghi")
                .build();

        Terms entity2 = Terms.builder()
                .termsId(2L)
                .name("약관 2")
                .termsDetailUrl("https://abcdef.ghi/dsanjdwh")
                .build();

        List<Long> acceptTerms = Arrays.asList(1L);

        ApplicationDto.AcceptTerms request = ApplicationDto.AcceptTerms.builder()
                .acceptTermsIds(acceptTerms)
                .build();

        Long findId = 1L;

        when(applicationRepository.findById(findId)).thenReturn(Optional.ofNullable(Application.builder().build()));
        when(termsRepository.findAll(Sort.by(Sort.Direction.ASC, "termsId"))).thenReturn(Arrays.asList(entity1, entity2));

        Assertions.assertThrows(BaseException.class, () -> applicationService.acceptTerms(findId, request));
    }
    @Test
    void RequestNotExistAcceptTermsOfApplication(){
        Terms entity1 = Terms.builder()
                .termsId(1L)
                .name("약관 1")
                .termsDetailUrl("https://abcdef.ghi")
                .build();

        Terms entity2 = Terms.builder()
                .termsId(2L)
                .name("약관 2")
                .termsDetailUrl("https://abcdef.ghi/dsanjdwh")
                .build();

        List<Long> acceptTerms = Arrays.asList(1L, 3L);

        ApplicationDto.AcceptTerms request = ApplicationDto.AcceptTerms.builder()
                .acceptTermsIds(acceptTerms)
                .build();

        Long findId = 1L;

        when(applicationRepository.findById(findId)).thenReturn(Optional.ofNullable(Application.builder().build()));
        when(termsRepository.findAll(Sort.by(Sort.Direction.ASC, "termsId"))).thenReturn(Arrays.asList(entity1, entity2));

        Assertions.assertThrows(BaseException.class, () -> applicationService.acceptTerms(findId, request));
    }
}
