package com.fj;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CrawlReport {

    private static String name = Config.getProperty("name");
    private static String id = Config.getProperty("usc_id");
    private static String news_site_crawled = Config.getProperty("news_site");
    private static int number_of_thread = Config.getIntProperty("numberThreads");

    static int attempt = 0;
    static int succeeded = 0;
    static int failed = 0;

    static int outgoing_url = 0;
    static int unique_url = 0;
    static int unique_url_within = 0;
    static int unique_url_outside = 0;

    static int ok_200 = 0;
    static int moved_temporarily_302 = 0;
    static int unauthorized_401 = 0;
    static int forbidden_403 = 0;
    static int not_found_404 = 0;
    static int internal_server_500 = 0;
    static int moved_permanently_301 = 0;
    static int bad_request_400 = 0;

    static int less_1kb = 0;
    static int from_1kb_10kb = 0;
    static int from_10kb = 0;
    static int from_100kb = 0;
    static int bigger_than_1mb = 0;

    static int text_html = 0;
    static int image_gif = 0;
    static int image_jpeg = 0;
    static int image_png = 0;
    static int application_pdf = 0;

    static void write(String filename ) throws IOException {
        File file = new File(filename);
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter csvWriter = new FileWriter(filename, true);


        csvWriter.append("Name: " + name + "\n");
        csvWriter.append("USC ID: " + id + "\n");
        csvWriter.append("News site crawled: " + news_site_crawled+ "\n");
        csvWriter.append("Number of threads: " + number_of_thread+ "\n");
        csvWriter.append("Fetch Statistics"+ "\n");
        csvWriter.append("================"+ "\n");
        csvWriter.append("# fetches attempted: " + attempt+ "\n");
        csvWriter.append("# fetches succeeded:" + succeeded+ "\n");
        csvWriter.append("# fetches failed or aborted:" + failed+ "\n");
        csvWriter.append("Outgoing URLs" + "\n");
        csvWriter.append("=============="+ "\n");

        csvWriter.append("Total URLs extracted:" + outgoing_url  + "\n");
        csvWriter.append("# unique URLs extracted:" + unique_url+ "\n");
        csvWriter.append("# unique URLs within News Site:" + unique_url_within + "\n");
        csvWriter.append("# unique URLs outside News Site:" + unique_url_outside+ "\n");
        csvWriter.append("Status Codes:"+ "\n");
        csvWriter.append("============="+ "\n");
        csvWriter.append("200 OK:" + ok_200 + "\n");
        csvWriter.append("301 Moved Permanently:" + moved_permanently_301 + "\n");
        csvWriter.append("302 Move Temporarily: " + moved_temporarily_302 + "\n");
        csvWriter.append("400 Bad Request: " + bad_request_400 + "\n");
        csvWriter.append("401 Unauthorized:" + unauthorized_401 + "\n");
        csvWriter.append("403 Forbidden:" + forbidden_403+ "\n");
        csvWriter.append("404 Not Found:" + not_found_404 + "\n");
        csvWriter.append("500 Internal Server:" + internal_server_500 + "\n");
        csvWriter.append("File Sizes:" + "\n");
        csvWriter.append("===========" + "\n");
        csvWriter.append("< 1KB:" + less_1kb+ "\n");
        csvWriter.append("1KB ~ <10KB:" + from_1kb_10kb+ "\n");
        csvWriter.append("10KB ~ <100KB:" + from_10kb+ "\n");
        csvWriter.append("100KB ~ <1MB:" + from_100kb+ "\n");
        csvWriter.append(">= 1MB:" + bigger_than_1mb+ "\n");
        csvWriter.append("Content Types:"+ "\n");
        csvWriter.append("=============="+ "\n");
        csvWriter.append("text/html:" + text_html+ "\n");
        csvWriter.append("image/gif:" + image_gif+ "\n");
        csvWriter.append("image/jpeg:" + image_jpeg+ "\n");
        csvWriter.append("image/png:" + image_png+ "\n");
        csvWriter.append("application/pdf:" + application_pdf+ "\n");


        csvWriter.flush();
        csvWriter.close();

    }
    //static void print(String s){
       // System.out.println(s);
    //}



}
