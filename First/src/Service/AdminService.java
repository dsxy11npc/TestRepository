package Service;
import dataBase.DataBase;
import model.Room;
import model.User;
import java.util.List;

/**
 * 管理员服务层
 */
public class AdminService {
    /** 私有化数据库 */
    private static DataBase database_;
    /** 数据库初始化 */
    public AdminService(DataBase database){
        database_=database;
    }
    /** 获取全部房间 */
    public void getAllRooms(){
        //调用内部方法
        List<Room>rooms=database_.getAllRooms();
        System.out.println("所有房间信息如下:");
        //遍历所有房间后打印
        for(Room room:rooms){
            System.out.println(room.roomToString());
        }
    }
    /** 查找房间 */
    public void findRoom(int roomID){
        //调用内部方法
        Room room=database_.getRoom(roomID);
        //处理respond
        if(room==null){
            System.out.println("未找到该房间!");
        }else{
            System.out.println("查找结果如下:");
            System.out.println(room.roomToString());
        }

    }
    /** 添加房间 */
    public void addRoom(int roomID,String roomType,int roomPrice){
        //调用内部方法
        database_.addRoom(roomID,roomType,roomPrice);
        //处理respond
        System.out.println("添加成功!");
    }
    /** 删除房间 */
    public void removeRoom(int roomID){
        /*
         * 调用内部方法
         * 处理respond
         */
        if(database_.removeRoom(roomID)){
            System.out.println("删除成功!");
        }else{
            System.out.println("该房间不存在");
        }

    }
    /** 修改房间 */
    public void modifyRoom(int roomID,String roomType,int roomPrice
            ,boolean isAvailable,User user){
        //创建新房间
        Room room=new Room(roomID,roomType,roomPrice);
        room.setRoomIsAvailable(isAvailable);
        room.setRoomByUser(user);
        /*
         * 调用内部方法
         * 处理respond
         */
        if(database_.modifyRoom(room)){
            System.out.println("修改成功!");
        }else{
            System.out.println("未找到该房间!");
        }
    }
    /** 修改用户信息 */
    public void modifyUser(int userID,String userName,String password
            ,boolean isAdmin,int discount,int money,int inputMoney){
        //创建新用户
        User user=new User(userID,userName,password,isAdmin);
        user.setUserDiscount(discount);
        user.setUserMoney(money);
        user.setUserInputMoney(inputMoney);
        /*
         * 调用内部方法
         * 处理respond
         */
        if(database_.modifyUser(user)){
            System.out.println("修改成功!");
        }else{
            System.out.println("未找到该用户!");
        }
    }

    /** 查看用户 */
    public User findUser(int userID){
        //调用内部方法
        return database_.getUser(userID);
    }

}
