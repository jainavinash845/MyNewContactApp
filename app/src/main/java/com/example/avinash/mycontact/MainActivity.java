package com.example.avinash.mycontact;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecycleAdapter recyclerAdapter;
    private List<Contact> phoneList = new ArrayList<>();
    private RecycleListener listener;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycleview);





        //adapter
        recyclerAdapter = new RecycleAdapter(this, phoneList);
        recyclerView.setAdapter(recyclerAdapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //searchview

        SearchView searchView = findViewById(R.id.search);
        searchView.setQueryHint("search..");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }
        });


        //TouchIteam
        ItemTouchHelper.Callback callback = new SwipeUtil(this,recyclerAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        getSupportLoaderManager().initLoader(0, null, MainActivity.this);
        //        AsyncTaskRunner asyncTaskRunner = (AsyncTaskRunner) new AsyncTaskRunner().execute();
//        try {
//           get();
//
//           // List<Contact> li = asyncTaskRunner.execute("task runner").get();
//
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {

        Log.d("MainActivity", "onCreateLoader " + Thread.currentThread().getName());
        return new CursorLoader(this, ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " COLLATE NOCASE ASC");
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        Log.d("MainActivity", "onLoadFinished " + Thread.currentThread().getName());

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Contact contact = new Contact();
            contact.setName(name);
            contact.setPhone(phone);
            phoneList.add(contact);
        }
        //                if ((cursor != null ? cursor.getCount() : 0) > 0) {
//                    while (cursor != null && cursor.moveToNext()) {
//                        String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
//                        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//
//                        if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
//                            Cursor data = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                                    null
//                                    , ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}
//                                    , null, null);
//                            while (data.moveToNext()) {
//                                String phoneNo = data.getString(data.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                                Contact contact = new Contact();
//                                contact.setName(name);
//                                contact.setPhone(phoneNo);
//                                phoneList.add(contact);
//                            }
//                            data.close();
//                        }
//
//
//
//                    }
//
//                }
//
//
//
//            return phoneList;
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        Log.d("MainActivity", "onLoaderReset " + Thread.currentThread()
        );
    }


//    private class AsyncTaskRunner extends AsyncTask<String, String, List<Contact>> {
//
//
//        //ProgressDialog progressDialog;
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//           progressDialog = ProgressDialog.show(MainActivity.this,null,"Loading..",true,false);
//           try{
//               progressDialog.show();
//           }catch (Exception e){
//               e.printStackTrace();
//
//           }
//
////            progressDialog = new ProgressDialog(MainActivity.this);
////            progressDialog.setTitle("Loading...");
////            progressDialog.show();
//
//
//
//        }
//
//
//        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//        @Override
//        protected List<Contact> doInBackground(String... params) {
//           // publishProgress("running...");
//
//
//
//                ContentResolver contentResolver = MainActivity.this.getContentResolver();
//
//                Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " COLLATE NOCASE ASC");
//
//
////
//
//                if ((cursor != null ? cursor.getCount() : 0) > 0) {
//                    while (cursor != null && cursor.moveToNext()) {
//                        String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
//                        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//
//                        if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
//                            Cursor data = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                                    null
//                                    , ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}
//                                    , null, null);
//                            while (data.moveToNext()) {
//                                String phoneNo = data.getString(data.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                                Contact contact = new Contact();
//                                contact.setName(name);
//                                contact.setPhone(phoneNo);
//                                phoneList.add(contact);
//                            }
//                            data.close();
//                        }
//
//
//
//                    }
//
//                }
//
//
//
//            return phoneList;
//
//
//        }
//
//
//        @Override
//        protected void onPostExecute(List<Contact> contacts) {
//            super.onPostExecute(contacts);
//
//            if(MainActivity.this.phoneList!=null){
//                progressDialog.dismiss();
//            }
//            else
//                MainActivity.this.phoneList=contacts;
//
//
//
//        }
//
//    }
}














