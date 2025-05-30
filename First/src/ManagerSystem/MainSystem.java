package ManagerSystem;

import dataBase.DataBase;
import model.User;
import control.UserControl;
import control.AdminControl;

import java.util.Scanner;
import Service.UserService;
import Service.AdminService;

/** 负责启动整个程序--调用各处方法 */
public class MainSystem {
    /** 输入 */
    Scanner scan=new Scanner(System.in);
    /** 数据库 */
    private static DataBase database_;
    /** 普通用户控制器 */
    private static UserControl userControl_;
    /** 管理员控制器 */
    private static AdminControl adminControl_;
    /** 初始化 */
    public MainSystem(){
        database_=new DataBase();
        UserService userService_ = new UserService(database_);
        AdminService adminService_ = new AdminService(database_);
        userControl_=new UserControl(userService_);
        adminControl_=new AdminControl(adminService_);
    }
    /** 开启程序 */
    public void startAll(){
        while(true){
            //显示菜单
            showMenu();
            //选择操作
            int select;
            System.out.print("请输入操作数:>");
            select=scan.nextInt();
            //名字
            String name;
            //密码
            String password;
            //内部操作数
            int innerSelect;
            //验证码默认为0
            int verification = 0;
            switch(select){
                case 1:
                    //交互
                    System.out.print("请输入账号:>");
                    scan.nextLine();
                    name=scan.nextLine();
                    System.out.print("请输入密码:>");
                    password=scan.nextLine();
                    //寻找用户
                    User user = database_.isExistUser(name, password);
                    if(user==null){
                        break;
                    }
                    if(user.getUserIsAdmin()){
                        //开启管理员控制器
                        adminControl_.startUserControl(user);
                    }
                    else{
                        //开启普通用户控制器
                        userControl_.startUserControl(user);
                    }
                    break;
                case 2:
                    //内层交互
                    System.out.println("请输入注册的用户:>");
                    System.out.println("1---普通用户");
                    System.out.println("2---管理员用户");
                    //内层操作数
                    innerSelect=scan.nextInt();
                    //交互
                    System.out.print("请输入账号:>");
                    scan.nextLine();
                    name=scan.nextLine();
                    System.out.print("请输入密码:>");
                    password=scan.nextLine();
                    if(innerSelect==2){
                        //管理员需要验证码
                        System.out.print("请输入验证码:>");
                        verification=scan.nextInt();
                        //调用内部方法
                        database_.registerAdmin(name,password,verification);break;
                    }else
                        //调用内部方法
                        database_.registerUser(name,password);break;
                case 0:
                    //正常退出
                    System.out.println("欢迎下次使用!");
                    System.exit(0);break;
                default:
                    //无此操作数
                    System.out.println("没有该操作数!");break;
            }
        }
    }
    /** 页面菜单 */
    public void showMenu(){
        System.out.println("**********欢迎使用旅馆小帮手**********");
        System.out.println("-----------1.登录------------------");
        System.out.println("-----------2.注册------------------");
        System.out.println("-----------0.退出------------------");
        System.out.println("***********************************");
    }

}
