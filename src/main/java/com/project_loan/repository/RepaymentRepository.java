package com.project_loan.repository;

import com.project_loan.domain.Repayment;
import com.project_loan.dto.RepaymentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepaymentRepository extends JpaRepository<Repayment, Long> {
       List<Repayment> findAllByApplicationId(Long applicationId);
}
