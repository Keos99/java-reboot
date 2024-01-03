package com.example.module11.model.dto;

public class Response {
    private boolean sucsess;

    public Response(boolean sucsess) {
        this.sucsess = sucsess;
    }

    public boolean isSucsess() {
        return sucsess;
    }

    public void setSucsess(boolean sucsess) {
        this.sucsess = sucsess;
    }

    @Override
    public String toString() {
        return "Response{" +
                "sucsess=" + sucsess +
                '}';
    }
}
