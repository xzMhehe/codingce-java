package cn.com.codingce.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.InputStream;
import java.net.URI;

//hadoop的工具类
public class HadoopUtils {
    //设置hdfs的访问地址
    private static final String HDFS_PATH = "hdfs://hadoop102:8020";

    //上传文件的地址路径
    private static final String FILE_PATH = "/input/";

    //hadoop的配置对象
    private static final Configuration conf = new Configuration();

    //文件上传
    public static void upload(InputStream inputStream, String filename) {
        try {
            //获取文件对象
            FileSystem fileSystem = FileSystem.get(new URI(HDFS_PATH), conf, "codingce");
            //获取hdfs的文件输出流
            FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path(FILE_PATH + filename));
            //输入输出流完成对拷
            IOUtils.copyBytes(inputStream, fsDataOutputStream, 1024, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //文件的下载
    public static InputStream download(String filename) {
        try {
            //获取hdfs的文件对象
            FileSystem fileSystem = FileSystem.get(new URI(HDFS_PATH), conf);
            //获取hdfs的数据输入流
            FSDataInputStream fsDataInputStream = fileSystem.open(new Path(FILE_PATH + filename));
            //返回输入流
            return fsDataInputStream;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //文件的删除
    public static void deleteFile(String filename) {
        try {
            //获取hdfs的文件对象
            FileSystem fileSystem = FileSystem.get(new URI(HDFS_PATH), conf);
            //执行删除
            fileSystem.delete(new Path(FILE_PATH + filename), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

























