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
        mList.add(new ModalClass(R.drawable.ip07, "Iphone 7"));
        mList.add(new ModalClass(R.drawable.ip11, "Iphone 11"));
        mList.add(new ModalClass(R.drawable.ip12, "Iphone 12"));
        mList.add(new ModalClass(R.drawable.ip13, "Iphone 13"));
        mList.add(new ModalClass(R.drawable.ip14, "Iphone 14"));
        mList.add(new ModalClass(R.drawable.ip14, "Iphone 15"));
        mList.add(new ModalClass(R.drawable.ip13, "Iphone 16"));
        mList.add(new ModalClass(R.drawable.ip12, "Iphone 17"));
        mList.add(new ModalClass(R.drawable.ip11, "Iphone 18"));
        mList.add(new ModalClass(R.drawable.ip07, "Iphone 19"));

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