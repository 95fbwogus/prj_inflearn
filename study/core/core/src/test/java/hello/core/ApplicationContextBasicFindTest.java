package hello.core;

import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        @Test
        @DisplayName("빈 이름으로 조회")
        void findBeanByName() {
                MemberService memberService = ac.getBean("memberService", MemberService.class);
                assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        }

        @Test
        @DisplayName("이름 없이 타입으로만 조회")
        void findBeanByType() {
                MemberService memberService = ac.getBean(MemberService.class);
                assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        }

        @Test
        @DisplayName("구제 타입으로 조회")
        void findBeanByType2() {
                MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
                assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        }

        @Test
        @DisplayName("빈 이름으로 조회(실패)")
        void findBeanByName2() {
                assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("nobean", MemberService.class) );
        }
}
