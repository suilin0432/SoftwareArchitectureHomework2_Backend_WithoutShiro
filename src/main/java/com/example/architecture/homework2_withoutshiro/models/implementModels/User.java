package com.example.architecture.homework2_withoutshiro.models.implementModels;

import com.example.architecture.homework2_withoutshiro.constants.DataBaseConst;
import com.example.architecture.homework2_withoutshiro.common.EncryptionUtils;
import com.example.architecture.homework2_withoutshiro.models.BaseModel;
import com.example.architecture.homework2_withoutshiro.models.exceptionModels.EncryptException;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.NotNull;
import java.util.List;

@Document(collection = DataBaseConst.User_DATABASE_NAME)
public class User extends BaseModel {

    @Indexed(unique = true)
    @NotNull
    private String username;
    @NotNull
    private String password;

    private List<Role> roleList;


    public void setPassword(String password) throws EncryptException {
        this.password = EncryptionUtils.userPasswordEncrypt("SHA1", password);
    }
    public boolean isRightPassword(String password) throws EncryptException {
        return this.password.equals(EncryptionUtils.userPasswordEncrypt("SHA1", password));
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

}
