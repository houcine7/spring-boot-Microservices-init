package com.authentication.app.configuration;


import com.authentication.app.service.JwtServices;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtAuthFilter  {

    @Autowired
    JwtServices jwtServices;

    @Autowired
    UserDetailsService userDetailsService ;

//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain)
//            throws ServletException, IOException {
//
//        // get authorization header
//        String authorization =request.getHeader("Authorization") ;
//        String jwt;
//        String subject;
//
//        System.out.println(authorization);
//
//        if(authorization==null || !authorization.split(" ")[0].equals("Bearer")){
//            System.out.println("..... no auth header");
//            filterChain.doFilter(request,response);
//            return ;
//        }
//
//        jwt =authorization.split(" ")[1];
//
//        subject=jwtServices.getSubject(jwt) ;
//
//        System.out.println(subject) ;
//
//        if( subject!=null && SecurityContextHolder.getContext().getAuthentication()==null){
//
//            UserDetails userDetails =userDetailsService.loadUserByUsername(subject) ;
//
//            UsernamePasswordAuthenticationToken authToken =new UsernamePasswordAuthenticationToken(
//                    userDetails,
//                    null,
//                    userDetails.getAuthorities()
//            );
//
//            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//            SecurityContextHolder.getContext().setAuthentication(authToken);
//        }
//        filterChain.doFilter(request,response);
//    }
}