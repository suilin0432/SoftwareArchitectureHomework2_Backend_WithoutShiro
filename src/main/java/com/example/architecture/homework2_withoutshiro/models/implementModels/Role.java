package com.example.architecture.homework2_withoutshiro.models.implementModels;


import com.example.architecture.homework2_withoutshiro.constants.DataBaseConst;
import com.example.architecture.homework2_withoutshiro.models.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Document(collection = DataBaseConst.Role_DATABASE_NAME)
public class Role extends BaseModel {

    @Indexed
    @NotNull
    private String name;

    private String description;

    private List<Permission> permissionList;

//    private List<User> userList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<Permission> getPermissions() {
        return permissionList;
    }

    public void setPermissions(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

//    public List<User> getUserInfos() {
//        return userList;
//    }
//
//    public void setUserInfos(List<User> userList) {
//        this.userList = userList;
//    }
}
