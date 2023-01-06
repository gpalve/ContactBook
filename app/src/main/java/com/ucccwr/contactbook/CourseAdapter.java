package com.ucccwr.contactbook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    // creating a variable for array list and context.

    private ArrayList<com.ucccwr.contactbook.CourseModal> courseModalArrayList;
    private Context context;


    // creating a constructor for our variables.
    public CourseAdapter(ArrayList<com.ucccwr.contactbook.CourseModal> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }

    // method for filtering our recyclerview items.
    public void filterList(ArrayList<com.ucccwr.contactbook.CourseModal> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        courseModalArrayList = filterllist;

        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        com.ucccwr.contactbook.CourseModal modal = courseModalArrayList.get(position);

        holder.officerName.setText(modal.getOfficerNameName());
        holder.cugNo.setText(modal.getCugNo());
        holder.rlyNo.setText(modal.getrlyNo());
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our views.
        private TextView officerName, cugNo, rlyNo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our views with their ids.
            officerName = itemView.findViewById(R.id.idTVCourseName);
            cugNo = itemView.findViewById(R.id.idTVCourseDescription);
            rlyNo = itemView.findViewById(R.id.idRailwayNo);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + cugNo.getText()));
                    context.startActivity(intent);

//                    Intent intent = new Intent(context, DetailActivity.class);
//                    intent.putExtra("firstKeyName", officerName.getText());
//                    intent.putExtra("secondKeyName",cugNo.getText());
//                    intent.putExtra("secondKeyName",rlyNo.getText());
//                    context.startActivity(intent);
                }
            });
        }


    }
}
