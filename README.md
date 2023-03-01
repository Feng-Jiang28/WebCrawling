# WebCrawling
## 1.object
This project will can help user download web pages and gather webpage metadata from preselected news website.

## 2. Preliminaries
The project makes use of an existing open source Java web crawler called crawler4j. This crawler
is built upon the open source crawler4j library which is located on github. For complete details on
downloading and compiling see <https://github.com/yasserg/crawler4j>

## 3. Crawling
The websites in this project that we would like to crwal is the NY times <https://www.nytimes.com>. The maximum pages to fetch can be set in crawler4j and it was set to 20,000 to ensure a reasonable execution time for this exercise. And maximum depth was set to 16 to ensure that we limit the crawling. The crawler was limited so it can only visits HTML, doc, pdf and different image format URLs and record the meta data for those file types.

## 4. Colloecting Statistics
1.the URLs it attempts to fetch, a two column spreadsheet, column 1 containing the URL and column 2 containing the HTTP/HTTPS status code received; name the file fetch_NewsSite.csv.
2. the files it successfully downloads, a four column spreadsheet, column 1 containing the URLs successfully downloaded, column 2 containing the size of the downloaded file (in Bytes, or you can choose your own preferred unit (bytes,kb,mb)), column 3 containing the # of outlinks found, and column 4 containing the resulting content-type; name the file visit_NewsSite.cs
