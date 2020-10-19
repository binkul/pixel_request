package com.example.pixel_request.algorithm;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class Luhna {

    public int Generator(String digits, boolean check) {
        int sum = 0;
        String tmp = check ? digits : digits + "0";
        tmp = tmp.replaceAll("\\s", "");

        for (int i = 0; i <= tmp.length() - 1; i++) {
            int digit = tmp.charAt(tmp.length() - 1 - i) ^ '0';
            if (i % 2 != 0) {
                digit *= 2;
                digit = digit > 9 ? digit - 9 : digit;
            }
            sum += digit;
        }

        return sum % 10 == 0 ? 0 : 10 - (sum % 10);
    }
}
