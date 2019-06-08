package com.company;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MainTestTest {



    @Test
    void testEncoding() {
        HashMap<Integer, String> inputsAndExpected = new HashMap<>();
        inputsAndExpected.put(0, "");
        inputsAndExpected.put(1, "I");
        inputsAndExpected.put(2, "II");
        inputsAndExpected.put(3, "III");
        inputsAndExpected.put(4, "IV");
        inputsAndExpected.put(5, "V");
        inputsAndExpected.put(6, "VI");
        inputsAndExpected.put(9, "IX");
        inputsAndExpected.put(10, "X");
        inputsAndExpected.put(14, "XIV");
        inputsAndExpected.put(20, "XX");
        inputsAndExpected.put(30, "XXX");
        inputsAndExpected.put(40, "XL");
        inputsAndExpected.put(49, "XLIX");
        inputsAndExpected.put(50, "L");
        inputsAndExpected.put(90, "XC");
        inputsAndExpected.put(100, "C");
        inputsAndExpected.put(400, "CD");
        inputsAndExpected.put(450, "CDL");
        inputsAndExpected.put(500, "D");
        inputsAndExpected.put(900, "CM");
        inputsAndExpected.put(1000, "M");
        inputsAndExpected.put(1987, "MCMLXXXVII");
        inputsAndExpected.put(2019, "MMXIX");

        for (Map.Entry<Integer, String> entry :inputsAndExpected.entrySet()) {
            Integer input = entry.getKey();
            String expected = entry.getValue();
            String result = encodeRomanNumeral(input);
            assertEquals( expected, result, "Input is: " + input);
        }

    }

    @Test
    void TestDecoding() {
        HashMap<Integer, String> expectedAndInputs = new HashMap<>();
        expectedAndInputs.put(0, "");
        expectedAndInputs.put(1, "I");
        expectedAndInputs.put(2, "II");

        expectedAndInputs.put(3, "III");
        expectedAndInputs.put(4, "IV");
        expectedAndInputs.put(5, "V");
        expectedAndInputs.put(6, "VI");
        expectedAndInputs.put(9, "IX");
        expectedAndInputs.put(10, "X");
        expectedAndInputs.put(14, "XIV");
        expectedAndInputs.put(20, "XX");
        expectedAndInputs.put(30, "XXX");
        expectedAndInputs.put(40, "XL");
        expectedAndInputs.put(49, "XLIX");
        expectedAndInputs.put(50, "L");
        expectedAndInputs.put(90, "XC");
        expectedAndInputs.put(100, "C");
        expectedAndInputs.put(400, "CD");
        expectedAndInputs.put(450, "CDL");
        expectedAndInputs.put(500, "D");
        expectedAndInputs.put(900, "CM");
        expectedAndInputs.put(1000, "M");
        expectedAndInputs.put(1987, "MCMLXXXVII");
        expectedAndInputs.put(2019, "MMXIX");


        
        for (Map.Entry<Integer, String> entry :expectedAndInputs.entrySet()) {
            String input = entry.getValue();
            int expected = entry.getKey();
            int result = decodeRomanNumeral(input);
            assertEquals( expected, result, "Input is: " + input);
        }
    }

    private int decodeRomanNumeral(String input) {
        DigitEncoding[] digitEncodingArray = {
                new DigitEncoding(1000, "M"),
                new DigitEncoding(900, "CM"),
                new DigitEncoding(500, "D"),
                new DigitEncoding(400, "CD"),
                new DigitEncoding(100, "C"),
                new DigitEncoding(90, "XC"),
                new DigitEncoding(50, "L"),
                new DigitEncoding(40, "XL"),
                new DigitEncoding(10, "X"),
                new DigitEncoding(9, "IX"),
                new DigitEncoding(5, "V"),
                new DigitEncoding(4, "IV"),
                new DigitEncoding(1, "I"),
        };
        int result = 0;
        int beginIndex = 0;
        for (DigitEncoding digit : digitEncodingArray) {
            while (input.startsWith(digit.roman, beginIndex)){
                beginIndex+=digit.roman.length();
                result += digit.number;
            }
        }


        return result;
    }

    private String encodeRomanNumeral(int input) {
        DigitEncoding[] digitEncodingArray = {
                new DigitEncoding(1000, "M"),
                new DigitEncoding(900, "CM"),
                new DigitEncoding(500, "D"),
                new DigitEncoding(400, "CD"),
                new DigitEncoding(100, "C"),
                new DigitEncoding(90, "XC"),
                new DigitEncoding(50, "L"),
                new DigitEncoding(40, "XL"),
                new DigitEncoding(10, "X"),
                new DigitEncoding(9, "IX"),
                new DigitEncoding(5, "V"),
                new DigitEncoding(4, "IV"),
                new DigitEncoding(1, "I"),
        };
        StringBuilder sb = new StringBuilder();

        for (DigitEncoding digit : digitEncodingArray) {
            while (input>= digit.number){
                sb.append(digit.roman);
                input -= digit.number;
            }
        }
        return sb.toString();
    }
    class DigitEncoding{
        int number;
        String roman;

        public DigitEncoding(int i, String m) {
            number = i;
            roman = m;
        }
    }
}