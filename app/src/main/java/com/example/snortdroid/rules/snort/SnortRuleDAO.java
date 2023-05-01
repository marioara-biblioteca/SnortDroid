package com.example.snortdroid.rules.snort;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.snortdroid.rules.yara.YaraRule;

import java.time.LocalDateTime;
import java.util.List;

@Dao
public interface SnortRuleDAO {
    @Insert
    void insertSnortRule(SnortRule rule);
    @Query("SELECT * FROM SnortRule")
    List<SnortRule> getAllSnortRules();
    @Query("SELECT * FROM SnortRule WHERE `action`= :action")
    List<SnortRule> getSnortRulesByAction(String action);
    @Query("SELECT * FROM SnortRule WHERE protocol= :protocol")
    List<SnortRule> getSnortRulesByProtocol(String protocol);
    @Query("UPDATE SnortRule SET rev = rev + 1 WHERE sid=:sid")
    void incrementRev(int sid);
    @Query("UPDATE SnortRule SET payload= :payload where sid= :sid")
    void updatePayload(String payload,int sid);
    @Query("SELECT * FROM SnortRule WHERE timestamp BETWEEN :minDate AND :maxDate")
    public List<SnortRule>getRulesByTimestamp(String minDate,String maxDate);

    @Delete
    void deleteSnortRule(SnortRule snortRule);
}
