package in.nirajansangraula.expensetrackerapi.Security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.security.core.context.SecurityContextHolder;
import in.nirajansangraula.expensetrackerapi.util.JwtTokenUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

public class JwtRequestFilter implements Filter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailService userDetailService;

    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        final String authorizationHeader = request.getHeader("Authorization");
        String jwttoken = null;
        String username = null;

        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            jwttoken = authorizationHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwttoken);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Unable to get JWT Token");
            } catch (TokenExpiredException e) {
                throw new RuntimeException("JWT Token has expired");
            }
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
           UserDetails userDetails =  userDetailService.loadUserByUsername(username); 
            if(jwtTokenUtil.validateToken(jwttoken, userDetails))
            {
                UsernamePasswordAuthenticationToken authToken = 
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            else
            {
                throw new RuntimeException("JWT Token is not valid");
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
