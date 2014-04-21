BfsCrawler
==========

a Zhihu BfsCrawler in Java

这是一个实现了宽度优先的知乎网络爬虫。

下载使用的是HttpClient工具，解析页面使用的是HtmlParser，未来还可以考虑使用Jsoup。

通过两个队列，visitedUrl和unVisitedUrl实现了宽度优先爬取。

单线程。

自动爬取知乎推荐的文章存储到本地指定文件中。
