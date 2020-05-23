package com.example.sanayisepet;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    String userID, otherID, userTable, otherTable, key;
    SharedPreferences sharedPreferences;
    DatabaseReference reference;
    EditText etMesaj;
    Button sendMesajButton;
    List<MesajModel> list;
    MesajAdapter adapter;
    RecyclerView mesajListview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        tanimla();
        click();
        load();
    }

    public void tanimla() {
        etMesaj = findViewById(R.id.mesajEditText);
        sendMesajButton = findViewById(R.id.sendMesajButton);
        reference = FirebaseDatabase.getInstance().getReference();
        otherID = DigerID.getOtherID();
        sharedPreferences = this.getSharedPreferences("giris", 0);
        userID = sharedPreferences.getString("uye_id", null);
        list = new ArrayList<>();
        adapter = new MesajAdapter(list,getApplicationContext(),userID);
        mesajListview = findViewById(R.id.mesajListView);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        mesajListview.setLayoutManager(manager);
        mesajListview.setAdapter(adapter);

    }

    public void click()
    {
        sendMesajButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(etMesaj.getText().toString(),userID,otherID);
            }
        });

    }

    public void sendMessage(String mesajbody, String UsrId, String othId) {
        userTable = "messages/" + userID + "/" + otherID;
        otherTable = "messages/" + otherID + "/" + userID;
        key = reference.child("messages").child(userTable).child(otherTable).push().getKey();

        Log.i("keyim", key);
        Map map = send(mesajbody, UsrId, othId);
        Map map2 = new HashMap();
        map2.put(userTable + "/" + key, map);
        map2.put(otherTable + "/" + key, map);

        etMesaj.setText("");
        reference.updateChildren(map2, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

            }
        });

    }

    public Map send(String mesajbody, String userId, String otherId) {
        Map msj = new HashMap();
        msj.put("mesaj", mesajbody);
        msj.put("from", userId);
        msj.put("to", otherId);
        return msj;
    }

    public void load ()
    {
        reference.child("messages").child(userID).child(otherID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                MesajModel m = dataSnapshot.getValue(MesajModel.class);
                list.add(m);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                MesajModel m = dataSnapshot.getValue(MesajModel.class);
                list.add(m);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                MesajModel m = dataSnapshot.getValue(MesajModel.class);
                list.add(m);
                adapter.notifyDataSetChanged();
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
