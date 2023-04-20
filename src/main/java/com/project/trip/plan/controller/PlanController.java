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
    @PostMapping
    public void save(@RequestBody @Valid PlanSaveRequest planSaveRequest){
        // 세션에서 로그인 유저 정보 받아온다.

        planService.save(planSaveRequest,"id");
    }
    @GetMapping("/{planId}")
    public void delete(@PathVariable int planId){
        //형준 : PostAuthInterceptor 인터셉터에 post와 user간 권한 확인 로직 넣어서 적용시켜봤음
        //권한이 없으면 NoPermissionException를 던지는거 같은데 comment나 post도 동일한 에러 던질 수 있을거 같으니 global 쪽으로
        //에러 빼보는건 어떨까싶음

        //세션에서 받아온 유저 정보와 글쓴이가 일치하는지 확인한다.

        //일치 할 시
        planService.delete(planId);
        //불일치 시

    }
    @PutMapping("/{planId}")
    public void update(@PathVariable int planId, @RequestBody PlanSaveRequest planSaveRequest){}

}
