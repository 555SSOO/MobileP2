package com.example.project2.util;

import com.example.project2.repository.db.entity.ScheduleEntryEntity;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

public class ScheduleEntryDiffCallback extends DiffUtil.Callback {

    private List<ScheduleEntryEntity> oldList;
    private List<ScheduleEntryEntity> newList;

    public ScheduleEntryDiffCallback(List<ScheduleEntryEntity> _oldList, List<ScheduleEntryEntity> _newList){
        oldList = _oldList;
        newList = _newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
//        ScheduleEntryEntity oldMovie = oldList.get(oldItemPosition);
//        ScheduleEntryEntity newMovie = newList.get(newItemPosition);
//        return oldMovie.getId().equals(newMovie.getId());
        return areContentsTheSame(oldItemPosition, newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        ScheduleEntryEntity oldScheduleEntry = oldList.get(oldItemPosition);
        ScheduleEntryEntity newScheduleEntry = newList.get(newItemPosition);
        return oldScheduleEntry.getClassName().equals(newScheduleEntry.getClassName()) &&
                oldScheduleEntry.getClassroom().equals(newScheduleEntry.getClassroom()) &&
                oldScheduleEntry.getDay().equals(newScheduleEntry.getDay()) &&
                oldScheduleEntry.getGroups().equals(newScheduleEntry.getGroups()) &&
                oldScheduleEntry.getClassType().equals(newScheduleEntry.getClassType()) &&
                oldScheduleEntry.getProfessor().equals(newScheduleEntry.getProfessor()) &&
                oldScheduleEntry.getTime().equals(newScheduleEntry.getTime());

    }
}
