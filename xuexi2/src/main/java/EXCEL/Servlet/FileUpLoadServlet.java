package EXCEL.Servlet;

import EXCEL.util.MyFileUpLoad;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FileUpLoadServlet", urlPatterns = "/fileUpLoadServlet")
public class FileUpLoadServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
                       用户数据库 用户权限字段
        设计一个sql库，上传用户名、上传时间、上传文件名、上传文件后缀名(类型)、上传文件大小、下载次数

        用户登录之后，提供上传功能，每一个不同的用户分离不同的上传文件夹(扩展)，上传成功，用户可以看到所有的上传文件列表(自己的)
        并且提供下载功能，但是用户只能下载自己上传你的文件内容

        扩展：vip用户，可以查看平台所有的文件，但是只能下载自己的，如果下载别人，提示没有权限
        扩展：分享
         */
        // 通过multipart/form-data方式上传的文字，在request.getParameter中是无法获取的，需要在fileupload控件中的获取
        System.out.println("username=" + request.getParameter("username"));
        MyFileUpLoad myFileUpLoad = new MyFileUpLoad();
        response.setContentType("text/html;charset=utf-8");          // 同步请求时，返回内容为文件或者页面
        try {
            boolean action = myFileUpLoad.fileUpLoad(request);
            if(action) {
                response.getWriter().print("上传成功!");  // {returncode:0,messsage:"上传成功!"}
            }
            else {
                response.getWriter().print("上传失败!!!");
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
            response.getWriter().print("服务器错误!!!");
        }
    }
}