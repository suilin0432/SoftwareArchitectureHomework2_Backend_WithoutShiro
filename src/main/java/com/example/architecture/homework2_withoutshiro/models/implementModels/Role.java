package com.example.architecture.homework2_withoutshiro.models.implementModels;


import com.example.architecture.homework2_withoutshiro.common.DataBaseConst;
import com.example.architecture.homework2_withoutshiro.models.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Document(collection = DataBaseConst.Role_DATABASE_NAME)
public class Role extends BaseModel {

    @Indexed
    @NotNull
    private String role;

    private String description;

    private Boolean available;

    private List<Permission> permissionList;

    private List<User> userList;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<Permission> getPermissions() {
        return permissionList;
    }

    public void setPermissions(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public List<User> getUserInfos() {
        return userList;
    }

    public void setUserInfos(List<User> userList) {
        this.userList = userList;
    }
}
