package EXCEL.util;


import EXCEL.JDBC.JDBC;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyExcelUpLoad {

    public static void main(String[] args) {
        String path = "D:\\程序\\userinfoSys\\xuexi2\\target\\xuexi2\\myfile\\1608777113734.xls";
        MyExcelUpLoad myExcelUpLoad = new MyExcelUpLoad();
        List<List<String>> lists = myExcelUpLoad.readExcelForPOI(path);
        for (List<String> list : lists){
             JDBC jdbc= new JDBC();
             jdbc.daoruList(list);

    }

//              System.out.println(list);







//        POIUtil poiUtil = new POIUtil();
//        List<List<String>> lists = new ArrayList<>();
//        List<String> only_list = new ArrayList<>();
//        only_list.add("第一行第一列");
//        only_list.add("第一行第二列");
//        only_list.add("第一行第三列");
//        lists.add(only_list);
//
//        only_list = new ArrayList<>();
//        only_list.add("第二行第一列");
//        only_list.add("第二行第二列");
//        lists.add(only_list);
//
//        try {
//            Workbook workbook = poiUtil.creatExcelForPOI(lists, "我的页码01");
//            FileOutputStream fileOutputStream = new FileOutputStream("E:/one.xls");
//            workbook.write(fileOutputStream);
//            workbook.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
        public List<List<String>> readExcelForPOI(String path) {
            String fileType = path.substring(path.lastIndexOf(".") + 1);   // 获取文件的后缀名
            List<List<String>> lists = new ArrayList<>();          // 里面的list代表每一行数据，外面list代表所有行数据，实际项目中，需要把excel中的每一行数据做成POJO对象处理
            InputStream is = null;                                             // 生成输入流
            try {
                is = new FileInputStream(path);
                Workbook wb = null;
                if (fileType.equals("xls")) {                  // 判断是2003版本还是2007之后的版本，xls为2003版本，xlsx为2007版本
                    wb = new HSSFWorkbook(is);                 // HSSFWorkbook类型对应2003版本
                } else if (fileType.equals("xlsx")) {
                    wb = new XSSFWorkbook(is);                 // XSSFWorkbook类型对应2007之后版本
                } else {
                    return null;
                }

                Sheet sheet = wb.getSheetAt(0);             //假设读取第一个工作页sheet，index默认从0开始，如果存在多个sheet，那么需要循环Sheet判断
                for (Row row : sheet) {                       //循环读取第一个工作页中的每一行记录
                    ArrayList<String> list = new ArrayList<>();
                    for (Cell cell : row) {                   // 循环读取一行中的每一列数据
                        cell.setCellType(CellType.STRING);    // 根据不同类型转化成字符串
                        list.add(cell.getStringCellValue());   // 获取当前列中的数据
                    }
                    lists.add(list);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return lists;
        }

}
