package model;

import java.io.Serializable;

/**
 * 用户类
 */
public class User implements Serializable {
    /** 用户ID */
    private int userID_;
    /** 用户名 */
    private String userName_;
    /** 用户密码 */
    private String password_;
    /** 是否为管理员 */
    private boolean isAdmin_;
    /** 折扣 */
    private int discount_;
    /** 余额 */
    private int money_;
    /** 累计充值 */
    private int inputMoney_;
    /**
     * 有参构造
     */
    public User(int userID,String userName,String password,boolean isAdmin){
        this.userID_=userID;
        this.userName_=userName;
        this.password_=password;
        this.isAdmin_=isAdmin;
        discount_=10;
        money_=0;
        inputMoney_=0;
    }
    /**
     * Getter
     * Setter
     */
    public int getUserID(){
        return userID_;
    }
    public String getUserName(){
        return userName_;
    }
    public String getUserPassword(){
        return password_;
    }
    public boolean getUserIsAdmin(){
        return isAdmin_;
    }
    public int getUserDiscount(){
        return discount_;
    }
    public int getUserMoney(){
        return money_;
    }
    public int getUserInputMoney(){
        return inputMoney_;
    }

    public void setUserID(int userID){
        userID_=userID;
    }
    public void setUserName(String userName){
        userName_=userName;
    }
    public void setUserPassword(String password){
        password_=password;
    }
    public void setUserIsAdmin(boolean isAdmin){
        isAdmin_=isAdmin;
    }
    public void setUserDiscount(int discount){
        discount_=discount;
    }
    public void setUserMoney(int money){
        money_=money;
    }
    public void setUserInputMoney(int inputMoney){
        inputMoney_=inputMoney;
    }

    /**
     * toString
     */
    public String userToString(){
        String userInfo=(isAdmin_)?"管理员":"用户";
        return "用户编号:"+userID_+",用户名:"+userName_+",权限:"+userInfo
                +",折扣:"+discount_+",余额:"+money_+",累计充值:"+inputMoney_;
    }
}
