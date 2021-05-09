package hello.core;

import hello.core.discount.DisCountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

//Application 전체를 설정하고 구성
public class AppConfig {

    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    //생성자 주입

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), disCountPolicy());
    }
    //>method 명을 보는순간 역할이 바로 드러날 수 있도록 수정

    public DisCountPolicy disCountPolicy(){
        return new FixDiscountPolicy();
    }

}
