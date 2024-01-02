package ru.sberbank.edu.model;

import com.google.gson.annotations.SerializedName;

public class Snow {
    @SerializedName("1h")
    public double _1h;

    public Snow() {
    }

    public Snow(double _1h) {
        this._1h = _1h;
    }

    public double get_1h() {
        return _1h;
    }

    public void set_1h(double _1h) {
        this._1h = _1h;
    }

    @Override
    public String toString() {
        return "Snow{" +
                "_1h=" + _1h +
                '}';
    }
}
