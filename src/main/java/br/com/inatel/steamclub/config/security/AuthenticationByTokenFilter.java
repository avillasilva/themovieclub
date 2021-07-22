package br.com.inatel.steamclub.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

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

    public AuthenticationByTokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String token = collectToken(request);
        boolean valid = tokenService.isValid(token);
        System.out.println(valid);
                
        filterChain.doFilter(request, response);
    }

    private String collectToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }
}