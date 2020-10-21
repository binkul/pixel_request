package com.example.pixel_request.algorithm;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class Luhna {

    public static int generate(String digits, boolean check) {
        int sum = 0;
        String fullNumber = check ? digits : digits + "0";

        for (int i = 0; i <= fullNumber.length() - 1; i++) {
            int digit = fullNumber.charAt(fullNumber.length() - 1 - i) ^ '0';
            if (i % 2 != 0) {
                digit *= 2;
                digit = digit > 9 ? digit - 9 : digit;
            }
            sum += digit;
        }

        return sum % 10 == 0 ? 0 : 10 - (sum % 10);
    }
}
