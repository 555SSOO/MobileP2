package com.example.project2.repository.db;

import android.content.Context;

import com.example.project2.repository.db.dao.ScheduleDao;
import com.example.project2.repository.db.entity.ScheduleEntryEntity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ScheduleEntryEntity.class}, version = 1)
public abstract class ScheduleDatabase extends RoomDatabase {

    private static volatile ScheduleDatabase DATABASE;

    private static final String DATABASE_NAME = "schedule.db";

    public abstract ScheduleDao getScheduleDao();

    public static ScheduleDatabase getDb(Context context) {
        if (DATABASE == null) {
            synchronized (ScheduleDatabase.class) {
                if (DATABASE == null) {
                    DATABASE = Room.databaseBuilder(context, ScheduleDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .fallbackToDestructiveMigrationOnDowngrade()
                            .build();
                }
            }
        }

        return DATABASE;
    }
}
