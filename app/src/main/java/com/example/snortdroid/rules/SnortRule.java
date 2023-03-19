package com.example.snortdroid.rules;

import java.io.Serializable;

public class SnortRule  extends Rule implements Serializable{

    private String action;
    private String protocol;
    private String sourceNet,destNet;
    private int sourcePort,destPort;
    private String message;
    private int rev,sid;
    private String content;
    private int contentOffset;
    private String ttl;
    public SnortRule() {
        super();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getSourceNet() {
        return sourceNet;
    }

    public void setSourceNet(String sourceNet) {
        this.sourceNet = sourceNet;
    }

    public String getDestNet() {
        return destNet;
    }

    public void setDestNet(String destNet) {
        this.destNet = destNet;
    }

    public int getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(int sourcePort) {
        this.sourcePort = sourcePort;
    }

    public int getDestPort() {
        return destPort;
    }

    public void setDestPort(int destPort) {
        this.destPort = destPort;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRev() {
        return rev;
    }

    public void setRev(int rev) {
        this.rev = rev;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getContentOffset() {
        return contentOffset;
    }

    public void setContentOffset(int contentOffset) {
        this.contentOffset = contentOffset;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }
}
