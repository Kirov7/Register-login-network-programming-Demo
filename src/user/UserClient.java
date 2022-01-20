package user;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Objects;

/**
 * @author ：xxx
 * @description：TODO
 * @date ：2022-01-20 10:10
 */

public class UserClient {
    private Socket client;

    public UserClient(String ip, int port) throws IOException {
        this.client = new Socket(ip, port);
    }

    public void sendMessage(Message<User> message) throws IOException {
        MessageUtil.sendMessage(client, message);
    }

    public String receiveMsg() throws IOException, ClassNotFoundException {
        Message<String> msg = MessageUtil.receiveMessage(client);
        return msg.getData();
    }

    public static void main(String[] args) {
        try {
            UserClient client = new UserClient("localhost", 8888);
            User user = new User("admin", "123456");
            client.sendMessage(new Message<>(user, "register"));
            String backMsg = client.receiveMsg();
            System.out.println(backMsg);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
