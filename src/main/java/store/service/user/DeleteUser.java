package store.service.user;

import org.springframework.transaction.annotation.Transactional;
import store.domain.User;
import store.repository.UserRepository;
import store.util.TimeUtil;

public class DeleteUser {

    private UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public void execute(User user) {
        user.setEmail("deleted_" + user.getEmail() + "_" + TimeUtil.now());
        user.setFirstName("");
        user.setLastName("");
        user.setDeleted(true);
        userRepository.save(user);
    }
}
