package com.zzzz.internshalatask;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddFragment extends Fragment {
    EditText id_input;
    EditText title_input, author_input, pages_input;
    Button add_button;
    MyDatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        databaseHelper = new MyDatabaseHelper(getContext());

        id_input = view.findViewById(R.id.id_input);
        title_input = view.findViewById(R.id.title_input);
        author_input = view.findViewById(R.id.author_input);
        pages_input = view.findViewById(R.id.pages_input);
        add_button = view.findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int id = Integer.parseInt(id_input.getText().toString());
                String title = title_input.getText().toString();
                String author = author_input.getText().toString();
                int pages = Integer.parseInt(pages_input.getText().toString());

                Product product = new Product(id,title,author,pages);
                long result = databaseHelper.insertBook(product);

                if (result>0)
                {
                    Toast.makeText(getContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "Not Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}