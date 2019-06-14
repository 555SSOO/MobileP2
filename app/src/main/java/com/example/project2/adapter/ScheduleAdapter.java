package com.example.project2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project2.R;
import com.example.project2.activity.ScheduleDetailsActivity;
import com.example.project2.repository.db.entity.ScheduleEntryEntity;
import com.example.project2.util.ScheduleEntryDiffCallback;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleHolder> {

    private List<ScheduleEntryEntity> dataSet;
    public Context c;
//    private OnItemClickedListener onItemClickedListener;

    public ScheduleAdapter() {
        dataSet = new ArrayList<>();
    }

    @NonNull
    @Override
    public ScheduleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.schedule_list_item, parent, false);
        return new ScheduleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleHolder holder, int position) {
        ScheduleEntryEntity scheduleEntry = dataSet.get(position);
        holder.classNameTv.setText(String.format("%s - %s", scheduleEntry.getClassName(), scheduleEntry.getClassType()));
        holder.professorTv.setText(scheduleEntry.getProfessor());
        holder.classroomTv.setText(scheduleEntry.getClassroom());
        holder.groupTv.setText(scheduleEntry.getGroups());
        holder.timeTv.setText(String.format("%s\n%s", scheduleEntry.getDay(), scheduleEntry.getTime()));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void setData(List<ScheduleEntryEntity> scheduleEntries) {
        ScheduleEntryDiffCallback callback = new ScheduleEntryDiffCallback(dataSet, scheduleEntries);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        dataSet.clear();
        dataSet.addAll(scheduleEntries);
        result.dispatchUpdatesTo(this);
    }

    public class ScheduleHolder extends RecyclerView.ViewHolder {

        TextView classNameTv;
        TextView professorTv;
        TextView classroomTv;
        TextView groupTv;
        TextView timeTv;

        public ScheduleHolder(@NonNull View itemView) {
            super(itemView);

            classNameTv = itemView.findViewById(R.id.tv_list_item_class_name);
            professorTv = itemView.findViewById(R.id.tv_list_item_professor);
            classroomTv = itemView.findViewById(R.id.tv_list_item_classroom);
            groupTv = itemView.findViewById(R.id.tv_list_item_groups);
            timeTv = itemView.findViewById(R.id.tv_list_item_time);

            classNameTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(c, ScheduleDetailsActivity.class);
                    intent.putExtra(ScheduleDetailsActivity.ASD, classNameTv.getText().toString());
                    c.startActivity(intent);
                }
            });
        }
    }

//    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
//        mOnItemClickedListener = onItemClickedListener;
//    }
//
//    public interface OnItemClickedListener {
//        void onItemClicked(MovieEntity movieEntity);
//    }
}
