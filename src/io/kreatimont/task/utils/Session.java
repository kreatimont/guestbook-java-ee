package io.kreatimont.task.utils;

import io.kreatimont.task.model.User;

public class Session {

    private boolean connect;
    private User user;

    public User getUser() {
        return user;
    }

    public Session(User user) {
        this.connect = true;
        this.user = user;
    }

    public boolean isConnect() {
        return connect;
    }

    public void disconnect() {
        this.connect = false;
        this.user = null;
    }

}
