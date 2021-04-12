package com.student.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class StudController {
    @Autowired
    UserServiceImpl std;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JWTUserDetailsService userDetailsService;
   // @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/")
    public List<student> getAllStudents(){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getDetails().toString();
//System.out.println(authentication.getAuthorities().toString());
        return std.getAllstudents();

    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());


       final  UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String username = userDetails.getUsername();
        String userpwd = userDetails.getPassword();
        Set<String> roles = userDetails.getAuthorities().stream().map(r -> r.getAuthority())
                .collect(Collectors.toSet());
        UserDTO user = new UserDTO();
        user.setUsername(username);
        user.setPassword(userpwd);
        user.setRoles(roles);
        //final String token = JwtTokenUtil.GenerateToken(user);


        //final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
System.out.println(hasRole("ROLE_ADMIN"));
System.out.println(userDetails.getAuthorities().toString());
        return ResponseEntity.ok(new JwtResponse(token));
    }



    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            System.out.println(username + "  " + password);
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    boolean hasRole(String role) {
        // get security context from thread local
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null)

            return false;

        Authentication authentication = context.getAuthentication();

        if (authentication == null)
            return false;

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            System.out.println(auth.getAuthority().toString());
            if (role.equals(auth.getAuthority()))
                return true;
        }

        return false;
    }


    @GetMapping("/student/{name}")
    public List<student> getAllStudentsByName(@PathVariable String name) {

        return std.getAllStudentsByName(name);
    }
        @RequestMapping(value = "/register",  method = RequestMethod.POST )
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));

    }
      @RequestMapping(method = RequestMethod.POST, value ="/AddStudent")
void addstudent(@RequestBody student stud) {

        std.addupstudent(stud);
      }

}