package org.example;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JwtTest {
    @Test
    public void testGen() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "Yun");
        //生成jwt代碼
        String token = JWT.create().withClaim("user", claims)//添加載荷
                .withExpiresAt(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 12))//過期時間
                .sign(Algorithm.HMAC256("ccc"));//指定算法密鑰
        System.out.println(token);
    }

    @Test
    public void testParse() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6Ill1biJ9LCJleHAiOjE3MDY4MDIyMDh9." +
                "z92t9qEsLE2fdSGXNM8bqhp3gPxl9QlEOmu919z3mdA";
        JWTVerifier jwtVerifier =  JWT.require(Algorithm.HMAC256("ccc")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        System.out.println(decodedJWT.getClaims());
    }

}
