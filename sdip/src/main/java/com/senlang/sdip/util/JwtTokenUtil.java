package com.senlang.sdip.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -3301605591108950415L;

	private static final String CLAIM_KEY_USERNAME = "sub";
	private static final String CLAIM_KEY_CREATED = "created";

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String getOpenidFromToken(String token) {
		String openid;
		try {
			final Claims claims = getClaimsFromToken(token);
			openid = claims.getSubject();
		} catch (Exception e) {
			openid = null;
		}
		return openid;
	}

	public long getOrgIdFromToken(String token) {
		long oid;
		try {
			final Claims claims = getClaimsFromToken(token);
			oid = Convert.of(claims.get("org")).tryToLong().getValueOrDefault(0L);
		} catch (Exception e) {
			oid = 0;
		}
		return oid;
	}

	public Date getCreatedDateFromToken(String token) {
		Date created;
		try {
			final Claims claims = getClaimsFromToken(token);
			created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
		} catch (Exception e) {
			created = null;
		}
		return created;
	}

	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}

	public Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration==null || expiration.before(new Date());
	}

	// private static Boolean isCreatedBeforeLastPasswordReset(Date created,
	// Date lastPasswordReset) {
	// return (lastPasswordReset != null && created.before(lastPasswordReset));
	// }

	public String generateToken(String openid,long orgId) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, openid);
		claims.put("org", orgId);
		claims.put(CLAIM_KEY_CREATED, new Date());
		return generateToken(claims);
	}
	
	public String generateToken(String openid) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, openid);
		//claims.put("org", orgId);
		claims.put(CLAIM_KEY_CREATED, new Date());
		return generateToken(claims);
	}

	String generateToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Boolean canTokenBeRefreshed(String token /* Date lastPasswordReset */) {
		// final Date created = getCreatedDateFromToken(token, secret);
		return /*
				 * !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
				 * &&
				 */ !isTokenExpired(token);
	}

	public String refreshToken(String token) {
		String refreshedToken;
		try {
			final Claims claims = getClaimsFromToken(token);
			claims.put(CLAIM_KEY_CREATED, new Date());
			refreshedToken = generateToken(claims);
		} catch (Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}
}
