package com.example.project2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.project2.R;
import com.example.project2.adapter.ScheduleAdapter;
import com.example.project2.viewmodel.ScheduleViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ScheduleFragment extends Fragment {

    private ScheduleViewModel scheduleViewModel;
    private ScheduleAdapter scheduleAdapter;
    private RecyclerView scheduleRv;

    public static ScheduleFragment newInstance() {
        return new ScheduleFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initViewModel();
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        initUi(view);
        return view;
    }

    private void initUi(View view) {
        initSpinners(view);
        initRecycler(view);
    }

    private void initViewModel() {
        scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);
        scheduleViewModel.getScheduleEntries().observe(this,
                resource -> {});
        scheduleViewModel.getFilteredScheduleEntries().observe(this,
                movieEntities -> scheduleAdapter.setData(movieEntities));
        scheduleViewModel.getAllScheduleEntries().observe(this,
                movieEntities -> scheduleAdapter.setData(movieEntities));
    }

    private void initRecycler(View view) {
        scheduleRv = view.findViewById(R.id.rv_fragment_schedule);
        scheduleAdapter = new ScheduleAdapter();
//        mAdapter.setOnItemClickedListener(new ScheduleAdapter().OnItemClickedListener() {
//            @Override
//            public void onItemClicked(MovieEntity movieEntity) {
//                Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
//                intent.putExtra(MovieDetailsActivity.MOVIE_ID_KEY, movieEntity.getId());
//                startActivity(intent);
//            }
//        });
        scheduleRv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        scheduleRv.setAdapter(scheduleAdapter);
    }

    private void initSpinners(View view){
        // Init day spinner
        Spinner daySpinner = view.findViewById(R.id.sp_fragment_schedule_day);
        ArrayAdapter<CharSequence> daySpinnerAdapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.days_array, android.R.layout.simple_spinner_item);
        daySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(daySpinnerAdapter);

        // Init groups spinner
        Spinner groupSpinner = view.findViewById(R.id.sp_fragment_schedule_group);
        ArrayAdapter<CharSequence> groupSpinnerAdapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.groups_array, android.R.layout.simple_spinner_item);
        groupSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupSpinner.setAdapter(groupSpinnerAdapter);
    }

}
