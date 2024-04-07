package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.UserDTO;
import com.devsuperior.dscommerce.entities.Role;
import com.devsuperior.dscommerce.entities.User;
import com.devsuperior.dscommerce.projection.UserDetailsProjection;
import com.devsuperior.dscommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);

        if (result.isEmpty()){
            throw new UsernameNotFoundException("User Not Found");
        }

        User user = new User();
        user.setEmail(username);
        user.setPassword(result.get(0).getPassword());
        for (UserDetailsProjection udp : result){
            user.addRole(new Role(udp.getRoleId(), udp.getAuthority()));
        }

        return user;
    }

    protected User getUserAuthenticated(){

        try {
            //Pega objeto dentro do contexto Spring security
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            //Obtem o claim
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String userName = jwtPrincipal.getClaimAsString("username");

            return repository.findByEmail(userName).get();
        }
        catch (Exception e){
            throw new UsernameNotFoundException("Email Not Found");
        }

    }

    @Transactional(readOnly = true)
    public UserDTO getLoggedUser(){
        User user = getUserAuthenticated();
        return new UserDTO(user);
    }
}
