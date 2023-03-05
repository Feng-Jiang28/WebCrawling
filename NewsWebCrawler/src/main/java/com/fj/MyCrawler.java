package com.fj;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.BinaryParseData;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.parser.ParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

public class MyCrawler extends WebCrawler {

    private static final Pattern DOC = Pattern.compile(".*(\\.(html|doc|pdf))$");
    private static final Pattern IMG = Pattern.compile(".*(\\.(jpe?g|ico|png|bmp|svg|gif|webp|tiff))$");

    //private Set<String> urlSet = new HashSet<>();

    public static List<UrlStatus> urlStatusList = new CopyOnWriteArrayList<>();

    //public static List<String> downLoadedFiles = new ArrayList<>();

    public static List<FileDownloadItem> fileList= new ArrayList<>();

    public static List<DiscoveredItem> discoveredItemsList = new ArrayList<>();

    @Override
    public boolean shouldVisit(Page page, WebURL url){
        String urlString = url.getURL();
        // 1. to see if it's in the website

        boolean hostNameCheck = hasHostName(url);
        if(hostNameCheck){
            discoveredItemsList.add(new DiscoveredItem(urlString, "OK"));
        }else{
            discoveredItemsList.add(new DiscoveredItem(urlString, "N_OK"));
        }
        // 1. to see if it has an extension or has required extension.
        boolean extensionCheck = extensionCheck(url);
        // System.out.println(hostNameCheck && extensionCheck);

        return hostNameCheck && extensionCheck;
    }

    @Override
    public void visit(Page page){

        // Crawler4j already handles duplication checks so you don't have to handle it. It doesn't crawl pages that have already been visited

        String url = page.getWebURL().getURL();
        int statusCode = page.getStatusCode();

        urlStatusList.add(new UrlStatus(url, statusCode));
        
        String contentType = page.getContentType().split(";")[0];
        int fileSize = page.getContentData().length;
        int outLinks = 0;

        ParseData parseData = page.getParseData();


        //System.out.println("Visited: " + url + " Status code: " + statusCode);
        if(statusCode >= 200 && statusCode < 300 ){
            if(parseData instanceof HtmlParseData || page.getContentType().contains("text/html")) {
                Set<WebURL> links = ((HtmlParseData) parseData).getOutgoingUrls();
                outLinks = links.size();
                fileList.add(new FileDownloadItem(url, fileSize, outLinks, contentType));
            }
            if(parseData instanceof BinaryParseData ){
                fileList.add(new FileDownloadItem(url, fileSize, outLinks, contentType));
            }
        }

        if(urlStatusList.size() == Config.getIntProperty("maximum_pages")) {
            myController.shutdown();
        }


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
