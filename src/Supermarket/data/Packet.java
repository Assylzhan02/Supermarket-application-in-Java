package Supermarket.data;

import java.io.ObjectInputStream;
import java.io.Serializable;

public class Packet implements Serializable {
    private String code;
    private Serializable data;

    public Packet() {}

    public Packet(String code, Serializable data) {
        this.code = code;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Serializable getData() {
        return data;
    }

    public void setData(Serializable data) {
        this.data = data;
    }
}
