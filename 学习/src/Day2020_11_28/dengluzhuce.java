package Day2020_11_28;

import java.sql.*;
import java.util.Scanner;

public class dengluzhuce {
    //    Scanner 接收处理
//        1. 执行程序显示  1.注册  2.登录
//        2. 输入1回车，进入到注册流程，提示用户输入用户名 回车
//        3. 提示用户输入密码  回车
//        4. 注册流程结束， 验证用户名和密码是否输入，输入的长度是否，验证用户名是否已注册   这时，保存用户输入的用户名和密码到数据库中
//        5. 提示用户注册已成功，请登录，输入2
//        6. 输入2回车，进入到登录流程，提示用户输入用户名 回车
//        7. 提示用户输入密码  回车
//        8. 登录流程结束，这时，通过用户名和密码验证，数据库中的数据是否一致
//        9. 如果不一致，提示用户，用户名或密码错误；如果一致，提示用户登录成功
//        1登录成功以后，系统提示输入1.查看自己的信息   2.查看所有用户的信息列表
    public static void main(String[] args) throws Exception {
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver);//加载驱动
        String url = "jdbc:mysql://localhost:3306/test";//数据库地址
        String user = "root";//使用人
        String password = "LIU915807";//密码
        Connection con = DriverManager.getConnection(url, user, password);//创建连接池
        Statement st = con.createStatement();//执行集
        Statement st1 = con.createStatement();
        Statement st2 = con.createStatement();
        Statement st3 = con.createStatement();
        Statement st4 = con.createStatement();
        Scanner sc = new Scanner(System.in);//键盘输入获取
        while (true) {
            System.out.println("回复 1.注册");
            System.out.println("回复 2.登录");
            System.out.println("请输入1或2");
            int s = sc.nextInt();

            if (s == 1) {
                System.out.println("请输入注册的用户名");
                String user1 = sc.next();
                System.out.println("请输入用户密码");
                String mm = sc.next();
                boolean se = false;
                ResultSet resultSet4 = st4.executeQuery("select user from users");
                while (resultSet4.next()) {
                    if (se = user1.equals(resultSet4.getString("user"))) {
                        break;
                    }
                }
                if (user1.isEmpty() || mm.isEmpty()) {
                    System.out.println("用户名或者密码不符合要求");
                } else if (user1.length() > 10 || mm.length() > 10) {
                    System.out.println("输入的密码或者用户名过长");
                } else if (se) {
                    System.out.println("该用户名已存在");
                } else {
                    st.execute("insert into users(user,mima) values('" + user1 + " ','" + mm + "')");
                    System.out.println("注册成功，请回复2 重新登录");
                    s = sc.nextInt();
                }
            }
            if (s == 2) {
                System.out.println("请输入用户名");
                String user2 = sc.next();
                System.out.println("请输入用户密码");
                String mm = sc.next();
                int count = 0;
                ResultSet resultSet = st.executeQuery("select user,mima from users");
                ResultSet resultSet1 = st1.executeQuery("select count(user) cou from users");
                int dbCount = 0;
                if (resultSet1.next()) {
                    dbCount = resultSet1.getInt("cou");
                }
                while (resultSet.next()) {//将resultSet中的数据取出来
                    count++;
                    if (user2.equals(resultSet.getString("user")) && mm.equals(resultSet.getString("mima")))//对比数据库中的数据有没有注册
                    {
                        System.out.println("登陆成功");
                        break;
                    } else if (count == dbCount)
                        System.out.println("用户名或密码错误");
                }
                System.out.println("回复 1.查看自身信息");
                System.out.println("回复 2.查看所有用户信息列表");
                System.out.println("请输入1或2");
                s = sc.nextInt();
                if (s == 1) {
                    ResultSet resultSet3 = st3.executeQuery("select id,user 用户名 from users where user='" + user2 + "'");
                    if (resultSet3.next()) {

                        System.out.println(resultSet3.getString("id"));
                        System.out.println(resultSet3.getString("用户名"));
                    }
                }
                if (s == 2) {
                    ResultSet resultSet2 = st2.executeQuery("select user 用户名 from users");
                    while (resultSet2.next()) {
                        System.out.println(resultSet2.getString("用户名"));
                    }
                }
                resultSet.close();
                resultSet1.close();
            }
            st.close();
            st1.close();
            con.close();
        }
    }
}











