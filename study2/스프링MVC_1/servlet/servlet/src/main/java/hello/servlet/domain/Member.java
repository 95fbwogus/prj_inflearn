package hello.servlet.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private Long id;
    private String username;
    private int age;

    public Member() {};

    public Member(String userName, int age) {
        this.username = userName;
        this.age = age;
    }

}
