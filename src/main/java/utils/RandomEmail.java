package utils;

import java.util.Random;

public class RandomEmail {
    public static String generateRandomEmail() {
        String username = getRandomString();

        String[] domains = {"example.com", "test.org", "sample.net"};
        String domain = domains[new Random().nextInt(domains.length)];

        return username + "@" + domain;
    }

    private static String getRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int usernameLength = 8;
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < usernameLength; i++) {
            int index = new Random().nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }
        return randomString.toString();
    }
}
