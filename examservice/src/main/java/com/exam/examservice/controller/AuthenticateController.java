package com.exam.examservice.controller;

import com.exam.examservice.config.JwtUtil;
import com.exam.examservice.entity.JwtRequest;
import com.exam.examservice.entity.JwtResponse;
import com.exam.examservice.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    //generate token

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest){
        try{
            authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
        }catch(UsernameNotFoundException e){
            e.printStackTrace();
            //throw new Exception("User not Found");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ///

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token= this.jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }
    private void authenticate(String username, String password) throws Exception {
        try{

        }catch(DisabledException e){
            throw new Exception("User Disable" + e.getMessage());
        }catch(BadCredentialsException e){
            throw new Exception("Invalid Credential" + e.getMessage());
        }
    }
}
