package org.repaire.service;

import org.repaire.controller.UploadController;
import org.repaire.pojo.BxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by Luo on 2018/5/14.
 */
@Service
public class UploadService implements IUploadService {
    private static final Logger logger = LoggerFactory.getLogger(UploadService.class);

    @Value("${imageNetPath}")
    public String imageNetPath;
    @Value("${imageDiskPath}")
    public String imageDiskPath;

    @Override
    public BxResult uploadPicture(HttpServletRequest request, HttpServletResponse response) throws Exception {
       /* String classPath = this.getClass().getClassLoader().getResource("/").getPath();
        try {
            classPath = URLDecoder.decode(classPath, "gb2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] strPath = classPath.split("/");
        String realPath = "";
        for(int i = 0;i < strPath.length ; i++){
            if(i > 0 && i <= 2){
                realPath = realPath + strPath[i]+"/";
            }
        }
        System.out.print(classPath + "dasdasdasdasd");
        logger.info(classPath);*/

        request.setCharacterEncoding("utf-8");  //设置编码
        MultipartHttpServletRequest req =(MultipartHttpServletRequest)request;
        MultipartFile multipartFile =  req.getFile("file");

        //获取文件需要上传到的路径
        String realPath = imageDiskPath;
        File dir = new File(realPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String fileName = multipartFile.getOriginalFilename();
        try {
            File file  =  new File(realPath,fileName);
            multipartFile.transferTo(file);
            return BxResult.ok(imageNetPath + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
