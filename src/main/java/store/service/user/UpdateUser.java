package store.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import store.domain.User;
import store.repository.UserRepository;
import store.web.rest.dto.request.UpdateUserRequest;

@Service
@RequiredArgsConstructor
public class UpdateUser {

    private UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public void execute(final User user, final UpdateUserRequest data) {
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setEmail(data.getEmail().trim().toLowerCase());
        user.setLanguageCode(data.getLanguageCode());
        user.setCountry(data.getCountry());
        user.setSubscriptions(data.getSubscriptions().toArray().toString());
        userRepository.save(user);
    }
}
