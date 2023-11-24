package com.vikash.instBackend.service;

import com.vikash.instBackend.model.AuthenticationToken;
import com.vikash.instBackend.model.User;
import com.vikash.instBackend.repo.IAuthRepo;
import com.vikash.instBackend.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    IAuthRepo iAuthRepo;

    public void createToken(AuthenticationToken token) {
        iAuthRepo.save(token);

    }

    public boolean authenticate(String email,String tokenValue){
        // fin user fot the gicen email
        AuthenticationToken tokenObj= iAuthRepo.findByTokenValue(tokenValue);
        if(tokenObj != null) {
            return tokenObj.getUser().getUserEmail().equals(email);
        }
            return false;



    }

    public void deleteToken(String token) {
        AuthenticationToken authObj = iAuthRepo.findByTokenValue(token);
        iAuthRepo.delete(authObj);
    }
}
