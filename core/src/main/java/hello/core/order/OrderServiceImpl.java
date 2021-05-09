package hello.core.order;

import hello.core.discount.DisCountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DisCountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DisCountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

//    private final DisCountPolicy disCountPolicy = new FixDiscountPolicy();
//    private final DisCountPolicy disCountPolicy = new RateDiscountPolicy();
//   >> 지금 이 상태에서는 OrderServiceImple이 OrderSerice에만 의존하는 것이 아니라 discountPolicy에도 의존한다는 문제가 있음.

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int disCountPrice = discountPolicy.discount(member, itemPrice);
        //주문 구현체에서 할인정책의 구현부분에 대해 몰라도되기 때문에 좋은 설계임. > 단일 책임의 원칙
        return new Order(memberId, itemName, itemPrice , disCountPrice);
    }
}
