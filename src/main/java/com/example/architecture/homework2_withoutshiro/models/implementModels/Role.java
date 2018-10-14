package com.software.architecture.homework2.models.implementModels;

import com.software.architecture.homework2.common.DataBaseConst;
import com.software.architecture.homework2.models.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.index.Indexed;

import javax.validation.constraints.NotNull;
import java.util.List;

@Document(collection = DataBaseConst.Role_DATABASE_NAME)
public class Role extends BaseModel{

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
