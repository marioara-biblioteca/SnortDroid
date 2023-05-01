package com.example.snortdroid.rules.yara;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {YaraRule.class},version = 1)
@TypeConverters(YaraStringsConverter.class)
public abstract class YaraRuleDb extends RoomDatabase {
    public abstract YaraRuleDAO yaraRuleDAO();
}
