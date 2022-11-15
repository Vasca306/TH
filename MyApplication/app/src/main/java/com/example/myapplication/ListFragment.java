package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {


    View v;
    RecyclerView recyclerView;
    List<ModalClass> mList;
    CustomAdapter customAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mList = new ArrayList<>();
        mList.add(new ModalClass(R.drawable.cs, "Trịnh Trần Phương Tuấn", "Jack", "Việt Nam", "6"));
        mList.add(new ModalClass(R.drawable.cs1, "Nguyễn Phước Thịnh", "Noo", "Việt Nam", "43"));
        mList.add(new ModalClass(R.drawable.cs2, "Nguyễn Việt Hoàng", "Mono", "Việt Nam", "8"));
        mList.add(new ModalClass(R.drawable.cs3, "Hồ Thị Ngọc Hà", "Hà", "Việt Nam", "10"));
        mList.add(new ModalClass(R.drawable.cs4, "Phạm Đan Trường", "Trường", "Việt Nam", "21"));
        mList.add(new ModalClass(R.drawable.cs5, "Nguyễn Thị N", "Soobin", "Việt Nam", "22"));
        mList.add(new ModalClass(R.drawable.cs7, "Nguyễn Thị M", "M1", "Việt Nam", "12"));
        mList.add(new ModalClass(R.drawable.cs4, "Nguyễn Thị E", "E", "Việt Nam", "11"));
        mList.add(new ModalClass(R.drawable.cs3, "Nguyễn Thị Ái", "AI", "Việt Nam", "61"));
        mList.add(new ModalClass(R.drawable.cs4, "Nguyễn  Nguyên", "NGUYEN", "Việt Nam", "156"));

    }


    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView=v.findViewById(R.id.recyclerViewID);

        customAdapter = new CustomAdapter(mList, getContext());
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return v;
    }



}