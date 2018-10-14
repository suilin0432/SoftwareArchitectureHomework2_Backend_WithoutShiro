package com.software.architecture.homework2.models.implementModels;

import com.software.architecture.homework2.common.DataBaseConst;
import com.software.architecture.homework2.common.EncryptionUtils;
import com.software.architecture.homework2.models.BaseModel;
import com.software.architecture.homework2.models.exceptionModels.EncryptException;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.NotNull;
import java.util.List;

@Document(collection = DataBaseConst.User_DATABASE_NAME)
public class User extends BaseModel {
    @Indexed
    @NotNull
    private String username;
    @NotNull
    private String password;

    private String salt;

    private List<Role> roleList;


    public void setPassword(String password) throws EncryptException {
        this.password = EncryptionUtils.userPasswordEncrypt("SHA1", password);
    }
    public String getPassword() {
        return password;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCredentialsSalt(){
        return this.username + this.salt;
    }
}
