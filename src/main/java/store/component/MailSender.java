package store.component;

import java.util.Map;

public interface MailSender {
    void send(String to, String templateId, Map<String, String> dynamicData);
}
