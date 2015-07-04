package com.busata.bhammer_sample.beans;

import com.busata.bhammer.beans.BasicExpandedItem;

public class ExpandedItem extends BasicExpandedItem {

    private String mTextHeader;
    private boolean mEnabled;
    private String mContentText;
    private String mTextButton;

    public ExpandedItem(String textHeader, String contentText, String textButton) {
        this.mTextHeader = textHeader;
        this.mContentText = contentText;
        this.mTextButton = textButton;
    }

    public ExpandedItem(String textHeader, String contentText, String textButton, boolean enabled) {
        this.mTextHeader = textHeader;
        this.mContentText = contentText;
        this.mTextButton = textButton;
        this.mEnabled = enabled;
    }

    public String getTextHeader() {
        return mTextHeader;
    }

    public void setTextHeader(String textHeader) {
        this.mTextHeader = textHeader;
    }

    public boolean ismEnabled() {
        return mEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.mEnabled = enabled;
    }

    public String getContentText() {
        return mContentText;
    }

    public void setmContentText(String contentText) {
        this.mContentText = contentText;
    }

    public String getTextButton() {
        return mTextButton;
    }

    public void setTextButton(String textButton) {
        this.mTextButton = textButton;
    }
}
