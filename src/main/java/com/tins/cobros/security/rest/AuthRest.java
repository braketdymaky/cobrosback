package com.tins.cobros.security.rest;

import com.tins.cobros.dto.Mensaje;
import com.tins.cobros.security.dto.JwtDto;
import com.tins.cobros.security.dto.LoginUser;
import com.tins.cobros.security.dto.NewUser;
import com.tins.cobros.security.enums.RolNombre;
import com.tins.cobros.security.jwt.JwtProvider;
import com.tins.cobros.security.model.Rol;
import com.tins.cobros.security.model.User;
import com.tins.cobros.security.service.RolService;
import com.tins.cobros.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthRest {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/new")
    public ResponseEntity<?> newUser(@RequestBody NewUser newUser){
            if(userService.existsUser(newUser.getName())){
                return new ResponseEntity<>(new Mensaje("User ya existe"), HttpStatus.BAD_REQUEST);
            }

        User user = new User(newUser.getName(), passwordEncoder.encode(newUser.getPassword()));
        Set<Rol> rols= new HashSet<>();
        rols.add(rolService.getRolByName("Admin").get());
        user.setRoles(rols);
        userService.saveUser(user);
        return new ResponseEntity<>(new Mensaje("Usuario Guardado"),HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login (@RequestBody LoginUser loginUser){
        if(!userService.existsUser(loginUser.getName())){
            return new ResponseEntity(new Mensaje("User no existe"), HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getName(),loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails= (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt,userDetails.getUsername(),userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDto,HttpStatus.OK);
    }
}
