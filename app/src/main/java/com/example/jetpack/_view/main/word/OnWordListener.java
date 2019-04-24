package com.example.jetpack._view.main.word;

public interface OnWordListener {

    enum visibility {
        VISIBLE,
        INVISIBLE
    }

    void visible(visibility val);
    void edit(Object obj);
    void delete(Object obj);

}
