package com.project_loan.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); //정확하게 필드명과 형식이 일치 할때만 매핑되도록 설정
        modelMapper.getConfiguration().setSkipNullEnabled(true); //DTO에서 entity로 변환하고자 할 때, null로 넘어오는 필드를 제외하고 업데이트 하고 싶을 때 사용
        return modelMapper;
    }
}
