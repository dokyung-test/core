package hello.core.discount;

import hello.core.member.Gradle;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {


    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10%할인이 되어야 한다")
    void vip_o(){
        //given
        Member vip = new Member(1L, "VIP", Gradle.VIP);
        //when
        int discount = discountPolicy.discount(vip, 10000);
        //then
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다")
    void vip_x(){
        //given
        Member basic = new Member(2L, "BASIC", Gradle.BASIC);
        //when
        int discount = discountPolicy.discount(basic, 10000);
        //then
        Assertions.assertThat(discount).isEqualTo(0);
    }
}