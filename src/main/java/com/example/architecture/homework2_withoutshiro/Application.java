package com.example.architecture.homework2_withoutshiro;

import com.example.architecture.homework2_withoutshiro.models.OperationType;
import com.example.architecture.homework2_withoutshiro.models.ResourceType;
import com.example.architecture.homework2_withoutshiro.models.implementModels.Permission;
import com.example.architecture.homework2_withoutshiro.models.implementModels.Role;
import com.example.architecture.homework2_withoutshiro.repositories.PermissionRepository;
import com.example.architecture.homework2_withoutshiro.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The entry point of the application.
 */

@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
@EnableSwagger2
public class Application {
    private static PermissionRepository permissionRepository;
    private static RoleRepository roleRepository;
    @Autowired
    Application(PermissionRepository permissionRepository,
                RoleRepository roleRepository){
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        init();//init 初始环境
    }
    private static Permission setPermission(String name, ResourceType resourceType, OperationType operationType){
        Permission permission = new Permission();
        permission.setResourceType(resourceType);
        permission.setOperationType(operationType);
        permission.setName(name);
        return permission;
    }
    private static void init() {
        Optional<Permission> permissionFound = permissionRepository.findOneByName("COMMODITY_INSERT");
        if(!permissionFound.isPresent()){
            //权限包括 COMMODITY、CAR、ROLE的四种权限
            Permission permission0 = setPermission("COMMODITY_INSERT", ResourceType.COMMODITY, OperationType.INSERT);
            Permission permission1 = setPermission("COMMODITY_UPDATE", ResourceType.COMMODITY, OperationType.UPDATE);
            Permission permission2 = setPermission("COMMODITY_DELETE", ResourceType.COMMODITY, OperationType.DELETE);
            Permission permission3 = setPermission("COMMODITY_SEARCH", ResourceType.COMMODITY, OperationType.SEARCH);
            Permission permission4 = setPermission("COMMODITY_INSERT", ResourceType.COMMODITY, OperationType.INSERT);
            Permission permission5 = setPermission("COMMODITY_UPDATE", ResourceType.COMMODITY, OperationType.UPDATE);
            Permission permission6 = setPermission("COMMODITY_DELETE", ResourceType.COMMODITY, OperationType.DELETE);
            Permission permission7 = setPermission("COMMODITY_SEARCH", ResourceType.COMMODITY, OperationType.SEARCH);
            Permission permission8 = setPermission("COMMODITY_DELETE", ResourceType.COMMODITY, OperationType.DELETE);
            Permission permission9 = setPermission("COMMODITY_UPDATE", ResourceType.COMMODITY, OperationType.UPDATE);
            Permission permission10 = setPermission("COMMODITY_INSERT", ResourceType.COMMODITY, OperationType.INSERT);
            Permission permission11 = setPermission("COMMODITY_SEARCH", ResourceType.COMMODITY, OperationType.SEARCH);
        }
        Optional<Role> roleFound = roleRepository.findOneByName("admin");
        if(!roleFound.isPresent()){
            //角色分为 admin, coder, basicuser1, basicuser2, vip
            //admin: 所有权限
            //coder: 除了DELETE的所有权限
            //basicuser1: CAR的SEARCH权限和COMMODITY的SEARCH权限和CAR的UPDATE权限
            //basicuser2: CAR的SEARCH权限和COMMODITY的SEARCH权限和COMMODITY的UPDATE权限
            //vip: CAR、COMMODITY、ROLE的SEARCH权限和UPDATE权限, COMMODITY和CAR 的INSERT权限，COMMODITY的删除权限
            Role admin = new Role();
            Role coder = new Role();
            Role basicuser1 = new Role();
            Role basicuser2 = new Role();
            Role vip = new Role();
        }
    }
}
