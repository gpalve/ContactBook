package com.ucccwr.contactbook;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String DB_PATH = "data/data/com.contactbook.ucccwr/databases/";
    private static final String DB_NAME = "mmctphonedirectory";
    File DB_FILE = new File(DB_PATH + DB_NAME);

    // creating variables for
    // our ui components.
    private RecyclerView courseRV;

    // variable for our adapter
    // class and array list
    private CourseAdapter adapter;
    private ArrayList<com.ucccwr.contactbook.CourseModal> courseModalArrayList;
    String designation , cug , rly;
    public static String PACKAGE_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PACKAGE_NAME = getApplicationContext().getPackageName();
        Log.d("packageee",PACKAGE_NAME);


       getSupportActionBar().setHomeAsUpIndicator(R.drawable.contact);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(!DB_FILE.exists()){
            // create db only if it's not present in internal storage and this function is visted only once.
            com.ucccwr.contactbook.copyDB.setDefaultDataBase(this);
        }

        // initializing our variables.
        courseRV = findViewById(R.id.idRVCourses);

        // calling method to
        // build recycler view.
        buildRecyclerView();
    }

    // calling on create option menu
    // layout to inflate our menu file.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // below line is to get our inflater
        MenuInflater inflater = getMenuInflater();

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search_menu, menu);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        // getting search view of our item.
        SearchView searchView = (SearchView) searchItem.getActionView();

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });
        return true;
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<com.ucccwr.contactbook.CourseModal> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (com.ucccwr.contactbook.CourseModal item : courseModalArrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getOfficerNameName().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filterList(filteredlist);
        }
    }

    private void buildRecyclerView() {

        // below line we are creating a new array list
        courseModalArrayList = new ArrayList<>();

        // open db and start getting data
        SQLiteDatabase mydatabase = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
        Log.d("dbConnected","yes");

        Cursor result = mydatabase.rawQuery("Select * from mmctcontacts", null);
        Log.d("mymsg", "Connected");
        result.moveToFirst();

        do {
            designation = result.getString(2);
            cug = result.getString(3);
            rly = result.getString(4);
            result.moveToNext();
            //list.add("\n"+"\uD83E\uDD35\uD83C\uDFFB\u200D" + stnCode + "\n\uD83D\uDCF1"+stnName + " / ☎️" + rly +"\n") ;
            //list.add( designation +"\n\n" + "\uD83D\uDCF1 "+ cug +"\n" + "☎ " + rly + "\n");

            courseModalArrayList.add(new com.ucccwr.contactbook.CourseModal(" "+designation, "   \uD83D\uDCDE "+cug,"☎️"+rly));

        } while (result.isAfterLast() == false);


        // below line is to add data to our array list.
//        courseModalArrayList.add(new CourseModal("DSA", "DSA Self Paced Course"));
//        courseModalArrayList.add(new CourseModal("JAVA", "JAVA Self Paced Course"));
//        courseModalArrayList.add(new CourseModal("\uD83D\uDFE2 C++", "C++ Self Paced Course"));
//        courseModalArrayList.add(new CourseModal("Python", "Python Self Paced Course"));
//        courseModalArrayList.add(new CourseModal("Fork CPP", "Fork CPP Self Paced Course"));
//        courseModalArrayList.add(new CourseModal("DSA", "DSA Self Paced Course"));
//        courseModalArrayList.add(new CourseModal("JAVA", "JAVA Self Paced Course"));
//        courseModalArrayList.add(new CourseModal("C++", "C++ Self Paced Course"));
//        courseModalArrayList.add(new CourseModal("Python", "Python Self Paced Course"));
//        courseModalArrayList.add(new CourseModal("Fork CPP", "Fork CPP Self Paced Course"));

        // initializing our adapter class.
        adapter = new CourseAdapter(courseModalArrayList, MainActivity.this);

        // adding layout manager to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        courseRV.setHasFixedSize(true);

        // setting layout manager
        // to our recycler view.
        courseRV.setLayoutManager(manager);

        // setting adapter to
        // our recycler view.
        courseRV.setAdapter(adapter);
    }
}
