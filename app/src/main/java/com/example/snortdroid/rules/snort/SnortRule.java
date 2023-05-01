package com.example.snortdroid.rules.snort;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.snortdroid.rules.Rule;
import com.example.snortdroid.rules.enums.Direction;
import com.example.snortdroid.rules.enums.HttpMethods;
import com.example.snortdroid.rules.enums.HttpStatusCodes;
import com.example.snortdroid.rules.enums.TcpFlags;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SnortRule  extends Rule implements Serializable{
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="action")
    @NonNull
    private String action;
    @ColumnInfo(name="protocol")
    @NonNull
    private String protocol;
    @ColumnInfo(name="source_net")
    private String sourceNet;
    @ColumnInfo(name="destination_net")
    private String destNet;
    @ColumnInfo(name="source_port")
    private int sourcePort;
    @ColumnInfo(name="dest_port")
    private int destPort;
    @ColumnInfo(name="timestamp")
    private String timestamp;
    @ColumnInfo(name="payload")
    private String payload;
    private String message;
    private int rev,sid;
    private String content;
    private int contentOffset;

    private HttpMethods httpMethod;
    private HttpStatusCodes httpStatusCode;
    private String tcpFlagsList;
    private Direction direction;
    private int ttl;

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public SnortRule() {
        super();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.timestamp=dtf.format(LocalDateTime.now());
    }

    public String getTimestamp() {
        return timestamp;
    }

    public SnortRule(String action, String protocol, String sourceNet, String destNet, int sourcePort, int destPort, String message, int rev, int sid) {
        super();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.timestamp=dtf.format(LocalDateTime.now());
        this.action = action;
        this.protocol = protocol;
        this.sourceNet = sourceNet;
        this.destNet = destNet;
        this.sourcePort = sourcePort;
        this.destPort = destPort;
        this.message = message;
        this.rev = rev;
        this.sid = sid;
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

    public HttpMethods getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethods httpMethod) {
        this.httpMethod = httpMethod;
    }

    public HttpStatusCodes getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(HttpStatusCodes httpStatusCode) {this.httpStatusCode = httpStatusCode;}

    public String getTcpFlagsList() {
        return tcpFlagsList;
    }

    public void setTcpFlagsList(String tcpFlagsList) {
        this.tcpFlagsList = tcpFlagsList;
    }
    public void addToTcpFlagsList(String flag){
        if(!tcpFlagsList.contains(flag))
            tcpFlagsList+=flag;
    }
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public String getPayload(){

        payload= "(msg:" +message+";content:\"|"+content+"|\";offset:"+contentOffset+";flow:"+direction+"flags:"+tcpFlagsList+  ";sid:" +sid+";rev:"+rev+";)";
        return payload;
    }
    @Override
    public String toString() {
        return  timestamp+" "+ action+" "+protocol+" "+sourceNet+" "+sourcePort+" -> "+destNet+" "+destPort+" "+getPayload();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
