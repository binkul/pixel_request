package com.example.pixel_request.algorithm;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class Luhna {

    public int generate(String digits, boolean check) {
        int sum = 0;
        String fullNumber = clean(digits);
        fullNumber = check ? fullNumber : fullNumber + "0";

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

    private String clean(String roughNumber) {
        String cleanNumber = roughNumber.replaceAll("\\s", "");
        cleanNumber = cleanNumber.replaceAll("_", "");
        cleanNumber = cleanNumber.replaceAll("-", "");
        cleanNumber = cleanNumber.replaceAll(":", "");
        return cleanNumber;
    }
}
