package com.henrique.login.springbooot.service;

import com.henrique.login.springbooot.Constants.ConstantsLogin;
import com.henrique.login.springbooot.model.LoginModel;
import com.henrique.login.springbooot.model.UserModel;
import com.henrique.login.springbooot.model.dto.LoginDTO;
import com.henrique.login.springbooot.model.dto.Response.UserResponseDTO;
import com.henrique.login.springbooot.repository.UserRepository;
import com.henrique.login.springbooot.util.Criptografia;
import com.henrique.login.springbooot.util.JwtUtil;
import com.henrique.login.springbooot.util.ReturnUserNotFound;
import com.henrique.login.springbooot.util.SearchDataBase;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    private Logger logger;
    private Criptografia criptografia;

    private SearchDataBase searchDataBase;

    private UserRepository userRepository;

    public LoginService(UserRepository userRepository, SearchDataBase searchDataBase) {
        this.userRepository = userRepository;
        this.searchDataBase = searchDataBase;
    }

    public ResponseEntity<?> verifyLoginAndGenrateToken(LoginModel login){
        UserModel user = searchDataBase.searchUserByEmailAndPassword(login);
        if(user == null){
            LoginDTO returnUserNotFound = ReturnUserNotFound.returnUserNotFoundLogin();
            return ResponseEntity.badRequest().body(returnUserNotFound);
        }
        String acessToken = JwtUtil.generateToken(user.getId(), user.getEmail());
        String refreshToken = JwtUtil.generateRefreshToken(user.getId());

        UserResponseDTO userResponseDTO = new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getDateOfBirth()
        );
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setAccess_token(acessToken);
        loginDTO.setRefresh_token(refreshToken);
        loginDTO.setToken_type("Bearer");
        loginDTO.setExpires_in(3600);
        loginDTO.setUser(userResponseDTO);
        loginDTO.setRequires_mfa(user.getEnterprise() != null ? user.getEnterprise().isRequiresMfa() : false);
        loginDTO.setMessage(ConstantsLogin.LOGIN_SUCCESS);
        loginDTO.setCode(200);

        return ResponseEntity.ok(loginDTO);
    }

}
