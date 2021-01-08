package cn.com.codingce.myenum;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyFileOne {
    public static void main(String[] args) throws IOException {

        byte[] bWrite = new byte[]{2, 5 ,3 ,4 , 1};
        File file = new File("test.txt");
        OutputStream ot = new FileOutputStream(file);
        for (int i = 0; i < bWrite.length; i++) {
            ot.write(bWrite[i]);
        }
        ot.close();
        InputStream it = new FileInputStream(file);
        int size = it.available();
        for (int z = 0; z < size; z++) {
            System.out.println((char)it.read() + "");
        }
        it.close();
    }
}
