package com.example.demo.designpattern.chapter03;

public final class MMSCInfo {
    private final String deviceId;

    private final String url;

    private final int maxAttachmentSizeIntBytes;

    public MMSCInfo(String deviceId, String url, int maxAttachmentSizeIntBytes) {
        this.deviceId = deviceId;
        this.url = url;
        this.maxAttachmentSizeIntBytes = maxAttachmentSizeIntBytes;
    }

    public MMSCInfo(MMSCInfo prototype){
        this.deviceId = prototype.deviceId;
        this.url = prototype.url;
        this.maxAttachmentSizeIntBytes = prototype.maxAttachmentSizeIntBytes;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getUrl() {
        return url;
    }

    public int getMaxAttachmentSizeIntBytes() {
        return maxAttachmentSizeIntBytes;
    }
}
