package app.client.controller;

import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.bouncycastle.eac.EACIOException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import java.io.*;

@RestController
public class FileReceiverController{
    @RequestMapping("/upload")
    public Integer receiveFile(MultipartHttpServletRequest data) throws IOException {
        if (data != null){
            HttpHeaders requestHeaders = data.getRequestHeaders();
            System.out.println(requestHeaders);
            System.out.println("接受到的数据为："+data.getParameter("receiver"));
            InputStream input = data.getFile("file").getInputStream();
            //System.out.println(data.getContextPath());
           // System.out.println(data.getServletContext().getRealPath(data.getRequestURI()));
            System.out.println(FileReceiverController.class.getResource("/"));
            File file = new File(FileReceiverController.class.getResource("/").getPath()+"datas.properties");
            OutputStream os = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            input.read(bytes);
            System.out.println("字节数组为："+bytes);
            System.out.println("文件内容为："+new String(bytes));
            os.write(bytes);
            System.out.println(file);
            os.close();
        }
        System.out.println("请求成功！");

         return 200;
    }
}