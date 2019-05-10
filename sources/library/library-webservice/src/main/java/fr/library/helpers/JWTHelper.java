package fr.library.helpers;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import fr.library.exceptions.JWTCheckingException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/**
 * JWT helper class to create JWT and parse tokens
 * @author Titouan
 *
 */
public abstract class JWTHelper {

	private final static String SECRET_KEY = "libraryOpenclassrooms";
	private static final Logger logger = Logger.getLogger(JWTHelper.class);
	/**
	 * Create a JWT token with HS256 algorithm
	 * @param id
	 * @param mail
	 * @return String
	 */
	public static String createJWT(Long id, String mail) {
		// Create expiration date +1 week
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.WEEK_OF_MONTH, 1);
		Date expirationDate = calendar.getTime();

		try {
			return Jwts.builder()
					.setSubject("Authentification")
					.setExpiration(expirationDate)
					.claim("userId", id)
					.claim("mail", mail)
					.signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes("UTF-8"))
					.compact();
		} catch (UnsupportedEncodingException e) {
			logger.error("JWT helper creation", e);
		}
		return null;
	}
	
	/**
	 * Parse jwt token and return a claim with users information and expiration date
	 * @param jwt
	 * @return
	 */
	public static Claims parseJWT(String jwt) {
		try {
			return Jwts.parser()
					.setSigningKey(SECRET_KEY.getBytes("UTF-8"))
					.parseClaimsJws(jwt).getBody();
		}catch(UnsupportedEncodingException e) {
			logger.error("JWT helper parser", e);
		}
		return null;
	}
	/**
	 * Check the expiration date of a jwt token
	 * @param jwt
	 * @return
	 * @throws JWTCheckingException
	 */
	public static Long checkJwt(String jwt) throws JWTCheckingException {
		Claims claim = JWTHelper.parseJWT(jwt);
		Date today = new Date();
		Long id = ((Integer) claim.get("userId")).longValue();
		if(claim.getExpiration().before(today)) {
			throw new JWTCheckingException("JWT expired");
		}else {
			return id;
		}
	}
}
