package cn.com.codingce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HdfsClient {

    @Test
    public void testMkdirs() throws IOException, URISyntaxException,
            InterruptedException {
        // 1 获取文件系统
        Configuration configuration = new Configuration();
        // FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:8020"), configuration);
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:8020"), configuration, "codingce");
        // 2 创建目录
        fs.mkdirs(new Path("/xiyou/huaguoshan/"));
        // 3 关闭资源
        fs.close();
    }

    /**
     * HDFS 文件上传(测试参数优先级)
     * <p>
     * 将 hdfs-site.xml 拷贝到项目的 resources 资源目录下
     * <p>
     * 参数优先级排序:(1)客户端代码中设置的值 >(2)ClassPath 下的用户自定义配置文
     * 件 >(3)然后是服务器的自定义配置(xxx-site.xml)>(4)服务器的默认配置(xxx-default.xml)
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws URISyntaxException
     */
    @Test
    public void testCopyFromLocalFile() throws IOException,
            InterruptedException, URISyntaxException {
        // 1 获取文件系统
        Configuration configuration = new Configuration();
        configuration.set("dfs.replication", "2");
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:8020"),
                configuration, "codingce"); // 2 上传文件
        fs.copyFromLocalFile(new Path("/Users/williamma/mxz_code/github/hello.txt"), new Path("/xiyou/huaguoshan"));
        // 3 关闭资源
        fs.close();
    }

    /**
     * HDFS 文件下载
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws URISyntaxException
     */
    @Test
    public void testCopyToLocalFile() throws IOException,
            InterruptedException, URISyntaxException {
        // 1 获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:8020"),
                configuration, "codingce");
        // 2 执行下载操作
        // boolean delSrc 指是否将原文件删除
        // Path src 指要下载的文件路径
        // Path dst 指将文件下载到的路径
        // boolean useRawLocalFileSystem 是否开启文件校验 fs.copyToLocalFile(false, new
        fs.copyToLocalFile(false, new
                Path("/xiyou/huaguoshan/hello.txt"), new Path("/Users/williamma/mxz_code/github/hello2.txt"), true);
        // 3 关闭资源
        fs.close();
    }

    /**
     * HDFS 文件更名和移动
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws URISyntaxException
     */
    @Test
    public void testRename() throws IOException, InterruptedException,
            URISyntaxException {
        // 1 获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:8020"),
                configuration, "codincge");
        // 2 修改文件名称
        fs.rename(new Path("/xiyou/huaguoshan/hello.txt"), new Path("/xiyou/huaguoshan/meihouwang.txt"));
        // 3 关闭资源
        fs.close();
    }

    /**
     * HDFS 删除文件和目录
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws URISyntaxException
     */
    @Test
    public void testDelete() throws IOException, InterruptedException,
            URISyntaxException {
        // 1 获取文件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:8020"),
                configuration, "codingce");
        // 2 执行删除
        fs.delete(new Path("/xiyou"), true);
        // 3 关闭资源
        fs.close();
    }

}
