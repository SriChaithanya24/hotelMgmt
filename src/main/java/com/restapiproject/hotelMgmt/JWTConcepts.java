package com.restapiproject.hotelMgmt;

public class JWTConcepts {
	/*
	 * JWT -> JSON Web Token
	 * -> compact token -> verify : who the user is (identity)
	 *                            : what the user is allowed to do (authorization)
	 *                            : whether the token is valid ans unmodified
	 * structure : 3 parts : xxxxx.yyyyy.zzzzz
	 * Header : what algorithm was used to sign it
	 * Payload : usernames, roles, expiry
	 * Signature :  ensures token is not tampered
	 *
	 * Why?
	 *
	 * -> REst APis
	 * -> Secure Rest APis
	 * -> EAsy authorization - inside some roles - admin, user
	 * -> adding security to Rest APis
	 *
	 * User Register -> end point -> POST auth/register -> username, pwd, role
	 *               -> encode the password - BCryptPasswordEncoder
	 *               -> Store in MySQL using jdbcTemplate
	 *               -> user is registeres
	 *               -> Created user who can authenticate now
	 * User login -> end point -> POST auth/login - name, pwd
	 *            -> compare raw pwd with encoded pwd
	 *            -> If valid -> generate JWT Token
	 *            -> username , role, expiry taken, secret key
	 *            -> token now -> user's identity
	 * CLinet sends token in request -> GET /api/hotels -> fetch this
	 *             -> send authorisation header also
	 * JWT filter -> extract token from authorisation
	 *           -> validate token
	 * Security COnfig -> admin -> POST, PUT, Delete
	 *                 -> user -> to do only GET
	 *                 -> roles -> what kind of access is specified
	 *                 -> Controller runs - execute allowed API           
	 *           
	 * ROLE_admin, ROLE_user
	 *
	 * JWT -> once you login -> you JWT -> authetication
	 *     -> request -> then authorizes based on role
	 *                                        
	 */
}
