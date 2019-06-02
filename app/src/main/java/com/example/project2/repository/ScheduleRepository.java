package com.example.project2.repository;

import android.app.Application;
import android.util.Log;

import com.example.project2.model.ScheduleEntryFilter;
import com.example.project2.repository.db.ScheduleDatabase;
import com.example.project2.repository.db.dao.ScheduleDao;
import com.example.project2.repository.db.entity.ScheduleEntryEntity;
import com.example.project2.repository.web.api.ScheduleApi;
import com.example.project2.repository.web.model.Resource;
import com.example.project2.repository.web.model.ScheduleApiModel;
import com.example.project2.util.Random;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleRepository {

    private static final String TAG = "ScheduleRepository";

    private ScheduleApi scheduleApi;
    private ScheduleDao scheduleDao;
    private MutableLiveData<Resource<Void>> mResourceLiveData;
    private ExecutorService mExecutorService;

    public ScheduleRepository(Application application) {
        scheduleApi = new ScheduleApi();
        scheduleDao = ScheduleDatabase.getDb(application).getScheduleDao();
        mResourceLiveData = new MutableLiveData<>();
        mExecutorService = Executors.newCachedThreadPool();
    }

    public LiveData<Resource<Void>> getSchedule(){
        scheduleApi.getSchedule().enqueue(new Callback<List<ScheduleApiModel>>() {
            @Override
            public void onResponse(Call<List<ScheduleApiModel>> call, Response<List<ScheduleApiModel>> response) {
                // Notify UI that data was fetched successfully
                notifyResult(true);
                // Insert response into db so all observers can get movie data
                insertScheduleEntries(response.body());
            }

            @Override
            public void onFailure(Call<List<ScheduleApiModel>> call, Throwable t) {
                // Notify UI that data fetch failed
                notifyResult(false);
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });
        return mResourceLiveData;
    }

    public LiveData<List<ScheduleEntryEntity>> getFilteredScheduleEntries(ScheduleEntryFilter filter) {
        return scheduleDao.getFilteredScheduleEntries(filter.getTitle(), filter.getReleaseDate());
    }

    public LiveData<List<ScheduleEntryEntity>> getAllScheduleEntries() {
        return scheduleDao.getAllScheduleEntries();
    }

    public LiveData<ScheduleEntryEntity> getScheduleEntryById(String id) {
        return scheduleDao.getScheduleEntryById(id);
    }

    private void notifyResult(boolean isSuccessful){
        // Since we are storing movies in a database, there is no need to send
        // the response we got from the server to the activity, we'll just wait
        // for the data to be inserted into db, and at that moment all observers observing
        // movie table will be notified. So we just send Void, and a flag if fetch was successful.
        Resource<Void> resource = new Resource<>(null, isSuccessful);
        mResourceLiveData.setValue(resource);
    }

    private void insertScheduleEntries(List<ScheduleApiModel> scheduleApiModels) {
        // We need to transform api model to entity before storing data
        List<ScheduleEntryEntity> scheduleEntryEntities = transformApiModelToEntity(scheduleApiModels);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                scheduleDao.insertScheduleEntries(scheduleEntryEntities);
            }
        });
    }

    private List<ScheduleEntryEntity> transformApiModelToEntity(List<ScheduleApiModel> scheduleApiModels) {
        List<ScheduleEntryEntity> scheduleEntryEntities = new ArrayList<>();

        for (ScheduleApiModel scheduleApiModel: scheduleApiModels) {

            String className = scheduleApiModel.getClassName();
            String classType = scheduleApiModel.getClassType();
            String professor = scheduleApiModel.getProfessor();
            String groups = scheduleApiModel.getGroups();
            String day = scheduleApiModel.getDay();
            String time = scheduleApiModel.getTime();
            String classroom = scheduleApiModel.getClassroom();

//
//            int rtScore = Integer.parseInt(rtScoreString);
            String uid = Random.getRandomUID();
            ScheduleEntryEntity scheduleEntryEntity = new ScheduleEntryEntity(uid, className, classType, professor, groups, day, time, classroom);
            scheduleEntryEntities.add(scheduleEntryEntity);
        }
        return scheduleEntryEntities;
    }
}
