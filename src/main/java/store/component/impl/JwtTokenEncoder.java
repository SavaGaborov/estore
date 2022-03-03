package store.component.impl;

import store.component.TokenEncoder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import store.configuration.CustomProperties;
import store.domain.User;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.UUID;

@Component
@AllArgsConstructor
public class JwtTokenEncoder implements TokenEncoder {

    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    private static final String ISSUER = "bojantransstore";

    private final CustomProperties customProperties;

    @Override
    public Claims getClaims(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(customProperties.getJwtSigningKey()).build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    @Override
    public String generate(User user) {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(customProperties.getJwtSigningKey());
        final Key signingKey = new SecretKeySpec(apiKeySecretBytes, SIGNATURE_ALGORITHM.getJcaName());

        final long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .claim("role", user.getRole().name())
                .setSubject(String.valueOf(user.getEmail()))
                .setIssuer(ISSUER)
                .setAudience(customProperties.getJwtAudience())
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(
                        new Date(Duration.ofDays(365).plusMillis(currentTimeMillis).toMillis()))
                .signWith(signingKey)
                .compact();
    }

    @Override
    public boolean isExpired(String jwt) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(customProperties.getJwtSigningKey()).build()
                .parseClaimsJws(jwt).getBody();
        return claims.getExpiration().before(new Date());
    }
}
