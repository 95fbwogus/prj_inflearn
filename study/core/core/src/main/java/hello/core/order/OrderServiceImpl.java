package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        //할인에 대한 정책은 discountPolicy가 알아서 처리해준다.
        //만약 할인에 대한 정책이 변경되면 discountPolicy만 변경해주면 된다.
        //단일 책임 원칙을 잘 지킨 것이다.

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
