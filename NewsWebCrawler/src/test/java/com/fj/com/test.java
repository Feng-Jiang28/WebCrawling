package com.fj.com;

import com.fj.Config;
import com.fj.MyCrawler;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import edu.uci.ics.crawler4j.url.WebURL;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

public class test {

    private static final Pattern DOC = Pattern.compile(".*(\\.(html|doc|pdf))$");
    private static final Pattern IMG = Pattern.compile(".*(\\.(jpe?g|ico|png|bmp|svg|gif|webp|tiff))$");

    @Test
    public void test(){
        System.out.println("Hello world!");
    }

    @Test
    public void testProperties() throws IOException {
        Properties props = new Properties();
        //FileReader file = new FileReader("src/main/resources/config.properties");
        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
        //InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
        props.load(fis);
    }

    @Test
    public void checkConfig(){
        String assginedDomain = Config.getProperty("news_site");
        System.out.println(assginedDomain);
    }

    @Test
    public void checkExtension(){
        String path = "https://www.nytimes.com";
        boolean lastDot = path.substring(path.lastIndexOf("/") + 1).contains(".");
        if(!lastDot)
            System.out.println("false");
        System.out.println(DOC.matcher(path).matches() || IMG.matcher(path).matches());
    }


    @Test
    public void hasHostName(){
        String domain = "nytimes.com";
        String assginedDomain = Config.getProperty("news_site");
        System.out.println(assginedDomain);
        //return domain.equals()
        System.out.println(domain.equals(assginedDomain));
    }

    @Test
    public void startCrawling() throws Exception {
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(Config.getProperty("crawl_storage"));
        System.out.println(Config.getProperty("crawl_storage"));
        //config.setCrawlStorageFolder("/tmp/crawler4j/");
        config.setMaxDepthOfCrawling(Config.getIntProperty("max_depth"));
        config.setMaxPagesToFetch(Config.getIntProperty("maximum_pages"));
        config.setIncludeBinaryContentInCrawling(true);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        controller.addSeed(Config.getProperty("url"));
        controller.start(MyCrawler.class, Config.getIntProperty("numberThreads"));

    }

//    @Test
//    public void hasHostName(){
//        String domain = "http://nytimes.com/section/sports";
//        String assginedDomain = Config.getProperty("news_site");
//        //return domain.equals()
//        System.out.println(domain.equals(assginedDomain));
//    }

}
