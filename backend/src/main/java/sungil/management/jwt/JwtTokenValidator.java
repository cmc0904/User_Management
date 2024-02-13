package sungil.management.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenValidator {

    @Value("${app.jwtSecret}")
    private String jwtSecret;
    private SecretKey key;

    @PostConstruct
    public void init() {
        // Base64 디코딩하여 SecretKey 생성
        byte[] secretBytes = Base64.getDecoder().decode(jwtSecret);
        this.key = new SecretKeySpec(secretBytes, SignatureAlgorithm.HS512.getJcaName());
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public List<String> getUserRolesFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.get("userRoles", List.class);
    }


    public String extractJwtToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null; // 적절한 토큰이 없을 경우
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        // 사용자의 식별자
        String userId = claims.getSubject();

        // 사용자의 권한 정보
        List<String> roles = (List<String>) claims.get("userRoles");

        // 권한 정보를 Spring Security의 SimpleGrantedAuthority 객체로 변환
        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // 인증 객체를 생성하여 반환
        return new UsernamePasswordAuthenticationToken(new User(userId, "", authorities), "", authorities);
    }
}
