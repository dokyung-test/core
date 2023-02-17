package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.
//기존의 Impl에서는 필요한 인터페이스를 파라미터로 받는 생성자만 지정하고,
//실제 구현체의 지정은 여기서 이루어진다.
// -> AppConfig는 생성한 객체 인스턴스의 참조(레퍼런스)를 "생성자를 통해서 주입"해준다.
//Impl은 "의존관계에 대한 고민은 외부 AppConfig에게 맡기고 실행에만 집중한다."
@Configuration
public class AppConfig {


    // dokyung_intellJ sourcetree 테스트


    //new로 생성하는게 아니라 메서드로 뺀 장점 : 각 메서드명만 확인해도 각 기능이 눈에 띔.
    @Bean
    public MemberService memberService(){
        //return new MemoryMemberRepository();
        // -> 메서드를 호출해서 구현체를 지정해준다.
        return new MemberServiceImpl(memberRepository());
    }


    //실제 구현체를 지정
    //구현체 또 변경되면 이 메서드만 바꿔주면 나머지 위 코드 등 변경안해도 됨!
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        //실제 구현체 지정 -> OrderService는 두개의 구현체가 필요
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }
}
