package com.exam.examservice.config;

import com.exam.examservice.service.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
       final String requestTokenHeader = httpServletRequest.getHeader("Authorization");
        System.out.println(requestTokenHeader);

        String username=null;
        String jwtToken =null;

        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")){
            //yes
           jwtToken = requestTokenHeader.substring(7);

           try{
               username = this.jwtUtil.extractUsername(jwtToken);
           }catch (ExpiredJwtException ex){
               ex.printStackTrace();
               System.out.println("jwtToken has Expired");
           }catch (Exception e){
               e.printStackTrace();
           }


        }
        else{
            System.out.println("Invalid Token, not start with bearer");
        }


        //validate the token
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails =this.userDetailsService.loadUserByUsername(username);

            if(this.jwtUtil.validateToken(jwtToken,userDetails)){
                //token is valid
                UsernamePasswordAuthenticationToken usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
            }
        }else{
            System.out.println("Token is not Valid");
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);


    }
}
