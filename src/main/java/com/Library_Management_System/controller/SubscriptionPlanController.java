package com.Library_Management_System.controller;

import com.Library_Management_System.payload.dto.SubscriptionPlanDTO;
import com.Library_Management_System.payload.response.ApiResponse;
import com.Library_Management_System.service.SubscriptionPlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/subscription-plans")
@RestController
@RequiredArgsConstructor
public class SubscriptionPlanController {

    private final SubscriptionPlanService subscriptionPlanService;

    @GetMapping
    public ResponseEntity<?> getAllSubscriptionPlans() {
        List<SubscriptionPlanDTO> plans =subscriptionPlanService.getAllSubscriptionPlan();
        return ResponseEntity.ok(plans);
    }
    @PostMapping("/admin/create")
    public ResponseEntity<?> createSubscriptionPlan(
            @Valid @RequestBody SubscriptionPlanDTO subscriptionPlanDTO
    ) throws Exception {
        SubscriptionPlanDTO plans =subscriptionPlanService.createSubscriptionPlan(subscriptionPlanDTO);
        return ResponseEntity.ok(plans);
    }
    @PutMapping("/admin/{id}")
    public ResponseEntity<?> updateSubscriptionPlan(
            @RequestBody SubscriptionPlanDTO subscriptionPlanDTO,
            @PathVariable Long id
    ) throws Exception {
        SubscriptionPlanDTO plans =subscriptionPlanService.updateSubscriptionPlan(id,subscriptionPlanDTO);
        return ResponseEntity.ok(plans);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> deleteSubscriptionPlan(
            @PathVariable Long id
    ) throws Exception {
        subscriptionPlanService.deleteSubscriptionPlan(id);
        ApiResponse response = new ApiResponse( "plan deleted success",true);
        return ResponseEntity.ok(response);
    }
}
