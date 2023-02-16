package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //나중에 변경할 수 있다. -> 다형성으로 인터페이스도 의존하고 구현체에도 의존하고 있다.
    // => DIP(의존관계 역전원칙) 에 위배
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    //인터페이스만 존재 -> 추상화에만 존재. => DIP를 따르고 있다.
    //실제 구현체의 지정은 AppConfig에서
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
