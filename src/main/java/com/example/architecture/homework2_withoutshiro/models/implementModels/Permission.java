package com.example.architecture.homework2_withoutshiro.models.implementModels;

import com.example.architecture.homework2_withoutshiro.constants.DataBaseConst;
import com.example.architecture.homework2_withoutshiro.models.BaseModel;
import com.example.architecture.homework2_withoutshiro.models.OperationType;
import com.example.architecture.homework2_withoutshiro.models.ResourceType;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = DataBaseConst.Permission_DATABASE_NAME)
public class Permission extends BaseModel {
    @Indexed
    @NotNull
    private String name;

    private ResourceType resourceType;

    private OperationType operationType;

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

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }
}
