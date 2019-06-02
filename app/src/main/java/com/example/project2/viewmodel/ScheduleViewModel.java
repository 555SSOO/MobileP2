package com.example.project2.viewmodel;

import android.app.Application;

import com.example.project2.model.ScheduleEntryFilter;
import com.example.project2.repository.ScheduleRepository;
import com.example.project2.repository.db.entity.ScheduleEntryEntity;
import com.example.project2.repository.web.model.Resource;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class ScheduleViewModel extends AndroidViewModel {

    private ScheduleRepository scheduleRepository;
    private MutableLiveData<ScheduleEntryFilter> scheduleEntryFilterMutableLiveData;
    private LiveData<List<ScheduleEntryEntity>> scheduleEntryEntitiesFilteredLiveData;

    public ScheduleViewModel(@NonNull Application application) {
        super(application);
        scheduleRepository = new ScheduleRepository(application);
        scheduleEntryFilterMutableLiveData = new MutableLiveData<>();
        scheduleEntryEntitiesFilteredLiveData = Transformations.switchMap(scheduleEntryFilterMutableLiveData,
                filter -> scheduleRepository.getFilteredScheduleEntries(filter));
    }

    public LiveData<Resource<Void>> getScheduleEntries() {
        return  scheduleRepository.getSchedule();
    }

    public LiveData<List<ScheduleEntryEntity>> getFilteredScheduleEntries(){
        return scheduleEntryEntitiesFilteredLiveData;
    }

    public LiveData<List<ScheduleEntryEntity>> getAllScheduleEntries() {
        return scheduleRepository.getAllScheduleEntries();
    }

    public void setFilter(ScheduleEntryFilter filter) {
        scheduleEntryFilterMutableLiveData.setValue(filter);
    }

}
