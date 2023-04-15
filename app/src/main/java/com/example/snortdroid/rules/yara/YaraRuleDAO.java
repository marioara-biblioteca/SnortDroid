package com.example.snortdroid.rules.yara;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

@Dao
public interface YaraRuleDAO {
    @Insert
    public void insertYaraRule(YaraRule rule);
    @Delete
    void deleteYaraRule(YaraRule yaraRule);
}
