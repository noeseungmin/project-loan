package com.project_loan.service;

import com.project_loan.domain.Counsel;
import com.project_loan.dto.CounselDto.Request;
import com.project_loan.dto.CounselDto.Response;
import com.project_loan.exception.BaseException;
import com.project_loan.exception.ResultType;
import com.project_loan.repository.CounselRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CounselServiceTest {

    @InjectMocks
    CounselServiceImpl counselService;

    @Mock
    private CounselRepository counselRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void RequestCounsel(){
        Counsel entity = Counsel.builder()
                .name("Member")
                .cellPhone("010-1111-2222")
                .email("abc@der.gc")
                .memo("대출 받고 싶습니다. 연락 주세요")
                .zipCode("12345")
                .address("서울 특별시 ㅇㅇ구 ㅇㅇ동")
                .addressDetail("ㅇㅇㅇ동 ㅇㅇㅇ호")
                .build();

        Request request = Request.builder()
                .name("Member")
                .cellPhone("010-1111-2222")
                .email("abc@der.gc")
                .memo("대출 받고 싶습니다. 연락 주세요")
                .zipCode("12345")
                .address("서울 특별시 ㅇㅇ구 ㅇㅇ동")
                .addressDetail("ㅇㅇㅇ동 ㅇㅇㅇ호")
                .build();

        when(counselRepository.save(ArgumentMatchers.any(Counsel.class))).thenReturn(entity);

        Response actual = counselService.create(request);

        assertThat(actual.getName()).isSameAs(entity.getName());
    }
    
    @Test
    void ReturnResponseCounselEntity(){
        Long findId = 1L;

        Counsel entity = Counsel.builder()
                .counselId(1L)
                .build();

        when(counselRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = counselService.get(findId);

        assertThat(actual.getCounselId()).isEqualTo(findId);
    }

    @Test
    void ThrowException_RequestNotExistCounselId(){
        Long findId = 2L;

        when(counselRepository.findById(findId)).thenThrow(new BaseException(ResultType.SYSTEM_ERROR));

        assertThrows(BaseException.class, () -> counselService.get(findId));
    }

    @Test
    void Update(){
        Long findId = 1L;

        Counsel entity = Counsel.builder()
                .counselId(1L)
                .name("Member")
                .build();

        Request request = Request.builder()
                .name("Member Kim")
                .build();

        when(counselRepository.save(ArgumentMatchers.any(Counsel.class))).thenReturn(entity);
        when(counselRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = counselService.update(findId, request);

        assertThat(actual.getCounselId()).isSameAs(findId);
        assertThat(actual.getName()).isSameAs(request.getName());
    }

    @Test
    void delete(){
        Long targetId = 1L;

        Counsel entity = Counsel.builder()
                .counselId(1L)
                .build();

        when(counselRepository.save(ArgumentMatchers.any(Counsel.class))).thenReturn(entity);
        when(counselRepository.findById(targetId)).thenReturn(Optional.ofNullable(entity));

        counselService.delete(targetId);

        assertThat(entity.getIsDeleted()).isSameAs(true);
    }
}