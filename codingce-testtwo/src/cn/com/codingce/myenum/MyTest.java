package cn.com.codingce.myenum;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MyTest {
    public static void main(String[] args) throws IOException {

        byte[] bWrite = new byte[]{2, 5 ,3 ,4 ,1};
        File file = new File("test.txt");
        OutputStream ot = new FileOutputStream(file);
        for (int i = 0; i < bWrite.length;i++) {
            ot.write(bWrite[i]);
        }
        ot.close();
        InputStream it = new FileInputStream(file);
        int size = it.available();
        for (int z = 0; z < size; z++) {
            System.out.println((char)it.read() + "");
        }
        it.close();

        File f = new File("test.txt");
        FileOutputStream fop = new FileOutputStream(f);


        // 构建FileOutputStream对象,文件不存在会自动新建

        OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
        // 构建OutputStreamWriter对象,参数可以指定编码,默认为操作系统默认编码,windows上是gbk

        writer.append("中文输入");
        // 写入到缓冲区
        writer.append("\r\n");
        // 换行
        writer.append("English");
        // 刷新缓存冲,写入到文件,如果下面已经没有写入的内容了,直接close也会写入

        writer.close();
        // 关闭写入流,同时会把缓冲区内容写入文件,所以上面的注释掉

        fop.close();
        // 关闭输出流,释放系统资源

        FileInputStream fip = new FileInputStream(f);
        // 构建FileInputStream对象

        InputStreamReader reader = new InputStreamReader(fip, "UTF-8");
        // 构建InputStreamReader对象,编码与写入相同
        StringBuffer sb = new StringBuffer();
        while (reader.ready()) {
            sb.append((char) reader.read());
            // 转成char加到StringBuffer对象中
        }
        System.out.println(sb.toString());
        reader.close();
        // 关闭读取流
        fip.close();
        // 关闭输入流,释放系统资源

    }
}
