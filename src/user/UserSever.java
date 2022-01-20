package user;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author ：xxx
 * @description：TODO
 * @date ：2022-01-20 10:10
 */

public class UserSever {

    private static final String USER_PATH= "./user.obj";

    private ServerSocket server;

    public UserSever(int port) throws IOException {
        this.server = new ServerSocket(port);
    }
    public void start(){
        while (true){
            try {
                Socket userClient = server.accept();
                Message<User> message = MessageUtil.receiveMessage(userClient);
                String action = message.getAction();
                if ("register".equals(action)){
                    register(userClient, message);
                }else if ("login".equals(action)){
                    login(userClient, message);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void register(Socket userClient, Message<User> message) throws IOException {
        //读取存档的用户列表
        List<User> users = FileUtil.readDate(USER_PATH);
        //获取注册的用户信息
        User registerUser = message.getData();
        //检测用户列表中是否存在已注册的用户信息
        boolean exists = users.stream().anyMatch(user -> user.equals(registerUser));
        Message<String> backMsg = new Message<>(null, "back");
        if (exists){
            backMsg.setData("账号已被注册");
        }else {
            //将用户信息添加至用户列表中
            users.add(registerUser);
            //将用户列表重新存档
            boolean result = FileUtil.writeData(USER_PATH, users);
            String info = result ? "注册成功" : "注册失败";
            backMsg.setData(info);
        }
        MessageUtil.sendMessage(userClient, backMsg);
    }

    private void login(Socket userClient, Message<User> message) throws IOException {
        //读取存档的用户列表
        List<User> users = FileUtil.readDate(USER_PATH);
        //获取注册的用户信息
        User loginUser = message.getData();
        boolean exists = users.stream().anyMatch(user -> user.equals(loginUser));
        String msg = exists ? "登录成功" : "账号或密码错误";
        Message<String> backMsg = new Message<>(msg, "back");
        MessageUtil.sendMessage(userClient, backMsg);
    }

    public static void main(String[] args) {
        try {
            UserSever server = new UserSever(8888);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
