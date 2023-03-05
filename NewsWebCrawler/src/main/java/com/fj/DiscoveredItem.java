package com.fj;

public class DiscoveredItem {
    private String url;
    private String resides;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setResides(String resides) {
        this.resides = resides;
    }

    public String getUrl() {
        return url;
    }

    public String getResides() {
        return resides;
    }

    public DiscoveredItem(String url, String resides){
        this.url = url;
        this.resides = resides;
    }

    @Override
    public String toString(){
        return "url : " + url + " resides : " + resides ;
    }

}
