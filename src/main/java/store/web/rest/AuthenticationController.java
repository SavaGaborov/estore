package store.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.service.authentication.ChangePassword;
import store.service.authentication.SignIn;
import store.service.authentication.SignUp;
import store.web.rest.dto.mapper.JwtMapper;
import store.web.rest.dto.request.ChangePasswordRequest;
import store.web.rest.dto.request.SignInRequest;
import store.web.rest.dto.request.SignUpRequest;
import store.web.rest.dto.response.JwtResponse;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final SignUp signUp;
    private final SignIn signIn;
    private final ChangePassword changePassword;
    private final JwtMapper jwtMapper;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpRequest data) {
        log.info("POST /authentication/sign-up - {}", data.toString());
        signUp.registerUser(data.toUserInfo());
        return new ResponseEntity<>(CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtResponse> signIn(@RequestBody @Valid SignInRequest data) {
        log.info("POST /authentication/sign-in - {}", data.toString());
        return ResponseEntity.ok(jwtMapper.mapToJwtResponse(signIn.signIn(data)));
    }

    @PostMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody @Valid ChangePasswordRequest data) {
        log.info("POST /authentication/change-password - {}", data.toString());
        changePassword.setNewPassword(data.toChangePasswordInfo());
        return ResponseEntity.noContent().build();
    }
}
