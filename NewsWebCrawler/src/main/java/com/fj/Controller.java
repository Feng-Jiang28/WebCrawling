package com.fj;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import java.io.IOException;

import static com.fj.MyCrawler.*;

public class Controller {


    public static void main(String[] args) throws Exception{
       CrawlConfig config = new CrawlConfig();
       config.setCrawlStorageFolder(Config.getProperty("crawl_storage"));
       config.setMaxDepthOfCrawling(Config.getIntProperty("max_depth"));
       config.setMaxPagesToFetch(Config.getIntProperty("maximum_pages"));
       config.setIncludeBinaryContentInCrawling(true);
       //config.setPolitenessDelay(2500);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);


        controller.addSeed(Config.getProperty("url"));
        controller.start(MyCrawler.class, Config.getIntProperty("numberThreads"));


        try {
            // Create a FileWriter object to write to the CSV file
            UrlWriter.writeUrlStatusListToFile(urlStatusList, "fetch_NYTimes.csv");
            // Clear the urlStatusList
            urlStatusList.clear();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try {
            // Create a FileWriter object to write to the CSV file
            UrlWriter.writeUrlStatusListToFile(urlStatusList, "fetch_NYTimes.csv");
            // Clear the urlStatusList
            urlStatusList.clear();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try {
            // Create a FileWriter object to write to the CSV file
            UrlWriter.writeFileDownloaded (fileList, "visit_NYTimes.csv");
            // Clear the urlStatusList
            fileList.clear();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int num_extract = set_within.size() + set_outside.size();
        CrawlReport crawlReport = new CrawlReport();

        crawlReport.unique_url = num_extract;
        crawlReport.unique_url_within = set_within.size();
        crawlReport.unique_url_outside = set_outside.size();

        crawlReport.write("CrawlReport_NYTimes.txt");

    }
}
