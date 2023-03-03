package com.fj;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.regex.Pattern;

public class MyCrawler extends WebCrawler {

    private static final Pattern DOC = Pattern.compile(".*(\\.(html|doc|pdf))$");
    private static final Pattern IMG = Pattern.compile(".*(\\.(jpe?g|ico|png|bmp|svg|gif|webp|tiff))$");

    @Override
    public boolean shouldVisit(Page page, WebURL url){
        String urlString = url.getURL();
        // 1. to see if it's in the website
        boolean hostNameCheck = hasHostName(url);
        // 1. to see if it has an extension or has required extension.
        boolean extensionCheck = extensionCheck(url);
        // System.out.println(hostNameCheck && extensionCheck);
        return hostNameCheck && extensionCheck;
    }

    @Override
    public void visit(Page page){

    }

    private boolean extensionCheck(WebURL url) {
        String path = url.getPath();
        boolean lastDot = path.substring(path.lastIndexOf("/") + 1).contains(".");
        if(!lastDot)
            return true;
        return DOC.matcher(path).matches() || IMG.matcher(path).matches();
    }

    public boolean hasHostName(WebURL url){
        String domain = url.getDomain();
        String assginedDomain = Config.getProperty("news_site");
        //return domain.equals()
        return domain.equals(assginedDomain);
    }

}
