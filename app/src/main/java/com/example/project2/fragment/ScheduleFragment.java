package com.example.project2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.project2.R;
import com.example.project2.adapter.ScheduleAdapter;
import com.example.project2.model.ScheduleEntryFilter;
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
    private Spinner daySpinner, groupSpinner;
    EditText filterText;

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
        initFilter(view);
    }

    private void initViewModel() {
        scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);
        scheduleViewModel.getScheduleEntries().observe(this,
                resource -> {});
        scheduleViewModel.getFilteredScheduleEntries().observe(this,
                scheduleEntryEntities -> scheduleAdapter.setData(scheduleEntryEntities));
        scheduleViewModel.getAllScheduleEntries().observe(this,
                scheduleEntryEntities -> scheduleAdapter.setData(scheduleEntryEntities));
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
        daySpinner = view.findViewById(R.id.sp_fragment_schedule_day);
        ArrayAdapter<CharSequence> daySpinnerAdapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.days_array, android.R.layout.simple_spinner_item);
        daySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(daySpinnerAdapter);
        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setFilter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // Init groups spinner
        groupSpinner = view.findViewById(R.id.sp_fragment_schedule_group);
        ArrayAdapter<CharSequence> groupSpinnerAdapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.groups_array, android.R.layout.simple_spinner_item);
        groupSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupSpinner.setAdapter(groupSpinnerAdapter);
        groupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setFilter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void initFilter(View view){

        Button filterButton = view.findViewById(R.id.btn_fragment_schedule_filter);
        filterText = view.findViewById(R.id.et_fragment_schedule_filter);
        filterButton.setOnClickListener(v -> {
            setFilter();
        });


    }

    private void setFilter(){
        String group = groupSpinner.getSelectedItem().toString();
        if(group.equalsIgnoreCase("GROUPS")){
            group = "";
        }
        String day = daySpinner.getSelectedItem().toString();
        if(day.equalsIgnoreCase("DAYS")){
            day = "";
        }
        String professorOrClassName = filterText.getText().toString();
//        if(professorOrClassName.equalsIgnoreCase("")){
//            professorOrClassName = null;
//        }
        ScheduleEntryFilter filter = new ScheduleEntryFilter(professorOrClassName, professorOrClassName, day, group, "");
        scheduleViewModel.setFilter(filter);
    }


}
