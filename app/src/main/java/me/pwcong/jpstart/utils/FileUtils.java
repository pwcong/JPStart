package me.pwcong.jpstart.utils;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Pwcong on 2016/9/26.
 */

public class FileUtils {

    public static final int BUFFER_SIZE = 1024;

    private FileUtils(){
        throw new RuntimeException("O.O");
    }

    public static void copyFileFromAssets(Context context, String assetName,String path, String fileName){

        File dir = new File(path);
        if (!dir.exists()){
            dir.mkdirs();
        }
        File dbFile = new File(path + fileName);
        if (!dbFile.exists()){
            InputStream is;
            OutputStream os;
            try {
                is = context.getResources().getAssets().open(assetName);
                os = new FileOutputStream(dbFile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int length;
                while ((length = is.read(buffer, 0, buffer.length)) > 0){
                    os.write(buffer, 0, length);
                }
                os.flush();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
