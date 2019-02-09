package com.edu.system.service.token;

import com.edu.system.exception.SecurityException;
import com.edu.system.model.user.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${secret.key}")
    private String secretKey;

    @Override
    public String fromToken(String token) {
        String decodedToken = new String(Base64.getDecoder().decode(token));
        String[] parts = decodedToken.split(":");
        if (!DigestUtils.md5Hex(parts[0] + ":" + secretKey).equals(parts[1])) {
            throw new SecurityException("Invalid token");
        }
        return parts[0];
    }

    @Override
    public String createToken(User user) {
        String hex = DigestUtils.md5Hex(user.getLogin() + ":" + secretKey);
        String token = user.getLogin() + ":" + hex;
        return new String(Base64.getEncoder().encode(token.getBytes()));
    }
}
