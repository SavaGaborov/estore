package template.java17.web.rest.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import template.java17.component.TokenEncoder;
import template.java17.domain.User;
import template.java17.domain.enumeration.Role;
import template.java17.web.rest.dto.response.AdminInfoResponse;
import template.java17.web.rest.dto.response.CustomerInfoResponse;
import template.java17.web.rest.dto.response.JwtResponse;


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
        if (user.getRole() == Role.ROLE_ADMIN) {
            builder.admin(AdminInfoResponse.from(user));
        } else if (user.getRole() == Role.ROLE_CUSTOMER) {
            final CustomerInfoResponse customer = CustomerInfoResponse.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .languageCode(user.getLanguageCode())
                    .build();
            builder.customer(customer);
            return builder.build();
        }
        return builder.build();
    }

    public JwtResponse mapToJwtResponse(final User user) {
        return JwtResponse.builder()
                .accessToken(tokenEncoder.generate(user))
                .build();
    }
}
