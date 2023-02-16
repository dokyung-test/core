package hello.core;

import hello.core.member.Gradle;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        //직접 객체구현
        //MemberService memberService = new MemberServiceImpl(memberRepository);

        //의존관계가 설정되어있는 AppConfig만 객체생성 후, 구현체 연결
        //AppConfig appConfig = new AppConfig();

        //스프링컨테이너 -> AppConfig에 있는 환경설정 정보를 가지고 컨테이너에 넣어서 관리해줌
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //AppConfig의 메서드명으로 호출됨.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        //MemberService memberService = appConfig.memberService();


        Member member = new Member(1L, "memberA", Gradle.VIP);
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
