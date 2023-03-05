package com.fj;

public class FileDownloadItem {
    private String url;
    private long fileSize;
    private int numOutlinks;
    private String contentType;

    public FileDownloadItem(String url, long fileSize, int numOutlinks, String contentType) {
        this.url = url;
        this.fileSize = fileSize;
        this.numOutlinks = numOutlinks;
        this.contentType = contentType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public int getNumOutlinks() {
        return numOutlinks;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString(){
        return "url: " + url + " file size: " + fileSize + " num outLinks: " + numOutlinks + " contentType: " + contentType;
    }

}