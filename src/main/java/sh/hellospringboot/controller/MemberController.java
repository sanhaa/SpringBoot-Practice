package sh.hellospringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sh.hellospringboot.service.MemberService;

@Controller
public class MemberController {
//    private final MemberService memberService = new MemberService();

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        // Autowired 어노테이션을 통해
        // spring container 가 관리하는 controller, memberService bean을 연결해줌
        this.memberService = memberService;
    }

}
