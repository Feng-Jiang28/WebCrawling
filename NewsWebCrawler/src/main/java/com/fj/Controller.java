package com.fj;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

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


        for(int i = 0; i < fileList.size(); i++){
            System.out.println(fileList.get(i));
        }

        for(int i = 0; i < discoveredItemsList.size(); i++){
            System.out.println(discoveredItemsList.get(i));
        }


        for(int i = 0; i < urlStatusList.size(); i++){
            System.out.println(i +  " URL: " + urlStatusList.get(i).getUrl() + " : " + urlStatusList.get(i).getStatusCode());
        }
        // System.out.println(urlStatusList);
        // System.out.println(MyCrawler.downLoadedFiles);
        ///System.out.println("done !!!!!");

    }
}
