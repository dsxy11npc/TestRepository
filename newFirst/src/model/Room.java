package model;

import java.io.Serializable;

/**
 * 房间类
 */
public class Room implements Serializable {
    /** 房间ID */
    private int roomID_;
    /** 房间类型 */
    private String roomType_;
    /** 房间价格 */
    private int price_;
    /** 房间是否空闲 */
    private boolean isAvailable_;
    /** 房间主人 */
    private User byUser_;
    /**
     * 有参构造
     */
    public Room(int roomID,String roomType,int price){
        roomID_=roomID;
        roomType_=roomType;
        price_=price;
        isAvailable_=true;
        byUser_=null;
    }

    /**
     * Getter
     * Setter
     */
    public int getRoomID(){
        return roomID_;
    }
    public String getRoomType(){
        return roomType_;
    }
    public int getRoomPrice(){
        return price_;
    }
    public boolean getRoomIsAvailable(){
        return isAvailable_;
    }
    public User getRoomByUser(){
        return byUser_;
    }
    public void setRoomID(int roomID){
        roomID_=roomID;
    }
    public void setRoomType(String roomType){
        roomType_=roomType;
    }
    public void setRoomPrice(int price){
        price_=price;
    }
    public void setRoomIsAvailable(boolean isAvailable){
        isAvailable_=isAvailable;
    }
    public void setRoomByUser(User byUser){
        byUser_=byUser;
    }
    /**
     * toString
     */
    public String roomToString(){
        String roomInfo=(byUser_!=null)?",入住者"+byUser_.getUserName():"";
        return "房间编号:"+roomID_+",房间类型:"+roomType_+",房间价格:"+price_
                +"是否空闲："+isAvailable_+roomInfo;
    }
}
