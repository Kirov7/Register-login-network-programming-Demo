package user;

import java.io.Serializable;

/**
 * @author ：xxx
 * @description：TODO
 * @date ：2022-01-20 10:07
 */

public class Message<T> implements Serializable {
    private T data;//发送的消息

    private String action;//行为

    public Message(T data, String action) {
        this.data = data;
        this.action = action;
    }



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


}
