package com.hybrid.internship.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hybrid.internship.dto.UserRequestDTO;
import com.hybrid.internship.exception.handler.ErrorMessage;
import com.hybrid.internship.model.MyUserDetails;
import com.hybrid.internship.model.User;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


import static com.hybrid.internship.Constants.EXPIRATION_TIME;
import static com.hybrid.internship.Constants.SECRET;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        setFilterProcessesUrl("/api/v1/login");
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            UserRequestDTO creds = new ObjectMapper()
                    .readValue(req.getInputStream(), UserRequestDTO.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {
        String token = JWT.create()
                .withSubject(((MyUserDetails) auth.getPrincipal()).getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));

        String body = ((MyUserDetails) auth.getPrincipal()).getEmail() + " " + token;

        res.getWriter().write(body);
        res.getWriter().flush();
    }
    protected void unsuccessfulAuthentication(javax.servlet.http.HttpServletRequest request,
                                              javax.servlet.http.HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
            ObjectMapper objectMapper = new ObjectMapper();
            ErrorMessage errorMessage = new ErrorMessage("Invalid combination of username and password", new Date());
            String returnVal = objectMapper.writeValueAsString(errorMessage);

            response.setStatus(400);
            response.setContentType(String.valueOf(MediaType.APPLICATION_JSON));
            response.getWriter().write(returnVal);
            response.getWriter().flush();
    }
}
