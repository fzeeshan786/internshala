package com.zzzz.internshalatask;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateFragment extends Fragment {
    EditText id_input,title_input,author_input,pages_input;
    Button update_button, delete_button;
    MyDatabaseHelper myDatabaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update, container, false);

        myDatabaseHelper = new MyDatabaseHelper(getContext());

        id_input = view.findViewById(R.id.id_input2);
        title_input = view.findViewById(R.id.title_input2);
        author_input = view.findViewById(R.id.author_input2);
        pages_input = view.findViewById(R.id.pages_input2);
        update_button = view.findViewById(R.id.update_button);
        delete_button = view.findViewById(R.id.delete_button);

        // use for get data
        Bundle b = getArguments();
        id_input.setText(""+b.getString("id"));
        title_input.setText(""+b.getString("title"));
        author_input.setText(""+b.getString("author"));
        pages_input.setText(""+b.getString("pages"));

        // #################################### UPDATE BUTTON ###############################
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int id = Integer.parseInt(id_input.getText().toString());
                String book_name = title_input.getText().toString();
                String author_name = author_input.getText().toString();
                int pages_name = Integer.parseInt(pages_input.getText().toString());

                Product product = new Product(id,book_name,author_name,pages_name);
                long result = myDatabaseHelper.updateData(product);
                if (result>0)
                {
                    Toast.makeText(getContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "Not Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // #################################### DELETE BUTTON ###############################
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ab = new AlertDialog.Builder(requireContext());
                ab.setTitle("Alert");
                ab.setMessage("Do you want to delete");

                ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = Integer.parseInt(id_input.getText().toString());

                        long result = myDatabaseHelper.deleteRecord(id);
                        if (result > 0) {
                            Toast.makeText(requireContext(), "Delete Record", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(requireContext(), "Query Problem", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                ab.show();
            }
        });

        return view;
    }
}