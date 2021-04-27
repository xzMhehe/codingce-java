package cn.com.codingce.controller;

import cn.com.codingce.domain.FileAttribute;
import cn.com.codingce.utils.HadoopUtils;
import cn.com.codingce.utils.RedisUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

//控制层
@RestController
public class HdfsController {

    //依赖
    @Autowired
    private RedisUtils redisUtils;

    //注册
    @PostMapping("registerUser")
    public ModelAndView registerUser(String username, String password) {
        //注册成功返回到显示页面
        if (redisUtils.registerUser(username, password)) {
            return new ModelAndView("show");
        }
        //注册失败返回到登录页面
        return new ModelAndView("index");
    }

    //登录
    @PostMapping("login")
    public ModelAndView login(String username, String password) {
        if (redisUtils.login(username, password)) {
            return new ModelAndView("redirect:show.html");
        }
        return new ModelAndView("redirect:index.html");
    }

    //调用显示页面
    @GetMapping("showPage")
    public ModelAndView showPage() {
        return new ModelAndView("show");
    }

    //显示数据
    @GetMapping("show")
    public ModelAndView show() {
        List<FileAttribute> list = JSON.parseArray(redisUtils.findAll("fileList").toString(),
                FileAttribute.class);
        return new ModelAndView("show", "list", list);
    }

    //显示上传页面
    @GetMapping("uploadPage")
    public ModelAndView uploadPage() {
        return new ModelAndView("upload");
    }

    /**
     * vo数据载体
     * entity必须有数据表对应
     * domain是一种xp快速开发方式的定义
     * beans：通常指dao  service  controller
     */
    //上传文件描述和hdfs中的上传
    @PostMapping("upload")
    //@Transactional
    public ModelAndView upload(MultipartFile file) {
        //获取文件的信息
        if (file == null) {
            return new ModelAndView("upload.html");
        }
        //文件的名称
        String filename = file.getOriginalFilename();
        //添加到vo中(数据的载体)
        FileAttribute fileAttribute = new FileAttribute();
        fileAttribute.setName(filename);
        fileAttribute.setSize(file.getSize() + "");
        fileAttribute.setDate(LocalDateTime.now().toString());

        //添加到redis
        redisUtils.uploadFileDesc("fileList", filename, JSON.toJSONString(fileAttribute));

        //上传到hadoop
        try {
            HadoopUtils.upload(file.getInputStream(), filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView("show");
    }

    //下载
    @RequestMapping("download/{filename}")
    public void download(@PathVariable String filename, HttpServletResponse response) {
        //处理中文
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        //下载提示框
        response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
        //执行下载
        try {
            //输入流
            InputStream inputStream = HadoopUtils.download(filename);
            //输出流
            ServletOutputStream outputStream = response.getOutputStream();
            //每次下载大小
            byte[] buf = new byte[1024];
            //初始值
            int len = 0;
            //下载
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            //释放
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除：1.redis  2.hadoop
    @RequestMapping("deleteFile/{filename}")
    public ModelAndView deleteFile(@PathVariable String filename) {
        //1.redis
        redisUtils.deleteFileDesc("fileList", filename);
        //2. hadoop
        HadoopUtils.deleteFile(filename);
        return new ModelAndView("show");
    }
}










































