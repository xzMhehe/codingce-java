package cn.com.codingce.domain;

import lombok.Data;

//描述上传文件的信息
@Data
public class FileAttribute {

    private String name;//文件名称
    private String size;//大小
    private String date;//上传的日期

}
