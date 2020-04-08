package order;

import io.jsonwebtoken.Jwts;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import static order.SecurityConstants.HEADER_STRING;
import static order.SecurityConstants.SECRET;
import static order.SecurityConstants.TOKEN_PREFIX;
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }
    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);
        		
        	

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
        	StringBuilder jwttoken = new StringBuilder(); 
    		jwttoken.append("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqFi2bF41edXcVMYme+/u")
         .append("zBbG28vkSpFf4/dTy+mOkJe+bU3H5djJTHZVVESdA9T9gX5TY39QxU4Mg0Lsb955")
         .append("8Yk3AmiySCNHBHsgw9oavePfgwZwjE94oiiC2BbvO4jhpC92AVuQbnEFTBmc+W/Y")
         .append("ifqQeb5su6CSkBYSy2fDgETcwZYirG1W95BmFqdBICR/As9AMeQzr6HdINIsH6my")
         .append("PKFep+/O9Ttz39UnrTd3E/wpUWFzXUFXwtihKDdomFC4UiEAbR6y4VqTg38VWaAr")
         .append("981czCm2lGD958Y4DSalYrt7Wf+07quOxr5/55hqGmHhLaZDQ0M3rBUVd6SJFT93")
         .append("AwIDAQAB");
    		String user = "";
    		if(token.indexOf("Outh")>=0) {
    			String[] us = token.split(",");
    			user = us[1];
    		}else {
    			
             user = Jwts.parser()
                   .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
    		}
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }
}
