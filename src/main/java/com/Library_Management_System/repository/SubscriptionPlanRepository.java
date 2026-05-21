package com.Library_Management_System.repository;

import com.Library_Management_System.modal.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan,Long> {

    Boolean existsByPlanCode(String planCode);
}
