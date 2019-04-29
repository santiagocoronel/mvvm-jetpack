package com.example.jetpack._model.pojo._base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MessageServices<T> {

    public static final int CODE_DAFAULT_OK = 1;
    public static final int CODE_DAFAULT_ERROR = 0;
    public static final String MSG_DEFAULT_OK = "ok";
    public static final String MSG_DEFAULT_ERROR_SHORT = "lo sentimos algo salio mal";
    public static final String MSG_DEFAULT_ERROR_LONG = "lo sentimos algo salio mal, intentalo nuevamente mas tarde";
    public static final String MSGDEV_NO_DATA_FOUND = "no data found";

    public static final String DEFAULT_FORMAT_DATETIME = "yyyy-MM-dd'T'HH:mm:ss";

    @SerializedName("code")
    private int code;
    @SerializedName("msg")
    private String msg;
    @SerializedName("msgDev")
    private String msgDev;
    @SerializedName("data")
    private List<T> data;
    @SerializedName("item")
    private T item;

    public MessageServices() {
    }

    public MessageServices(int code, String msg, String msgDev, List<T> data, T item) {
        this.code = code;
        this.msg = msg;
        this.msgDev = msgDev;
        this.data = data;
        this.item = item;
    }

    private MessageServices(Builder builder) {
        this.code = builder.code;
        this.msg = builder.msg;
        this.msgDev = builder.msgDev;
        this.data = builder.data;
        this.item = (T) builder.item;
    }

    public static Builder newMessageServices() {
        return new Builder();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgDev() {
        return msgDev;
    }

    public void setMsgDev(String msgDev) {
        this.msgDev = msgDev;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {

        if (data == null || data.size() == 0) {
            this.msgDev = this.msgDev == null ? MSGDEV_NO_DATA_FOUND : this.msgDev;
        }

        this.data = data;
    }

    public void setData(T data) {
        List<T> list = new ArrayList<>();
        if (data != null) {
            list.add(data);
        } else {
            this.msgDev = this.msgDev == null ? MSGDEV_NO_DATA_FOUND : this.msgDev;
        }

        this.data = list;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public List<T> dataToSpeficObjectList(Class<T> typeClass) throws Exception {
        List<T> resList = new ArrayList<>();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        for (int i = 0; i < ((ArrayList) getData()).size(); i++) {
            JsonObject jsonObject = gson.toJsonTree(((ArrayList) getData()).get(i)).getAsJsonObject();
            T item = gson.fromJson(jsonObject.toString(), typeClass);
            resList.add(item);
        }
        return resList;
    }

    public T dataToSpeficObject(Class<T> typeClass) throws Exception {

        Gson gson = new GsonBuilder().setDateFormat("?????????????-Z")/*.setDateFormat("yyyy-MM-dd HH:mm:ss")*/.create();
        JsonObject jsonObject = gson.toJsonTree(getItem()).getAsJsonObject();
        T object = gson.fromJson(jsonObject.toString(), typeClass);
        return object;
    }

    public static String toGson(MessageServices messageServices) throws Exception {
        return new GsonBuilder().setDateFormat(DEFAULT_FORMAT_DATETIME).create().toJson(messageServices);
    }

    public static MessageServices toMessageServices(String gson) throws Exception {
        return new GsonBuilder().setDateFormat(DEFAULT_FORMAT_DATETIME).create().fromJson(gson, MessageServices.class);
    }

    public static final class Builder<T> {
        private int code;
        private String msg;
        private String msgDev;
        private List<T> data;
        private T item;

        private Builder() {
        }

        public MessageServices build() {
            return new MessageServices(this);
        }

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder msgDev(String msgDev) {
            this.msgDev = msgDev;
            return this;
        }

        public Builder data(List<T> data) {
            this.data = data;
            return this;
        }

        public Builder item(T item) {
            this.item = item;
            return this;
        }
    }
}