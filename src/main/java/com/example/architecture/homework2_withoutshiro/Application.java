package com.example.architecture.homework2_withoutshiro;

import com.example.architecture.homework2_withoutshiro.models.OperationType;
import com.example.architecture.homework2_withoutshiro.models.ResourceType;
import com.example.architecture.homework2_withoutshiro.models.implementModels.Car;
import com.example.architecture.homework2_withoutshiro.models.implementModels.Commodity;
import com.example.architecture.homework2_withoutshiro.models.implementModels.Permission;
import com.example.architecture.homework2_withoutshiro.models.implementModels.Role;
import com.example.architecture.homework2_withoutshiro.repositories.CarRepository;
import com.example.architecture.homework2_withoutshiro.repositories.CommodityRepository;
import com.example.architecture.homework2_withoutshiro.repositories.PermissionRepository;
import com.example.architecture.homework2_withoutshiro.repositories.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static CarRepository carRepository;
    private static CommodityRepository commodityRepository;

    @Autowired
    Application(PermissionRepository permissionRepository,
                RoleRepository roleRepository,
                CarRepository carRepository,
                CommodityRepository commodityRepository){
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
        this.carRepository = carRepository;
        this.commodityRepository = commodityRepository;
    }
    public static void main(String[] args) throws InterruptedException {
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
    private static void init() throws InterruptedException {
        Optional<Permission> permissionFound = permissionRepository.findOneByName("COMMODITY_INSERT");
        if(!permissionFound.isPresent()){
            //权限包括 COMMODITY、CAR、ROLE的四种权限
            Permission permission0 = setPermission("COMMODITY_INSERT", ResourceType.COMMODITY, OperationType.INSERT);
            Permission permission1 = setPermission("COMMODITY_UPDATE", ResourceType.COMMODITY, OperationType.UPDATE);
            Permission permission2 = setPermission("COMMODITY_DELETE", ResourceType.COMMODITY, OperationType.DELETE);
            Permission permission3 = setPermission("COMMODITY_SEARCH", ResourceType.COMMODITY, OperationType.SEARCH);
            Permission permission4 = setPermission("CAR_INSERT", ResourceType.CAR, OperationType.INSERT);
            Permission permission5 = setPermission("CAR_UPDATE", ResourceType.CAR, OperationType.UPDATE);
            Permission permission6 = setPermission("CAR_DELETE", ResourceType.CAR, OperationType.DELETE);
            Permission permission7 = setPermission("CAR_SEARCH", ResourceType.CAR, OperationType.SEARCH);
            Permission permission8 = setPermission("USER_DELETE", ResourceType.USER, OperationType.DELETE);
            Permission permission9 = setPermission("USER_UPDATE", ResourceType.USER, OperationType.UPDATE);
            Permission permission10 = setPermission("USER_INSERT", ResourceType.USER, OperationType.INSERT);
            Permission permission11 = setPermission("USER_SEARCH", ResourceType.USER, OperationType.SEARCH);
            permission0 = permissionRepository.save(permission0);
            permission1 = permissionRepository.save(permission1);
            permission2 = permissionRepository.save(permission2);
            permission3 = permissionRepository.save(permission3);
            permission4 = permissionRepository.save(permission4);
            permission5 = permissionRepository.save(permission5);
            permission6 = permissionRepository.save(permission6);
            permission7 = permissionRepository.save(permission7);
            permission8 = permissionRepository.save(permission8);
            permission9 = permissionRepository.save(permission9);
            permission10 = permissionRepository.save(permission10);
            permission11 = permissionRepository.save(permission11);
            //角色分为 admin, coder, basicuser1, basicuser2, vip
            //admin: 所有权限 0-11
            //coder: 除了DELETE的所有权限 0,1,3,4,5,7,9,10,11
            //basicuser1: CAR的SEARCH权限和COMMODITY的SEARCH权限和CAR的UPDATE权限 3,5,7
            //basicuser2: CAR的SEARCH权限和COMMODITY的SEARCH权限和COMMODITY的UPDATE权限3,7,2
            //vip: CAR、COMMODITY、ROLE的SEARCH权限和UPDATE权限, COMMODITY和CAR 的INSERT权限，COMMODITY的删除权限 0-5,7,9,11
            Role admin = new Role();
            Role coder = new Role();
            Role basicuser1 = new Role();
            Role basicuser2 = new Role();
            Role vip = new Role();
            admin.setName("admin");
            coder.setName("coder");
            basicuser1.setName("basicuser1");
            basicuser2.setName("basicuser2");
            vip.setName("vip");
            vip.setDescription("CAR、COMMODITY、ROLE的SEARCH权限和UPDATE权限, COMMODITY和CAR 的INSERT权限，COMMODITY的删除权限");
            basicuser1.setDescription("CAR的SEARCH权限和COMMODITY的SEARCH权限和CAR的UPDATE权限");
            basicuser2.setDescription("CAR的SEARCH权限和COMMODITY的SEARCH权限和COMMODITY的UPDATE权限");
            coder.setDescription("除了DELETE的所有权限");
            admin.setDescription("所有权限");
            List<Permission> adminList = new ArrayList<Permission>();
            List<Permission> coderList = new ArrayList<Permission>();
            List<Permission> basicuser1List = new ArrayList<Permission>();
            List<Permission> basicuser2List = new ArrayList<Permission>();
            List<Permission> vipList = new ArrayList<Permission>();
            adminList.add(permission0);
            adminList.add(permission1);
            adminList.add(permission2);
            adminList.add(permission3);
            adminList.add(permission4);
            adminList.add(permission5);
            adminList.add(permission6);
            adminList.add(permission7);
            adminList.add(permission8);
            adminList.add(permission9);
            adminList.add(permission10);
            adminList.add(permission11);
            admin.setPermissions(adminList);
            coderList.add(permission0);
            coderList.add(permission1);
            coderList.add(permission3);
            coderList.add(permission4);
            coderList.add(permission5);
            coderList.add(permission7);
            coderList.add(permission9);
            coderList.add(permission10);
            coderList.add(permission11);
            coder.setPermissions(coderList);
            vipList.add(permission0);
            vipList.add(permission1);
            vipList.add(permission2);
            vipList.add(permission3);
            vipList.add(permission4);
            vipList.add(permission5);
            vipList.add(permission7);
            vipList.add(permission9);
            vipList.add(permission11);
            vip.setPermissions(vipList);
            basicuser1List.add(permission3);
            basicuser1List.add(permission5);
            basicuser1List.add(permission7);
            basicuser1.setPermissions(basicuser1List);
            basicuser2List.add(permission3);
            basicuser2List.add(permission2);
            basicuser2List.add(permission7);
            basicuser2.setPermissions(basicuser2List);
            roleRepository.save(admin);
            Thread.sleep(100);
            roleRepository.save(coder);
            Thread.sleep(100);
            roleRepository.save(vip);
            Thread.sleep(100);
            roleRepository.save(basicuser1);
            Thread.sleep(100);
            roleRepository.save(basicuser2);
        }
        if(!carRepository.findOneByName("Car1").isPresent()){
            Car car1 = new Car();
            car1.setName("Car1");
            car1.setPrice(new Long(1000000));
            car1.setDescription("Car1.");
            Car car2 = new Car();
            car2.setName("Car2");
            car2.setPrice(new Long(1000040));
            car2.setDescription("Car2.");
            Car car3 = new Car();
            car3.setName("Car3");
            car3.setPrice(new Long(1020000));
            car3.setDescription("Car3.");
            Car car4 = new Car();
            car4.setName("Car4");
            car4.setPrice(new Long(1100100));
            car4.setDescription("Car4.");
            Car car5 = new Car();
            car5.setName("Car5");
            car5.setPrice(new Long(3000000));
            car5.setDescription("Car5.");
            carRepository.save(car1);
            carRepository.save(car2);
            carRepository.save(car3);
            carRepository.save(car4);
            carRepository.save(car5);

            Commodity commodity1 = new Commodity();
            commodity1.setName("commodity1");
            commodity1.setPrice(new Long(100002321));
            commodity1.setDescription("commodity1.");
            Commodity commodity2 = new Commodity();
            commodity2.setName("commodity2");
            commodity2.setPrice(new Long(100012321));
            commodity2.setDescription("commodity2.");
            Commodity commodity3 = new Commodity();
            commodity3.setName("commodity3");
            commodity3.setPrice(new Long(230002321));
            commodity3.setDescription("commodity3.");
            Commodity commodity4 = new Commodity();
            commodity4.setName("commodity4");
            commodity4.setPrice(new Long(242002321));
            commodity4.setDescription("commodity4.");
            Commodity commodity5 = new Commodity();
            commodity5.setName("commodity5");
            commodity5.setPrice(new Long(359002321));
            commodity5.setDescription("commodity5.");
            commodityRepository.save(commodity1);
            commodityRepository.save(commodity2);
            commodityRepository.save(commodity3);
            commodityRepository.save(commodity4);
            commodityRepository.save(commodity5);
        }
    }

}
