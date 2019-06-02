package com.example.project2.repository.db.dao;

import com.example.project2.repository.db.entity.ScheduleEntryEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertScheduleEntries(List<ScheduleEntryEntity> scheduleEntryEntities);

    @Query("SELECT * FROM schedule_entries")
    LiveData<List<ScheduleEntryEntity>> getAllScheduleEntries();

    @Query("SELECT * FROM schedule_entries " +
            "WHERE predmet LIKE :className || '%' " +
            "AND nastavnik LIKE :professor")
    LiveData<List<ScheduleEntryEntity>> getFilteredScheduleEntries(String className, String professor);

    @Query("SELECT * FROM schedule_entries WHERE id LIKE :id")
    LiveData<ScheduleEntryEntity> getScheduleEntryById(String id);

}
