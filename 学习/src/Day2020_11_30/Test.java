package Day2020_11_30;

import javax.swing.plaf.BorderUIResource;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test {
   public String name;
   public int age;
  private String sex;

    public Test() {
        System.out.println("kong");
    }
    public Test(String name) {
        System.out.println(name);

    }
    private Test(int age) {
        System.out.println(age);
    }

    public  double  add( int a,double b){
           return a+b;}

    public  void  ad( int a){
        System.out.println(a);
        return;}

  private void  a( String name){
      System.out.println(name);
        return ;}







    public static void main(String[] args) throws Exception {
//获取Class  对象
//       Test cle= new Test();
//       Class cla1=cle.getClass();//通过对象名.getClass 获取Class对象
//       Class cla2=Test.class;//通过类名.class获取class对象
//       Class cla3 = Class.forName("Day2020_11_30.Test");//通过Class类的静态方法，Class.forName("包名+类名")获取class 文件
//获取构造函数
//        Constructor constructor = cla3.getConstructor();//通过class对象获取公共空参构造函数
//        System.out.println( constructor);
//        Constructor  constructor1=cla3.getConstructor( String.class);//通过class对象获取公共有参构造函数
//        System.out.println( constructor1);
//        Constructor constructor2 =cla3.getDeclaredConstructor(int.class);//通过class对象获取私有有参构造函数
//        System.out.println( constructor2);
//        Constructor[] constructor3 =cla3.getConstructors();//通过class对象获取所有构造函数
//       for(Constructor constructor4: constructor3){
//           System.out.println( constructor4);}
// 根据构造函数创建对象
//        Test test=(Test)constructor1.newInstance("刘俊先");
//        System.out.println(test);
//        Test test2=(Test)constructor2.newInstance(25);
//        System.out.println(test2);
//        Test test3=(Test)constructor.newInstance();
//        System.out.println(test3);

        Class cla =Test.class;                         //获取class对象
        Constructor constructor = cla.getConstructor();//获取构造器

//        Object object =constructor.newInstance();
          Test test=(Test)constructor.newInstance();   //强转
//            Field field =cla.getField("name");
//            field.set(test,"刘俊先");
//           System.out.println(test.name);
//           Field[] fields=cla.getFields();
//          for(Object f:fields) {
//              System.out.println(f);
//          }

        Method method=cla.getDeclaredMethod("a",String.class);
        method.setAccessible(true);
        method.invoke(test,"liujunxian");

//        System.out.println(cla1 == cla2);
//        System.out.println(cla2 == cla3);
//        Field field = cla.getField("username01");
//        int i = field.getModifiers();
//        System.out.println(i);
//
//        System.out.println( cla.getName());
    }
}