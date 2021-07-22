package br.com.inatel.steamclub.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.inatel.steamclub.model.User;
import br.com.inatel.steamclub.repository.UserRepository;

// External API Communication:
//  - Steam

// Relational Database Integration:
//  - Postgresql

// Automated tests:
// - mockito
// - cucumber

// logging
// No relational Database Integration (use to store the logging)
// cache
// authentication and authorization

public class AuthenticationByTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserRepository userRepository;

    public AuthenticationByTokenFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String token = collectToken(request);
        
        if (tokenService.isValid(token)) {
        	authenticateClient(token);
        }
        
        filterChain.doFilter(request, response);
    }

    private String collectToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }
    
    public void authenticateClient(String token) {
    	Long userId = tokenService.getUserId(token);
    	User user = userRepository.findById(userId).get();
    	UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    	SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}