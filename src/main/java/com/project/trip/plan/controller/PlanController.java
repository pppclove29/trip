package com.project.trip.plan.controller;

import com.project.trip.plan.model.request.PlanSaveRequest;
import com.project.trip.plan.service.PlanServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@Controller
@RequestMapping("/plans")
public class PlanController {

    PlanServiceImpl planService;
    @GetMapping
    public String show(){

        return "plan";
    }
    @PostMapping
    public void save(@RequestBody @Valid PlanSaveRequest planSaveRequest){
        // 세션에서 로그인 유저 정보 받아온다.

        planService.save(planSaveRequest,"id");
    }
    @GetMapping("/{planId}")
    public void delete(@PathVariable int planId){
        //세션에서 받아온 유저 정보와 글쓴이가 일치하는지 확인한다.

        //일치 할 시
        planService.delete(planId);
        //불일치 시

    }
    @PutMapping("/{planId}")
    public void update(@PathVariable int planId, @RequestBody PlanSaveRequest planSaveRequest){}

}
