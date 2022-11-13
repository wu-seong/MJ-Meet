package group4.MJMeet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LombookTest {

    private String hello;
    private int lombok;

    public static void main(String[] args) {
        LombookTest helloLombok = new LombookTest();
        helloLombok.setHello("헬로");
        helloLombok.setLombok(5);

        System.out.println(helloLombok.getHello());
        System.out.println(helloLombok.getLombok());
    }
}