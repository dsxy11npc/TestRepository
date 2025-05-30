package control;
import Service.UserService;
import model.User;
import model.Room;

import java.util.Scanner;
/**
 * 普通用户控制器
 */
public class UserControl {
    /** 输入 */
    Scanner scan=new Scanner(System.in);
    /** 用户服务层实体 */
    private static UserService userService_;
    /** 初始化 */
    public UserControl(UserService userService){userService_=userService;}
    /** 用户菜单 */
    public void showMenuUser(User user){
        System.out.println("**********欢迎使用旅馆小帮手**********");
        System.out.println("顾客："+user.getUserName()+"当前余额:"+user.getUserMoney()+"折扣:"+user.getUserDiscount());
        System.out.println("-----------1.查看空闲房间-----------");
        System.out.println("-----------2.开房------------------");
        System.out.println("-----------3.退房------------------");
        System.out.println("-----------4.查看我的房间------------");
        System.out.println("-----------5.充值-------------------");
        System.out.println("-----------0.退出登录---------------");
        System.out.println("***********************************");
    }
    /** 查询空闲房间 */
    public void findAvailableRooms(){
        System.out.println("空闲房间如下:");
        //调用内部方法
        userService_.findIsAvailableRooms();
    }
    /** 预订房间 */
    public void bookRoom(User user){
        //交互
        int ID;
        System.out.print("请输入您要预订房间的房间编号:");
        ID=scan.nextInt();
        //调用内部方法
        userService_.bookRoom(ID,user);
    }
    /** 退房 */
    public void checkOutRoom(User user){
        //交互
        int ID;
        System.out.print("请输入您要退房的房间编号:");
        ID=scan.nextInt();
        //调用内部方法
        userService_.checkOutRoom(ID,user);
    }
    /** 查询预订房间 */
    public void findMyRoom(User user){
        //调用内部方法
        userService_.findMyRoom(user);
    }
    /** 充值 */
    public void inputMoney(User user){
        //交互
        int money;
        System.out.print("请输入充值的金额: ");
        money=scan.nextInt();
        //调用内部方法
        userService_.inputUserMoney(money,user);
    }
    /** 开启用户控制器 */
    public void startUserControl(User user){
        while(true){
            //显示菜单
            showMenuUser(user);
            //交互
            int select;
            System.out.println("请输入操作数:>");
            select=scan.nextInt();
            //响应操作
            switch(select){
                case 1:findAvailableRooms();break;
                case 2:bookRoom(user);break;
                case 3:checkOutRoom(user);break;
                case 4:findMyRoom(user);break;
                case 5:inputMoney(user);break;
                case 0:System.exit(0);break;
                default:
                    System.out.println("无该操作数!");break;
            }
        }
    }
}
