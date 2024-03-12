package com.eventstore.bookdatabase.diaryapp.themechanging;

public class ColorModel {
    private int color;
    private boolean isSelected;

    public ColorModel(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
