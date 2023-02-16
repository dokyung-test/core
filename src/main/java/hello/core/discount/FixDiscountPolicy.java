package hello.core.discount;

import hello.core.member.Gradle;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int disCountFixAmount = 1000;

    //vip면 1000원 할인
    @Override
    public int discount(Member member, int price) {
        //enum 타입은 ==
        if(member.getGrade() == Gradle.VIP){
            return disCountFixAmount;
        }else{
            return 0;
        }
    }
}
