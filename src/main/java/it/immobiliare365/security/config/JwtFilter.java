package it.immobiliare365.security.config;

import io.jsonwebtoken.ExpiredJwtException;
import it.immobiliare365.excpetions.UnauthorizedException;
import it.immobiliare365.security.Admin;
import it.immobiliare365.security.AdminService;
import it.immobiliare365.security.utils.JwtTools;
import it.immobiliare365.security.utils.RestAuthenticationEntryPoint;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTools jwt;
    private final AdminService adminService;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.equals("/admin/login") ||
                path.startsWith("/v3/api-docs") ||
                path.startsWith("/swagger-ui") ||
                path.startsWith("/swagger-resources") ||
                path.startsWith("/webjars") ||
                path.equals("/swagger-ui.html") ||
                path.equals("/auth/refresh");

    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = request.getHeader("Authorization");
            if (token == null || !token.startsWith("Bearer ")) {
                throw new UnauthorizedException("Token not found");
            }

            String email = jwt.decodeToken(token.substring(7));
            Admin admin = adminService.getAdminByEmail(email);


            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    admin,
                    null,
                    admin.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(auth);

            filterChain.doFilter(request, response);
        } catch (UnauthorizedException | ExpiredJwtException ex) {
            SecurityContextHolder.clearContext();

            InsufficientAuthenticationException authEx = new InsufficientAuthenticationException("Token issue: " + ex.getMessage(), ex);
            restAuthenticationEntryPoint.commence(request, response, authEx);
        }
    }
}
