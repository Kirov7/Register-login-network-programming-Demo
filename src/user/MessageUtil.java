package user;

import java.io.*;
import java.net.Socket;

/**
 * @author ：xxx
 * @description：TODO
 * @date ：2022-01-20 10:14
 */

public class MessageUtil {

    public static <T> void sendMessage(Socket socket, Message<T> message)
            throws IOException {
        OutputStream os = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(message);
        oos.flush();
        socket.shutdownOutput();

    }

    public static <T> Message<T> receiveMessage(Socket socket)
            throws IOException, ClassNotFoundException {
        InputStream is = socket.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(is);
        Message<T> message = (Message<T>) ois.readObject();
        return message;
    }
}
