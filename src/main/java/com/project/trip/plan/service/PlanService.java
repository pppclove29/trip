package com.project.trip.plan.service;

import com.project.trip.plan.model.request.PlanSaveRequest;

public interface PlanService {
    void show(String writer);
    void save(PlanSaveRequest planSaveRequest, String writer);
    void delete(int planId);
    void update(PlanSaveRequest planSaveRequest);
}
