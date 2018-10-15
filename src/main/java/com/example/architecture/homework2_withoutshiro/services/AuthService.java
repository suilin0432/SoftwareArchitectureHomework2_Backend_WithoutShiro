package com.example.architecture.homework2_withoutshiro.services;

import com.example.architecture.homework2_withoutshiro.constants.UIConst;
import com.example.architecture.homework2_withoutshiro.models.exceptionModels.AuthException;
import com.example.architecture.homework2_withoutshiro.models.implementModels.Permission;
import com.example.architecture.homework2_withoutshiro.models.implementModels.Role;
import com.example.architecture.homework2_withoutshiro.models.implementModels.User;
import com.example.architecture.homework2_withoutshiro.repositories.PermissionRepository;
import com.example.architecture.homework2_withoutshiro.repositories.RoleRepository;
import com.example.architecture.homework2_withoutshiro.repositories.UserRepository;
import com.example.architecture.homework2_withoutshiro.services.env.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class AuthService {
    private PermissionRepository permissionRepository;
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private Config config;

    @Autowired
    public AuthService(PermissionRepository permissionRepository,
                       RoleRepository roleRepository,
                       UserRepository userRepository,
                       Config config){
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.config = config;
    }

    //判定一个用户是否有这个权限
    //1.我们要能够判断一个用户是否有一个权限
    //2.我们要判断一个用户是否有一个角色

    public boolean userHasAuth(User user, Permission permission){
        List<Role> roleList = user.getRoleList();
        List<Permission> permissionList = new ArrayList<Permission>();
        for (Role role:roleList){
            permissionList.addAll(role.getPermissions());
        }
        return permissionList.contains(permission); //返回是否有权限
    }

    public Optional<String> getUserIdFromSession(HttpSession session){
        return Optional.ofNullable((String)session.getAttribute(UIConst.SESSION_USER_ID));
    }

    public User getUserFromSession(HttpSession session) throws AuthException {
        Optional<String> userId = getUserIdFromSession(session);
        if(!userId.isPresent()){
            throw new AuthException(1018, config.getExceptionsMap().get(1018));
        }
        Optional<User> userFound = userRepository.findOneById(userId.get());
        if(!userFound.isPresent()){
            throw new AuthException(1016, config.getExceptionsMap().get(1016));
        }
        return userFound.get();
    }

}
