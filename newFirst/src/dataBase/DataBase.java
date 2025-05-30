package dataBase;
import model.User;
import model.Room;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 模拟数据库+文件类
 * 用于辅助整个程序
 * 主要调用需要动文件
 * database的操作(或是依靠database的操作)
 * 增删查改
 */
public class DataBase {
    /** 内置管理员验证码 */
    private static final int verification_=12767;
    /** 存放房间和用户 */
    private List<Room>rooms;
    private List<User>users;
    /** 文件名 */
    private static final String DATA_FILE="myRoomManager.ser";

    /** 初始化加载文件到数据库即可 */
    public DataBase(){
        loadDataBaseFromFile();
    }
    /** 加载文件 */
    private void loadDataBaseFromFile(){
        //利用Object文件操作读取全部的对象
        try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(DATA_FILE))){
            rooms=(List<Room>) ois.readObject();
            users=(List<User>) ois.readObject();
        }//异常处理分配空间
        catch (FileNotFoundException e){
            rooms=new ArrayList<>();
            users=new ArrayList<>();
        }catch (IOException | ClassNotFoundException e){
            System.out.println("数据加载失败:"+e.getMessage());
            rooms=new ArrayList<>();
            users=new ArrayList<>();
        }
    }

    /** 保存文件 */
    public void saveFile(){
        //利用Object文件操作写入全部的对象
        try(ObjectOutputStream oos=new ObjectOutputStream((new FileOutputStream(DATA_FILE)))){
            oos.writeObject(rooms);
            oos.writeObject(users);
        }catch (IOException e){
            System.out.println("数据保存失败:"+e.getMessage());
        }
    }
    /** 登录用户 */
    public User isExistUser(String name,String password){
        //双层遍历查找用户名和密码相同的用户
        for(User user:users){
            if(user.getUserName().equals(name)){
                if(user.getUserPassword().equals(password)){
                    return user;
                }else{
                    System.out.println("密码错误!");
                    return null;
                }

            }
        }
        System.out.println("用户不存在！");
        return null;
    }
    /** 注册管理员 */
    public void registerAdmin(String name,String password,int verification){
        for(User user:users){
            //防止用户名重复
            if(user.getUserName().equals(name)){
                System.out.println("用户名已存在");
                return;
            }
        }
        //系统默认给予用户ID
        int newUserID=(users.isEmpty())?1:users.size()+1;
        //验证验证码是否正确
        boolean isAdmin= verification_ == verification;
        if(isAdmin){
            //创建管理员
            User newUser=new User(newUserID,name,password,true);
            users.add(newUser);
            System.out.println("注册成功!");
            //保存文件
            saveFile();
        }else{
            System.out.println("验证码错误，注册失败!");
        }
    }
    /** 注册用户 */
    public void registerUser(String name,String password){
        for(User user:users){
            //防止用户名重复
            if(user.getUserName().equals(name)){
                System.out.println("用户名已存在");
                return;
            }
        }
        //系统默认给予用户ID
        int newUserID=(users.isEmpty())?1:users.size()+1;
        //创建普通用户
        User newUser=new User(newUserID,name,password,false);
        users.add(newUser);
        System.out.println("注册成功!");
        //保存文件
        saveFile();
    }

    /** 查询用户 */
    public User getUser(int userID){
        //遍历用户ID
         for(User user:users){
             if(user.getUserID()==userID){
                 return user;
             }
         }return null;
    }
    /** 删除用户 */
    public boolean removeUser(int userID){
        //遍历用户ID
        User user=getUser(userID);
        if(user==null){
            System.out.println("该用户不存在");
            return false;
        }else{
            //找到该用户移除即可
            users.remove(user);
            //保存文件
            saveFile();
            System.out.println("删除成功!");
            return true;
        }
    }
    /** 修改用户 */
    public boolean modifyUser(User user){
        //遍历用户
        for(int i=0;i<users.size();i++){
            if(users.get(i).getUserID()==user.getUserID()){
                //调用内部方法
                users.set(i,user);
                //保存文件
                saveFile();
                return true;
            }
        }
        return false;
    }
    /** 获取全部用户 */
    public List<User> getAllUsers(){return new ArrayList<>(users);}
    /** 获取全部房间 */
    public List<Room> getAllRooms(){
        return new ArrayList<>(rooms);
    }
    /** 获取空闲房间 */
    public List<Room> getAvailableRooms(){
        List<Room>availableRooms=new ArrayList<>();
        for(Room room:rooms){
            //返回空闲的房间
            if(room.getRoomIsAvailable()){
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
    /** 查询房间 */
    public Room getRoom(int roomID){
        //遍历查询ID一样的房间
        for(Room room:rooms){
            if(room.getRoomID()==roomID){
                return room;
            }
        }return null;
    }
    /** 添加房间 */
    public void addRoom(int roomID,String roomType,int roomPrice){
        Room room=new Room(roomID,roomType,roomPrice);
        //调用内部方法
        rooms.add(room);
        //添加数据后重新写入文件
        saveFile();
        return;
    }
    /** 删除房间 */
    public boolean removeRoom(int roomID){
        Room room=getRoom(roomID);
        if(room==null){
            return false;
        }else{
            //找到该房间移除即可
            rooms.remove(room);
            //保存文件
            saveFile();
            return true;
        }
    }
    /** 修改房间 */
    public boolean modifyRoom(Room room){
        for(int i=0;i<rooms.size();i++){
            //找到房间
            if(rooms.get(i).getRoomID()==room.getRoomID()){
                //调用内部方法
                rooms.set(i,room);
                //保存文件
                saveFile();
                return true;
            }
        }
        return false;
    }
}


