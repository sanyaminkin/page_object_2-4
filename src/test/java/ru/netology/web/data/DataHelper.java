package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardNumber {
        private String cardNumber;
    }

        public static CardNumber getFirstCardNumber() {
            return new CardNumber("5559 0000 0000 0001");
        }

        public static CardNumber getSecondCardNumber() {
            return new CardNumber("5559 0000 0000 0002");
        }

        public static CardNumber randomCardNumber() {
            final Faker faker = new Faker();
            return new CardNumber(faker.numerify("#### #### #### ####"));
        }

}


