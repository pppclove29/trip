package com.project.trip.plan.controller;

import com.project.trip.plan.model.request.PlanSaveRequest;
import com.project.trip.plan.service.PlanServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/plans")
public class PlanController {

    PlanServiceImpl planService;
    @GetMapping
    public void show(){

    }
    @PostMapping
    public void save(@RequestBody PlanSaveRequest planSaveRequest){
        planService.save(planSaveRequest,"id");
    }
    @GetMapping("/{planId}")
    public void delete(@PathVariable int planId){}
    @PutMapping("/{planId}")
    public void update(@PathVariable int planId, @RequestBody PlanSaveRequest planSaveRequest){}
}
