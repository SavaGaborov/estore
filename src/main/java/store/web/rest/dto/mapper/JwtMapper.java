package store.web.rest.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import store.component.TokenEncoder;
import store.domain.User;
import store.web.rest.dto.response.UserInfoResponse;
import store.web.rest.dto.response.JwtResponse;


@Component
@RequiredArgsConstructor
public class JwtMapper {

    private final TokenEncoder tokenEncoder;

    public JwtResponse mapToJwtResponse(Pair<User, String> userJwt) {
        final User user = userJwt.getFirst();
        final JwtResponse.JwtResponseBuilder builder = JwtResponse.builder()
                .accessToken(userJwt.getSecond())
                .role(user.getRole())
                .languageCode(user.getLanguageCode());
            final UserInfoResponse customer = UserInfoResponse.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .languageCode(user.getLanguageCode())
                    .build();
            builder.customer(customer).build();
            return builder.build();
    }

    public JwtResponse mapToJwtResponse(final User user) {
        return JwtResponse.builder()
                .accessToken(tokenEncoder.generate(user))
                .build();
    }
}
