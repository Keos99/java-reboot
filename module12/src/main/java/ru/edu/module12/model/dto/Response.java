package ru.edu.module12.model.dto;

public class Response {
    private boolean sucsess;
    private String message;

    public Response(boolean sucsess, String message) {
        this.sucsess = sucsess;
        this.message = message;
    }

    public boolean isSucsess() {
        return sucsess;
    }

    public void setSucsess(boolean sucsess) {
        this.sucsess = sucsess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "sucsess=" + sucsess +
                ", message='" + message + '\'' +
                '}';
    }
}
