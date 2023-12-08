package com.senac.web.reserve.infra.security;

import com.senac.web.reserve.model.repository.CondominiumRepository;
import com.senac.web.reserve.model.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CondominiumRepository condominiumRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.recoverToken(request);
        if (token != null) {
            String name = tokenService.validateToken(token);
            UserDetails userDetails = condominiumRepository.findByName(name);
            if(userDetails != null) {
                UsernamePasswordAuthenticationToken authorization = new UsernamePasswordAuthenticationToken(name ,null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authorization);
            } else {
                System.out.println("O token é nulo");
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authenticateHeader = request.getHeader("Authorization");
        if(authenticateHeader == null) {
            return null;
        }
        return authenticateHeader.replace("Bearer ", "");
    }
}
