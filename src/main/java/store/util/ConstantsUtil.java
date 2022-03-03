package store.util;

import org.springframework.scheduling.support.SimpleTriggerContext;

public final class ConstantsUtil {

    private ConstantsUtil() {
    }

    public static final String EMAIL_REGEXP = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    public static final Integer SESSION_ID_LENGTH = 36;
}
