package inflearn.springmvc.basic;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HelloData {
    private String username;
    private int age;
}
