package com.fj;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.regex.Pattern;

public class MyCrawler extends WebCrawler {

    private boolean finaVisit(Page page, WebURL url){
        String urlString = url.getURL();
        return false;
    }
}
