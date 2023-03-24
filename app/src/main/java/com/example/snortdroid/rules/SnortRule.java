package com.example.snortdroid.rules;

import com.example.snortdroid.rules.enums.Direction;
import com.example.snortdroid.rules.enums.HttpMethods;
import com.example.snortdroid.rules.enums.HttpStatusCodes;
import com.example.snortdroid.rules.enums.TcpFlags;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SnortRule  extends Rule implements Serializable{

    private String action;
    private String protocol;
    private String sourceNet,destNet;
    private int sourcePort,destPort;
    private String message;
    private int rev,sid;
    private String content;
    private int contentOffset;
    private int ttl;
    private HttpMethods httpMethod;
    private HttpStatusCodes httpStatusCode;
    private List<TcpFlags> tcpFlagsList=new ArrayList<>();
    private Direction direction;
    private String msgType;

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

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

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    @Override
    public String toString() {
        return  this.action+";"+this.protocol+";"+this.message+";"+this.sourceNet+";"+this.sourcePort+";"+this.destNet+";"+this.destPort;
    }

    public HttpMethods getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethods httpMethod) {
        this.httpMethod = httpMethod;
    }

    public HttpStatusCodes getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(HttpStatusCodes httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public List<TcpFlags> getTcpFlagsList() {
        return tcpFlagsList;
    }

    public void setTcpFlagsList(List<TcpFlags> tcpFlagsList) {
        this.tcpFlagsList = tcpFlagsList;
    }
    public void addToTcpFlagsList(String flag){
        tcpFlagsList.add(TcpFlags.valueOf(flag));
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
