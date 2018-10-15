package com.example.architecture.homework2_withoutshiro.controllers;

import com.example.architecture.homework2_withoutshiro.common.Objects;
import com.example.architecture.homework2_withoutshiro.constants.UIConst;
import com.example.architecture.homework2_withoutshiro.models.exceptionModels.AuthException;
import com.example.architecture.homework2_withoutshiro.models.exceptionModels.EncryptException;
import com.example.architecture.homework2_withoutshiro.models.implementModels.Role;
import com.example.architecture.homework2_withoutshiro.models.implementModels.User;
import com.example.architecture.homework2_withoutshiro.models.requestModels.Data;
import com.example.architecture.homework2_withoutshiro.repositories.RoleRepository;
import com.example.architecture.homework2_withoutshiro.repositories.UserRepository;
import com.example.architecture.homework2_withoutshiro.services.AuthService;
import com.example.architecture.homework2_withoutshiro.services.BasicService;
import com.example.architecture.homework2_withoutshiro.services.env.Config;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(description = "基本操作")
@RestController
@RequestMapping(value = "/")
public class BasicController {
    Config config;
    BasicService basicService;
    AuthService authService;
    UserRepository userRepository;
    RoleRepository roleRepository;

    @Autowired
    public BasicController(Config config,
                           BasicService basicService,
                           AuthService authService,
                           UserRepository userRepository,
                           RoleRepository roleRepository){
        this.config = config;
        this.basicService = basicService;
        this.authService = authService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    //注册操作不涉及到权限问题
    @ApiOperation(value = "注册", notes = "")
    @RequestMapping(method = RequestMethod.POST, value = "register")
    public ResponseEntity userRegister(@RequestBody Data data, HttpSession session) throws AuthException, EncryptException {
        String username = data.username;
        String password = data.password;
        String confirm = data.confirm;
        //规定用;进行初始身份的约束
        String managementCode = data.managementCode;
        if(!Objects.isNotNull(username, password, confirm, managementCode)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        if(!basicService.isRightUsername(username)){
            throw new AuthException(1012, config.getExceptionsMap().get(1012));
        }
        if(!basicService.isRightPassword(password)){
            throw new AuthException(1014, config.getExceptionsMap().get(1014));
        }
        if(!password.equals(confirm)){
            throw new AuthException(1015, config.getExceptionsMap().get(1015));
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        List<Role> roleList = new ArrayList<Role>();
        String[] roles = managementCode.split(";");
        List<String> roleIdList = Arrays.asList(roles);
        roleList = roleRepository.findById(roleIdList).collect(Collectors.toList());
        user.setRoleList(roleList);

        session.setAttribute(UIConst.SESSION_USER_ID, user.getId());

        User saveUser = userRepository.save(user);
        return new ResponseEntity(saveUser, HttpStatus.OK);
    }

    //登陆也不涉及到权限
    @ApiOperation(value = "登陆", notes = "")
    @RequestMapping(method = RequestMethod.POST, value = "login")
    public ResponseEntity userLogin(@RequestBody Data data, HttpSession session) throws AuthException, EncryptException {
        String username = data.username;
        String password = data.password;
        if(!Objects.isNotNull(username, password)){
            throw new AuthException(1011, config.getExceptionsMap().get(1011));
        }
        if(!basicService.isRightUsername(username)){
            throw new AuthException(1012, config.getExceptionsMap().get(1012));
        }
        if(!basicService.isRightPassword(password)){
            throw new AuthException(1014, config.getExceptionsMap().get(1014));
        }
        Optional<User> userFound = userRepository.findOneByUsername(username);
        if(!userFound.isPresent()){
            throw new AuthException(1016, config.getExceptionsMap().get(1016));
        }
        User user = userFound.get();
        if(!(user.isRightPassword(password))){
            throw new AuthException(1017, config.getExceptionsMap().get(1017));
        }
        session.setAttribute(UIConst.SESSION_USER_ID, user.getId());
        return new ResponseEntity(user, HttpStatus.OK);
    }
    @ApiOperation(value = "用户登出", notes = "无参数传入,当前用户登出")
    @RequestMapping(value = "/signOut",method = RequestMethod.PUT)
    public ResponseEntity userSignOut(HttpSession session){
        session.invalidate();
        return new ResponseEntity(HttpStatus.OK);
    }

}
