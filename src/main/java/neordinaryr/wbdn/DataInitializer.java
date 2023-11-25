package neordinaryr.wbdn;

import lombok.RequiredArgsConstructor;
import neordinaryr.wbdn.domain.Member;
import neordinaryr.wbdn.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public void run(String... args) {
        Member testMember = Member.builder()
                .nickname("더구더구")
                .loginId("더구더구@example.com")
                .password(encoder.encode("password123"))
                .build();


        memberRepository.save(testMember);
    }
}
