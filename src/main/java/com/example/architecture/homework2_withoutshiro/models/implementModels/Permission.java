package com.example.architecture.homework2_withoutshiro.models.implementModels;

import com.example.architecture.homework2_withoutshiro.common.DataBaseConst;
import com.example.architecture.homework2_withoutshiro.models.BaseModel;
import com.example.architecture.homework2_withoutshiro.models.ResourceType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = DataBaseConst.Permission_DATABASE_NAME)
public class Permission extends BaseModel {
    private String name;

    private ResourceType resourceType;

    private String url;

    private String permission;

    private Long parentId;

    private String parentIds;

    private Boolean available;

    private List<Role> roleList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

}
