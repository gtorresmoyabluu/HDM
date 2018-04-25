package com.bluu.hdm.web.progressbar;

public interface ProgressBarListener {
    void onProgressBarUpdated(String id, int progress, boolean finished, boolean forceFinish);
}
