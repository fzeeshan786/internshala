package com.zzzz.internshalatask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ShowListFragment extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    MyDatabaseHelper myDatabaseHelper;
    ArrayList<String> book_id, book_title, book_author, book_pages;
    CustomAdapter customAdapter;
    ArrayAdapter<String> arrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_list, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);
        myDatabaseHelper = new MyDatabaseHelper(getContext());
        add_button = view.findViewById(R.id.floatingActionButton);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AddFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer,fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();

        //  storeDataInArrays();
        customAdapter = new CustomAdapter(getContext(), book_id, book_title, book_author, book_pages);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        customAdapter.notify();
//        customAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(customAdapter);

        // get data
        List<Product> list = myDatabaseHelper.getAllData();
        for(Product product:list)
        {
            book_id.add(String.valueOf(product.getId()));
            book_title.add(product.getBook_title());
            book_author.add(product.getBook_author());
            book_pages.add(String.valueOf(product.getBook_pages()));
        }
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){

            requireContext();

        }
    }
}