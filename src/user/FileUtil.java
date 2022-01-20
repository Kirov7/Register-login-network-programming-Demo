package user;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：xxx
 * @description：TODO
 * @date ：2022-01-20 10:45
 */

public class FileUtil {
    public static <T> List<T> readDate(String path) {
        List<T> dataList = new ArrayList<>();
        File file = new File(path);
        if (file.exists()) {
            try (InputStream is = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(is)) {
                dataList = (List<T>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    public static <T> boolean writeData(String path, List<T> dataList) {
        File file = new File(path);
        File parent = file.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }
        boolean success = true;

        try (OutputStream os = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(dataList);
            oos.flush();
        } catch (IOException e) {
            success = false;
            e.printStackTrace();
        }
        return success;
    }
}
