package com.alonz.reumanatlot.Natlot;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.alonz.reumanatlot.Natlot.Natla;
import com.alonz.reumanatlot.Natlot.NatlaAdapter;
import com.alonz.reumanatlot.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * Created by alonz on 15/11/2017.
 */

public class NatlotFragment extends Fragment {
    private RecyclerView recycleView;
    private ProgressBar pb;
    NatlaAdapter adapter;
    ArrayList<Natla> natlas;
    Context context;

    String[] s;

    public NatlotFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.natlot_recycler_view, container, false);
        Log.e("detach", "natlotfragment on create view");
        pb = rootView.findViewById(R.id.pb_natlot_list);
        pb.setVisibility(View.VISIBLE);
        String color;
        Bundle args = getArguments();
        adapter = new NatlaAdapter(getContext());
        int position = args.getInt("mPosition");

        switch (position){
            case 0:
                color="Red";
                break;
            case 1:
                color="Turquoise";
                break;
            case 2:
                color="Purple";
                break;
            case 3:
                color="Orange";
                break;
            case 4:
                color="White";
                break;
            case 5:
                color="LightTurquoise";
                break;
            case 6:
                color="Yellow";
                break;
            case 7:
                color="LightBlue";
                break;
            default:
                color="LightGreen";

        }


        recycleView =  rootView.findViewById(R.id.recyclerview);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference root = database.getReference("Natlot");
        DatabaseReference colorRoot = root.child(color);
        colorRoot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> values = (ArrayList<String>) dataSnapshot.getValue();
                Natla[] g;
                natlas = new ArrayList<>();
                for (String url: values){
                    Natla natla = new Natla(url);
                    natlas.add(natla);
                }
                s = values.toArray(new String[0]);
                context = getContext();
                recycleView.setAdapter(new NatlaAdapter(context, natlas));
                pb.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("hh", "Failed to read value.", error.toException());
            }
        });

        return rootView;
    }


}

