package com.gis_server.controller;

import com.gis_server.config.FastDFSConfig;
import com.gis_server.pojo.SysUser;
import com.gis_server.service.UserService;
import com.gis_server.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class UserController {

    @Value("${server.servlet.context-path}")
    private String context_path;

    @Value("${fastdfs-nginx-module-host}")
    private String fastdfs_nginx_module_host;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @ResponseBody
    @PostMapping("/modifyUserInfo")
    public String modifyUserInfo(Principal principal,
                                 @RequestParam(value = "imgFile", required = false) MultipartFile file,
                                 HttpServletRequest request) throws IOException, ServletException, ParseException {
        logger.info("========== 修改用户信息 ===========");
//        final Part filePart = request.getPart("imgFile");
        final String nickName = request.getParameter("nickName");
        final String password = BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt());
        final String email = request.getParameter("email");
        final String sex = request.getParameter("sex");
        final String birthday = request.getParameter("birthday");
        final String sign = request.getParameter("sign");

        logger.info("nickName=" + nickName);
        SysUser user = new SysUser();
        user.setuLoginid(principal.getName());
//        user.setuLoginid("admin");
//        HttpSession session = request.getSession();
        if(file != null){
            String filePath = FastDFSConfig.upload(file);
            logger.info("上传头像路径: "+ filePath);
            if (filePath != null && filePath.length() > 0){
                user.setuHeadportrait(fastdfs_nginx_module_host + "/" + filePath);
            }
//            StringBuilder filePath = new StringBuilder();
//            String fileFormat = getFileName(filePart).split("\\.")[1];
////            String rootPath = session.getServletContext().getRealPath("/");
//            String rootPath = ResourceUtils.getURL("classpath:").getPath();
//            System.out.println("rootPath=" + rootPath);
//            filePath.append("users/")
//                    .append(principal.getName())
//                    .append("/head_img/")
//                    .append(DateUtils.getNow("yyyyMMddHHmmss"))
//                    .append(".")
//                    .append(fileFormat);
//            File file = new File(rootPath + "static/" + filePath.toString());
//            File fileDir = file.getParentFile();
//            if(!fileDir.exists()){
//                fileDir.mkdirs();  // 表示如果当前目录不存在就创建，包括所有必须的父目录
//            }
//            if(!file.exists()){
//                file.createNewFile();
//            }
//            OutputStream out = new FileOutputStream(file);
//            InputStream fileContent = filePart.getInputStream();
//            int read = 0;
//            final byte[] bytes = new byte[1024];
//            while((read = fileContent.read(bytes)) != -1){
//                out.write(bytes, 0, read);
//            }
//            if (out != null) {
//                out.close();
//            }
//            if (fileContent != null) {
//                fileContent.close();
//            }
//            System.out.println("头像存储路径:" + rootPath + filePath.toString());
//            user.setuHeadportrait(context_path + "/" + filePath.toString());
        }
        if(!"".equals(nickName) && nickName != null){
            user.setuNickname(nickName);
        }
        if(!"".equals(password) && password != null){
            user.setuPassword(password);
        }
        if(!"".equals(email) && email != null){
            user.setuEmail(email);
        }
        if(!"".equals(sex) && sex != null){
            if("男".equals(sex)){
                user.setuSex(true);
            }
            else if("女".equals(sex)){
                user.setuSex(false);
            }
        }
        if(!"".equals(birthday) && birthday != null){
            user.setuBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
        }
        if(!"".equals(sign) && sign != null){
            user.setuSignature(sign);
        }
        if(userService.updateUserInfo(user) > 0){
            return "修改用户信息成功";
        }else{
            return "修改用户信息失败";
        }
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        logger.info("Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
