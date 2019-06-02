package com.example.project2.viewmodel;

import android.app.Application;

import com.example.project2.repository.ScheduleRepository;
import com.example.project2.repository.db.entity.ScheduleEntryEntity;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ScheduleEntryDetailsViewModel extends AndroidViewModel {

    private ScheduleRepository scheduleRepository;

    public ScheduleEntryDetailsViewModel(@NonNull Application application) {
        super(application);
        scheduleRepository = new ScheduleRepository(application);
    }

    public LiveData<ScheduleEntryEntity> getScheduleEntryById(String id) {
        return  scheduleRepository.getScheduleEntryById(id);
    }
}
