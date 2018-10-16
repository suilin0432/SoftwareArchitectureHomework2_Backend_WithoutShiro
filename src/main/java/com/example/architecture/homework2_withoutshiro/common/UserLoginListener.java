package com.example.architecture.homework2_withoutshiro.common;

public class UserLoginListener {
    private static UserLoginListener userLoginListener;
    private UserLoginListener(){

    }
    private static long userOnline = 0;
    public static void addUserOnline(){
        userOnline += 1;
    }
    public static void removeUserOnline(){
        userOnline -= 1;
    }
    public static long getUserOnline(){
        return userOnline;
    }
    public static UserLoginListener getUserLoginListener(){
        if(Objects.isNull(userLoginListener)){
            userLoginListener = new UserLoginListener();
        }
        return userLoginListener;
    }
}
