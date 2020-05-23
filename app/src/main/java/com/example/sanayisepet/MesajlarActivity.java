package com.example.sanayisepet;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MesajlarActivity extends AppCompatActivity{


    List<String> otherIdList;
    String userID;
    SharedPreferences sharedPreferences;
    DatabaseReference reference;
    MesajlarAdapter mesajlarAdapter;
    ListView mesajlarlistview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesajlar);
        otherIdList = new ArrayList<>();

        sharedPreferences = this.getSharedPreferences("giris",0);
        userID = sharedPreferences.getString("uye_id",null);
        reference = FirebaseDatabase.getInstance().getReference();

        mesajlarAdapter = new MesajlarAdapter(otherIdList, userID, getApplicationContext(),MesajlarActivity.this);
        mesajlarlistview = findViewById(R.id.lvMesajlar);
        mesajlarlistview.setAdapter(mesajlarAdapter);

        Listele();
    }

    public  void Listele()
    {
        reference.child("messages").child(userID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.i("mesajlar", dataSnapshot.getKey());
                otherIdList.add(dataSnapshot.getKey());
                mesajlarAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
