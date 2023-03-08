package com.fj;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UrlWriter {


    public static void writeUrlStatusListToFile(List<UrlStatus> urlStatusList, String filename) throws IOException {
        // If file doesn't exist, create it
        File file = new File(filename);
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter csvWriter = new FileWriter(filename, true);
        // Write UrlStatus objects to file
        for (UrlStatus urlStatus : urlStatusList) {
            csvWriter.append(urlStatus.getUrl());
            csvWriter.append(",");
            csvWriter.append(Long.toString(urlStatus.getStatusCode()));
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }

    public static void writeVisit(List<DiscoveredItem> visitList, String filename) throws IOException {
        // If file doesn't exist, create it
        File file = new File(filename);
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter csvWriter = new FileWriter(filename, true);
        // Write UrlStatus objects to file
        for (DiscoveredItem discoveredItem : visitList) {
            csvWriter.append(discoveredItem.getUrl());
            csvWriter.append(",");
            csvWriter.append(discoveredItem.getResides());
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }

    public static void writeFileDownloaded(List<FileDownloadItem> fileList, String filename) throws IOException {
        // If file doesn't exist, create it
        File file = new File(filename);
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter csvWriter = new FileWriter(filename, true);
        // Write UrlStatus objects to file
        for (FileDownloadItem fileDownloadItem : fileList ) {
            csvWriter.append(fileDownloadItem.getUrl());
            csvWriter.append(",");
            csvWriter.append("" + fileDownloadItem.getFileSize());
            csvWriter.append(",");
            csvWriter.append("" + fileDownloadItem.getNumOutlinks());
            csvWriter.append(",");
            csvWriter.append(fileDownloadItem.getContentType());
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }


}
