package Service;

import dataBase.DataBase;
import model.Room;
import model.User;
import java.util.List;

/**
 * 普通用户服务层
 */
public class UserService {
    /** 私有化数据库 */
    private static DataBase database_;
    /** 数据库初始化 */
    public UserService(DataBase database){
        database_=database;
    }

    /** 查询空闲房间 */
    public void findIsAvailableRooms(){
        //调用内部方法
        List<Room>availableRooms=database_.getAvailableRooms();
        //打印空闲房间
        for(Room room:availableRooms){
            System.out.println(room.roomToString());
        }
    }

    /** 预订房间 */
    public void bookRoom(int roomID,User user){
        //调用内部方法
        Room room=database_.getRoom(roomID);
        if(room!=null&&room.getRoomIsAvailable()){
           /*
            *计算预订价格
            */
            int value=room.getRoomPrice()*user.getUserDiscount()/10;
            user.setUserMoney(user.getUserMoney()-value);
            room.setRoomByUser(user);
            //修改房间状态
            room.setRoomIsAvailable(false);
            //修改房间响应操作
            if(database_.modifyRoom(room)){
                System.out.println("预订成功!");
                return;
            }
            System.out.println("预订失败！");
        }
        else{
            System.out.println("预订失败!房间不存在或房间已有人入住!");
        }
    }

    /** 退房 */
    public void checkOutRoom(int roomID,User user){
        //调用内部方法
        Room room=database_.getRoom(roomID);
        if(room!=null&&!room.getRoomIsAvailable()&&room.getRoomByUser().equals(user)){
            //设置退房后房间属性且响应
            room.setRoomByUser(null);
            room.setRoomIsAvailable(true);
            database_.modifyRoom(room);
            System.out.println("退房成功！");
        }else{
            System.out.println("退房失败!房间不存在或您没有入住该房间!");
        }
    }

    /** 查看预订的房间 */
    public void findMyRoom(User user){
        //调用内部方法
        List<Room>rooms=database_.getAllRooms();
        //遍历房间寻找自己预订房间
        for(Room room:rooms){
            if(room.getRoomByUser()!=null&&room.getRoomByUser().equals(user)){
                System.out.println(room.roomToString());
                return;
            }
        }
        System.out.println("您没有预订房间!");
    }

    /** 充值 */
    public void inputUserMoney(int money,User user){
        //增加余额
        user.setUserMoney(user.getUserMoney()+money);
        user.setUserInputMoney(user.getUserInputMoney()+money);
        //修改数据保存文件
        database_.saveFile();
        //充值超过3000变为会员享受9折
        if(user.getUserInputMoney()>=3000){
            System.out.println("温馨提示，您已升级为会员用户!");
            user.setUserDiscount(9);
        }
        System.out.println("充值成功！");
    }
}
