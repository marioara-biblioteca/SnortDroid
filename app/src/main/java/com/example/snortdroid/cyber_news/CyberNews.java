package com.example.snortdroid.cyber_news;

public class CyberNews {
    private String title;
    private String url;
    private String source;

    public CyberNews(String title, String url, String source) {
        this.title = title;
        this.url = url;
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
