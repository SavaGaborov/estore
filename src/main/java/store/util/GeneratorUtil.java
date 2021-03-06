package store.util;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Random;
import java.util.UUID;

import static store.util.ConstantsUtil.SESSION_ID_LENGTH;

public final class GeneratorUtil {

    private static final String ALPHA_NUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz";

    private GeneratorUtil() {
    }

    public static String generate4Digit() {
        return String.valueOf(1000 + new Random().nextInt(9000));
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static String generatePassword() {
        final int length = new Random().nextInt(9) + 10;
        final StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int) (ALPHA_NUMERIC.length() * Math.random());
            sb.append(ALPHA_NUMERIC.charAt(index));
        }
        return sb.toString();
    }

    public static String getRandomStringWithNumbers() {
        return RandomStringUtils.random(SESSION_ID_LENGTH, true, true);
    }
}
