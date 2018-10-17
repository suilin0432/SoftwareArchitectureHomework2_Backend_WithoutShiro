package com.example.architecture.homework2_withoutshiro.models.implementModels;

import com.example.architecture.homework2_withoutshiro.constants.DataBaseConst;
import com.example.architecture.homework2_withoutshiro.models.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = DataBaseConst.Commodity_DATABASE_NAME)
public class Commodity extends BaseModel {
    @Indexed(unique = true)
    @NotNull
    private String name;

    private String description;
    @NotNull
    private Long price;

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getPrice() {
        return price;
    }
}
