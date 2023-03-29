package com.project.trip.plan.service;

import com.project.trip.plan.model.request.PlanSaveRequest;
import com.project.trip.post.model.request.PostSaveRequest;

public interface PlanService {
    void show(String writer);
    void save(PlanSaveRequest planSaveRequest, String writer);
    void delete(int planId);
    void update(PlanSaveRequest planSaveRequest);
}
