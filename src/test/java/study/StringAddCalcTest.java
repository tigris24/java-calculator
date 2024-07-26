package study;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class StringAddCalcTest {


    @ParameterizedTest
    @MethodSource({"data"})
    void test(String input) {
        List<Integer> results = new ArrayList<>();
        String[] parts = input.split(";");
        int sum = 0;

        // get number from string
        getSubParts(parts, results);

        System.out.println(sum);
    }

    @ParameterizedTest
    @MethodSource({"data2"})
    void test2(String input) {
        System.out.println(checkCustomDelimiter(input));
    }


    private static Stream<Arguments> data() {
        return Stream.of(Arguments.of("1;2:3"));
    }

    private static Stream<Arguments> data2() {
        return Stream.of(Arguments.of("//;\n1;2;3"));
    }

    private void getSubParts(String[] parts, List<Integer> results){
        String[] subParts;
        for (String part : parts) {
            subParts = part.split(":");
            for(String subPart : subParts){
                // check if number is integer and greater than 0
                results.add(isPositiveNumber(subPart));
            }
        }
    }

    private int isPositiveNumber(String s){
        int number;
        try{
            number = Integer.parseInt(s);
        }catch(NumberFormatException e){    // check whether s is not number
            throw new RuntimeException("Not a valid number: " + s);
        }
        if(number <= 0){    // check whether number is greater than 0
            throw new RuntimeException("Invalid number: " + number);
        }
        return number;
    }

    private int checkCustomDelimiter(String s){
        Pattern pattern = Pattern.compile( "//(.)\n(.*)");
        Matcher matcher = pattern.matcher(s);
        if(matcher.find()){
            String[] numbers = matcher.group(2).split(matcher.group(1));
            int sum =0;
            for(String number : numbers){
                sum += isPositiveNumber(number);
            }
            return sum;

        }
        return 0;
    }





}
