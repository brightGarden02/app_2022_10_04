package com.example.app__2022_10_04;

import com.example.app__2022_10_04.jwt.JwtProvider;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JwtTests {

    @Autowired
    private JwtProvider jwtProvider;

    @Value("${custom.jwt.secretKey}")
    private String secretKeyPlain;


    @Test
    @DisplayName("secretKey 키가 존재해야한다.")
    void t1() {
        assertThat(secretKeyPlain).isNotNull();
    }


    @Test
    @DisplayName("sercretKey 원문으로 hmac 암호화 알고리즘에 맞는 SecretKey 객체를 만들 수 있다.")
    void t2() {

        String keyBase64Encoded = Base64.getEncoder().encodeToString(secretKeyPlain.getBytes());
        SecretKey secretKey = Keys.hmacShaKeyFor(keyBase64Encoded.getBytes());

        assertThat(secretKey).isNotNull();
    }

    @Test
    @DisplayName("JwtProvider 객체로 SecretKey 객체를 생설할 수 있다.")
    void t3() {
        SecretKey secretKey = TestUtil.callMethod(jwtProvider, "getSecretKey");

        assertThat(secretKey).isNotNull();
    }


    @Test
    @DisplayName("SecretKey 객체는 단 한번만 생성되야만 한다.")
    void t4() {
//        SecretKey secretKey1 = jwtProvider.getSecretKey();
        SecretKey secretKey1 = TestUtil.callMethod(jwtProvider, "getSecretKey");

        SecretKey secretKey2 = TestUtil.callMethod(jwtProvider, "getSecretKey");

        assertThat(secretKey1 == secretKey2).isTrue();
    }

}
