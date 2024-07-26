package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class StringTest {
    @Test
    void firstTest(){
        String test = "1,2";
        String[] result = test.split(",");
        assertThat(result).containsExactly("1", "2");

    }
    @Test
    void secondTest(){
        String test = "1";
        String[] result = test.split(",");
        assertThat(result).containsOnly("1");
    }
    @Test
    void thirdTest(){
        String test = "(1,2)";
        String test2 = test.substring(1,4);
        assertThat(test2).containsSequence("1", "," ,"2");
    }
    @Test
    @DisplayName("assertThatThrownBy String 범위 테스트")
    void fourthTest(){
        String test = "abc";
        int n = 5;
        assertThatThrownBy(() ->{
            test.charAt(n);
        }).isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessageContaining("String index out of range: ", n);
    }
}
