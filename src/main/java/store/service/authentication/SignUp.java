package store.service.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.domain.User;
import store.service.user.value.UserInfo;

@Service
@RequiredArgsConstructor
public class SignUp {

    final CreateUser createUser;

    @Transactional(rollbackFor = Exception.class)
    public void registerUser(final UserInfo userInfo) {
        final User user = createUser.createUser(userInfo);
    }
}
