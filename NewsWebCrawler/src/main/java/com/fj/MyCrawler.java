package com.fj;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.BinaryParseData;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.parser.ParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

public class MyCrawler extends WebCrawler {

    CrawlReport crawlReport = new CrawlReport();
    private static final Pattern DOC = Pattern.compile(".*(\\.(html|doc|pdf))$");
    private static final Pattern IMG = Pattern.compile(".*(\\.(jpe?g|ico|png|bmp|svg|gif|webp|tiff))$");

    //private Set<String> urlSet = new HashSet<>();

    public static List<UrlStatus> urlStatusList = new CopyOnWriteArrayList<>();

    //public static List<String> downLoadedFiles = new ArrayList<>();

    public static List<FileDownloadItem> fileList= new ArrayList<>();

    public static List<DiscoveredItem> discoveredItemsList = new ArrayList<>();

    public static Set<String> set_within = new HashSet();
    public static Set<String> set_outside = new HashSet();


    @Override
    protected void handlePageStatusCode(WebURL webUrl, int statusCode, String statusDescription) {
        crawlReport.attempt++;
        String url = webUrl.getURL().toLowerCase().replaceAll(",", "_");
        urlStatusList.add(new UrlStatus(url, statusCode));

        if(statusCode == 400){
            crawlReport.bad_request_400++;
        }else if(statusCode == 403){
            crawlReport.forbidden_403  ++;
        }else if(statusCode == 404){
            crawlReport.not_found_404++;
        }else if(statusCode == 500){
            crawlReport.internal_server_500++;
        }else if(statusCode == 200){
            crawlReport.ok_200++;
        }else if(statusCode == 301){
            crawlReport.moved_permanently_301++;
        }else if(statusCode == 302){
            crawlReport.moved_temporarily_302++;
        }else if(statusCode == 401 ){
            crawlReport.unauthorized_401++;
        }

        if(!(statusCode >= 200 && statusCode < 300 )){
            crawlReport.failed++;
        }else{
            crawlReport.succeeded++;
        }

        if (urlStatusList.size() >= Config.getIntProperty("writeEveryRows")) {
            try {
                // Create a FileWriter object to write to the CSV file
                UrlWriter.writeUrlStatusListToFile(urlStatusList, "fetch_NYTimes.csv");
                // Clear the urlStatusList
                urlStatusList.clear();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean shouldVisit(Page page, WebURL url){

        crawlReport.outgoing_url++;

        String urlString = url.getURL();
        // 1. to see if it's in the website

        boolean hostNameCheck = hasHostName(url);
        if(hostNameCheck){
            discoveredItemsList.add(new DiscoveredItem(urlString, "OK"));
            set_within.add(urlString);
        }else{
            discoveredItemsList.add(new DiscoveredItem(urlString, "N_OK"));
            set_outside.add(urlString);
        }

        if (discoveredItemsList.size() >= Config.getIntProperty("writeEveryRows")) {
            try {
                // Create a FileWriter object to write to the CSV file
                UrlWriter.writeVisit(discoveredItemsList, "url_NYTimes.csv");
                // Clear the urlStatusList
                discoveredItemsList.clear();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
        // int statusCode = page.getStatusCode();
        String contentType = "";
        int fileSize = 0;
        ParseData parseData = null;

        //System.out.println("Visited: " + url + " Status code: " + statusCode);

        parseData = page.getParseData();
        contentType = page.getContentType().split(";")[0];
        fileSize = page.getContentData().length;

        int outLinks = 0;

        if(fileSize < Config.KB){
            crawlReport.less_1kb++;
        }else if(fileSize < 10 *  Config.KB){
            crawlReport.from_1kb_10kb++;
        }else if(fileSize < 100 * Config.KB){
            crawlReport.from_10kb++;
        }else if(fileSize < Config.MB){
            crawlReport.from_100kb++;
        }else{
            crawlReport.bigger_than_1mb++;
        }


        if(parseData instanceof HtmlParseData || page.getContentType().contains("text/html")) {
            Set<WebURL> links = ((HtmlParseData) parseData).getOutgoingUrls();
            outLinks = links.size();
            fileList.add(new FileDownloadItem(url, fileSize, outLinks, contentType));
            crawlReport.text_html++;
        }

        if(parseData instanceof BinaryParseData ){
            if( page.getContentType().contains("image/png")){
                crawlReport.image_png++;
            }else if(page.getContentType().contains("image/gif")){
                crawlReport.image_gif++;
            }else if(page.getContentType().contains("image/jpeg")){
                crawlReport.image_jpeg++;
            }else if(page.getContentType().contains("application/pdf")){
                crawlReport.application_pdf++;
            }
            fileList.add(new FileDownloadItem(url, fileSize, outLinks, contentType));
        }


        if (fileList.size() >= Config.getIntProperty("writeEveryRows")) {
            try {
                // Create a FileWriter object to write to the CSV file
                UrlWriter.writeFileDownloaded (fileList, "visit_NYTimes.csv");
                // Clear the urlStatusList
                fileList.clear();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


//        if(urlStatusList.size() == Config.getIntProperty("maximum_pages")) {
//            myController.shutdown();
//        }

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
