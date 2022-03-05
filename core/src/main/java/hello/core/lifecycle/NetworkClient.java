package hello.core.lifecycle;

import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Setter
public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("url = " + url);
    }

    public void call(String msg) {
        System.out.println("call: " + url + " msg = " + msg);
    }

    public void connect() {
        System.out.println("connect = " + url);
    }

    public void disconnect() {
        System.out.println("close = " + url);
    }

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disConnect();
    }

    public void disConnect() {
        System.out.println("close = " + url);
    }
}
