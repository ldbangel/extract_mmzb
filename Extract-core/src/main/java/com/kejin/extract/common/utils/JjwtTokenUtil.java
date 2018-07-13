package com.kejin.extract.common.utils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JjwtTokenUtil {
	public static final String JWT_ID = "jwt";
    public static final String JWT_SECRET = "hong1mu2zhi3ruan4jian5";
    public static final int JWT_TTL = 60*60*1000;  //millisecond
    public static final int JWT_REFRESH_INTERVAL = 55*60*1000;  //millisecond
    public static final int JWT_REFRESH_TTL = 12*60*60*1000;  //millisecond
	
	
	public static String getTokenAccess(Map<String, Object> claims){
		long nowMillis = System.currentTimeMillis();
		Date now = new Date( nowMillis);
		long expMillis = nowMillis + 60*1000;
		Date expiration = new Date( expMillis);
		
		JwtBuilder builder = Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
	            .setExpiration(expiration)
	            .signWith(SignatureAlgorithm.HS512, getKeyInstance()); //采用什么算法是可以自己选择的，不一定非要采用HS512
		
		return builder.compact();
	}
	
	
	public static Claims parseJWT(String jwt) throws Exception{
		Claims claims = Jwts.parser()
				.setSigningKey(DatatypeConverter.parseBase64Binary(JWT_SECRET))
				.parseClaimsJws(jwt).getBody();
	    return claims;
	}
	
	
	//该方法使用HS256算法和Secret:bankgl生成signKey
    private static Key getKeyInstance() {
        //We will sign our JavaWebToken with our ApiKey secret
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(JWT_SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return signingKey;
    }
    
    
    
    public static void main(String[] args) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("username", "liudongbo");
    	map.put("password", "123456");
		String jwt = getTokenAccess(map);
    	System.out.println(jwt);	
		
    	try {
			Claims parseJwt = parseJWT(jwt);
			System.out.println(parseJwt.get("username"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
