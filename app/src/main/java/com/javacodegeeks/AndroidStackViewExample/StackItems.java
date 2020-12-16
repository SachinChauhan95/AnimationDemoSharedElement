package com.javacodegeeks.AndroidStackViewExample;

import java.io.Serializable;

public class StackItems implements Serializable {
    Integer image;
    private String sharedTextViewId;

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    private String titleText;

    public StackItems(String name, Integer image) {
        this.image = image;
        this.sharedTextViewId=name;
    }

    public int getImage() {
        return image;
    }

    public String getSharedTextViewId() {
        return sharedTextViewId;
    }

    public void setSharedTextViewId(String sharedTextViewId) {
        this.sharedTextViewId = sharedTextViewId;
    }



}