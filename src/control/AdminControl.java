package control;
import Service.AdminService;
import model.User;

import java.util.Scanner;

/**
 * 管理员控制器
 */
public class AdminControl {
    /** 输入 */
    Scanner scan=new Scanner(System.in);
    /** 管理员服务层实体 */
    private static AdminService adminService_;
    /** 初始化 */
    public AdminControl(AdminService adminService){
        adminService_=adminService;
    }
    /** 用户菜单 */
    public void showAdminMenu(User user){
        System.out.println("**********欢迎使用旅馆小帮手**********");
        System.out.println("管理员账号："+user.getUserName());
        System.out.println("-----------1.查看所有房间-----------");
        System.out.println("-----------2.添加房间---------------");
        System.out.println("-----------3.删除房间---------------");
        System.out.println("-----------4.修改房间---------------");
        System.out.println("-----------5.查找房间---------------");
        System.out.println("-----------6.查找用户---------------");
        System.out.println("-----------7.修改用户---------------");
        System.out.println("-----------0.退出登录---------------");
        System.out.println("***********************************");
    }
    /** 查询全部房间 */
    public void findAllRooms(){
        //调用内部方法
        adminService_.getAllRooms();
    }
    /** 添加房间 */
    public void addRoom(){
        //添加房间的基础数据
        int ID;
        String type;
        int price;
        //交互
        System.out.print("请输入添加房间的编号:");
        ID=scan.nextInt();
        System.out.print("请输入添加房间的类型:");
        scan.nextLine();
        type=scan.nextLine();
        System.out.print("请输入添加房间的价格:");
        price=scan.nextInt();
        //调用内部方法
        adminService_.addRoom(ID,type,price);
    }
    /** 删除房间 */
    public void removeRoom(){
        //交互
        int ID;
        System.out.print("请输入删除房间的编号:");
        ID=scan.nextInt();
        //调用内部方法
        adminService_.removeRoom(ID);
    }
    /** 修改房间 */
    public void modifyRoom(){
        //修改房间信息变量
        int ID;
        String type;
        int price;
        boolean available;
        int userID;
        System.out.print("请输入修改房间的编号:");
        ID=scan.nextInt();
        System.out.print("请输入修改房间新的类型:");
        scan.nextLine();
        type=scan.nextLine();
        System.out.print("请输入修改房间新的价格:");
        price=scan.nextInt();
        System.out.print("请输入修改房间新是否空闲(true or false):");
        available=scan.nextBoolean();
        System.out.println("请输入房间新入住人的编号:");
        userID=scan.nextInt();
        //调用内部方法
        adminService_.modifyRoom(ID,type,price,available,adminService_.findUser(userID));
    }
    /** 寻找房间 */
    public void findRoom(){
        //交互
        int ID;
        System.out.print("请输入查询房间的编号:");
        ID=scan.nextInt();
        //调用内部方法
        adminService_.findRoom(ID);
    }
    /** 寻找用户 */
    public void findUser(){
        //交互
        int ID;
        System.out.print("请输入查询用户的编号:");
        ID=scan.nextInt();
        //调用内部方法
        User user=adminService_.findUser(ID);
        //结果响应
        if(user==null){
            System.out.println("未找到该用户!");
        }
        else{
            System.out.println("查找结果如下:");
            System.out.println(user.userToString());
        }
    }
    /** 修改用户 */
    public void modifyUser(){
        //修改用户信息变量
        int ID;
        String name;
        String password;
        boolean admin;
        int discount;
        int money;
        int inputMoney;
        System.out.print("请输入修改用户的编号:");
        ID=scan.nextInt();
        System.out.print("请输入修改用户新的姓名:");
        scan.nextLine();
        name=scan.nextLine();
        System.out.print("请输入修改用户新的密码:");
        password=scan.nextLine();
        System.out.print("请输入修改用户是否为管理员:(true or false)");
        admin=scan.nextBoolean();
        System.out.print("请输入修改用户新的折扣:");
        discount=scan.nextInt();
        System.out.print("请输入修改用户新的余额:");
        money=scan.nextInt();
        System.out.print("请输入修改用户新的累计充值:");
        inputMoney=scan.nextInt();
        //调用内部方法
        adminService_.modifyUser(ID,name,password,admin,discount,money,inputMoney);
    }
    /** 开启管理员控制器 */
    public void startUserControl(User user){
        while(true){
            //显示菜单
            showAdminMenu(user);
            int select;
            System.out.println("请输入操作数:>");
            //交互
            select=scan.nextInt();
            //响应操作
            switch(select){
                case 1:findAllRooms();break;
                case 2:addRoom();break;
                case 3:removeRoom();break;
                case 4:modifyRoom();break;
                case 5:findRoom();break;
                case 6:findUser();break;
                case 7:modifyUser();break;
                case 0:System.exit(0);break;
                default:
                    System.out.println("无该操作数!");break;
            }
        }

    }
}
