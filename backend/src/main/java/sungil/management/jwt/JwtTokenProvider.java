package sungil.management.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sungil.management.domain.Role;
import sungil.management.domain.User;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;
    private SecretKey key;

    @PostConstruct
    public void init() {
        // Base64 디코딩하여 SecretKey 생성
        byte[] secretBytes = Base64.getDecoder().decode(jwtSecret);
        this.key = new SecretKeySpec(secretBytes, SignatureAlgorithm.HS512.getJcaName());
    }
    public String generateToken(User user, List<String> roleList) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);


        return Jwts.builder()
                .setSubject(user.getUserId())
                .claim("userName", user.getUserName())
                .claim("userPhone", user.getUserPhone())
                .claim("userRoles", roleList)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }
}
