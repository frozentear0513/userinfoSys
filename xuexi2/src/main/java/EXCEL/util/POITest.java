package EXCEL.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: thinknovo
 * Date: 2018/12/23
 * Description:  建立excel文件，需要apache poi驱动包poi-4.0.1.jar支持
 * Version: V1.0
 */
public class POITest {
    /*
    结构：
        HSSF － 提供读写Microsoft Excel格式档案的功能。2003
        XSSF － 提供读写Microsoft Excel OOXML格式档案的功能。2007
        HWPF － 提供读写Microsoft Word格式档案的功能。
        HSLF － 提供读写Microsoft PowerPoint格式档案的功能。
        HDGF － 提供读写Microsoft Visio格式档案的功能。
     */

    /** Excel 文件要存放的位置，假定在D盘下*/
    public static String outputFile = "D:" + File.separator + "test.xls";
    public static void main(String argv[]) {
        try {
            // 创建新的Excel 工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 在Excel工作簿中建一工作表，其名为缺省值
            // 如要新建一名为"效益指标"的工作表，其语句为：
            // HSSFSheet sheet = workbook.createSheet("效益指标");
            HSSFSheet sheet = workbook.createSheet();
            // 在索引0的位置创建行（最顶端的行）
            HSSFRow row = sheet.createRow((short)0);
            //在索引0的位置创建单元格（左上端）
            HSSFCell cell = row.createCell((short)0);
            // 定义单元格为字符串类型
//            cell.setCellType(HSSFCell.CELL_TYPE_STRING);//已过时
            // 在单元格中输入一些内容
            cell.setCellValue("增加值");
            // 新建一输出文件流
            FileOutputStream fOut = new FileOutputStream(outputFile);
            // 把相应的Excel 工作簿存盘
            workbook.write(fOut);
            fOut.flush();
            // 操作结束，关闭文件
            fOut.close();
            System.out.println("文件生成...");
        } catch (Exception e) {
            System.out.println("已运行 xlCreate() : " + e);
        }
    }
}
