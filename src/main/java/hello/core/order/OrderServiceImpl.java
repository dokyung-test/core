package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{




    //정액할인
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //정률할인
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // -> 할인방식을 변경하는 순간 OrderServiceImpl의 소스코드도 함께 변경해야한다 -> OCP위반


    // -> 추상(인터페이스)에만 의존하도록 의존관계를 변경해야 한다.
    private final DiscountPolicy discountPolicy;

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    //필요한 인터페이스만 지정할 뿐 실제 구현체는 AppConfig가 지정한다.
    public OrderServiceImpl(MemberRepository memberRepository,DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discount = discountPolicy.discount(member, itemPrice);
        Order order = new Order(memberId, itemName, itemPrice, discount);
        return order;
    }
}
