package com.pewde.taskmanager.token;

import com.fasterxml.jackson.annotation.JsonValue;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Token {

    @JsonValue
    private String token;

    private Date expiry;

    private static Map<Integer, Token> auths = new HashMap<>();

    private Token(){
        expiry = Date.from(LocalDateTime.now().plusMinutes(30).atZone(ZoneId.systemDefault()).toInstant());
        token = UUID.randomUUID().toString().replace("-", "");
    }

    public static Token generateToken(int userId){
        Token token = new Token();
        auths.put(userId, token);
        return token;
    }

    public static boolean checkAuthentication(int userId, String token){
        return auths.containsKey(userId) && auths.get(userId).toString().equals(token);
    }

    public static boolean checkExpiration(int userId, String token){
        Date now = new Date();
        return auths.containsKey(userId) && auths.get(userId).toString().equals(token) &&auths.get(userId).expiry.getTime() > now.getTime();
    }

    public static void extendToken(int userId, String token){
        if (!checkAuthentication(userId, token)) return;
        auths.get(userId).extend();
    }

    public static void expireToken(int userId, String token){
        if (!checkAuthentication(userId, token)) return;
        auths.get(userId).expire();
    }

    public static String getTokenFromAuthorization(String authToken){
        return authToken.replaceAll("Bearer ", "");
    }

    private void extend(){
        expiry = Date.from(LocalDateTime.now().plusMinutes(30).atZone(ZoneId.systemDefault()).toInstant());
    }

    private void expire(){
        expiry = new Date();
    }

    @Override
    public String toString(){
        return token;
    }

}
