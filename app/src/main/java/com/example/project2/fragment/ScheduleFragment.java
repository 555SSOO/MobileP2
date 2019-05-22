package com.example.project2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.example.project2.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ScheduleFragment extends Fragment {

    private Spinner mCategorySpinner;


    public static ScheduleFragment newInstance() {
        return new ScheduleFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

//        EditText expenseNameET = view.findViewById(R.id.et_fragment_first_name);
//        EditText priceET = view.findViewById(R.id.et_fragment_first_price);
//        mCategorySpinner = view.findViewById(R.id.sp_fragment_first_category);
//        Button button = view.findViewById(R.id.btn_fragmnet_first_add);
//        button.setOnClickListener(v -> {
//
//        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//
//        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
//        mViewModel.getExpensesLiveData().observe(getViewLifecycleOwner(),
//                expenses -> Toast.makeText(FirstFragment.this.getContext(), expenses.size()+"", Toast.LENGTH_SHORT).show());
//
//        mViewModel.getCategoriesLiveData().observe(getViewLifecycleOwner(),
//                categories -> {
//                    // Init spinner array over all categories
//                    ArrayAdapter<Category> adapter =
//                            new ArrayAdapter<>(FirstFragment.this.getContext(), android.R.layout.simple_spinner_dropdown_item, categories);
//                    adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
//
//                    mCategorySpinner.setAdapter(adapter);
//                });

    }
}
