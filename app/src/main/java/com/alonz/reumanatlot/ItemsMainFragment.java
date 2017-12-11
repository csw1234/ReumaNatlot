package com.alonz.reumanatlot;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alonz.reumanatlot.Natlot.Natla;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ItemsMainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ItemsMainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemsMainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    int mPosition = 0;
    String[] urlStrings;
    ImageView imageView;
    ProgressBar pb;
    Button[] mButtons;

    private OnFragmentInteractionListener mListener;

    public ItemsMainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemsMainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemsMainFragment newInstance(String param1, String param2) {
        ItemsMainFragment fragment = new ItemsMainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {
;
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_items_main, container, false);
        Button color1 = view.findViewById(R.id.color1);
        Button color2 = view.findViewById(R.id.color2);
        Button color3 = view.findViewById(R.id.color3);
        Button color4 = view.findViewById(R.id.color4);
        Button color5 = view.findViewById(R.id.color5);
        Button color6 = view.findViewById(R.id.color6);
        Button color7 = view.findViewById(R.id.color7);
        Button color8 = view.findViewById(R.id.color8);
        Button color9 = view.findViewById(R.id.color9);
        Button color10 = view.findViewById(R.id.color10);
        Button color11 = view.findViewById(R.id.color11);
        Button color12 = view.findViewById(R.id.color12);
        imageView= view.findViewById(R.id.meyham);
        pb = view.findViewById(R.id.pb_meyham);
        final Button[] buttons ={color1,color2,color3,color4,color5,color6,color7,color8,color9,color10,color11,color12};
        mButtons=buttons;

        String item = mParam1;

        //Create Firebase call to get Array of Photos Urls
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference root = database.getReference(item);
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> values = (ArrayList<String>) dataSnapshot.getValue();
                String[] s;
                s = values.toArray(new String[0]);
                collection(s);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("hh", "Failed to read value.", error.toException());
            }
        });

        switch (item){
            case "Kidush":
                color1.setBackground(getResources().getDrawable(R.drawable.reuma246));
                color2.setBackground(getResources().getDrawable(R.drawable.reuma246));
                color3.setBackgroundColor(Color.BLUE);
                color4.setBackgroundColor(Color.BLUE);
                color5.setBackgroundColor(Color.YELLOW);
        }

        for(int i=0; i<buttons.length-1;i++){
            final int position = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pb.setVisibility(View.VISIBLE);
                    mPosition=position;
                    picasso();
                }
            });
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        FullScreenFragment fullScreenFragment = FullScreenFragment.newInstance(urlStrings[mPosition],null);
                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        activity.getSupportFragmentManager().beginTransaction().add(R.id.content, fullScreenFragment).addToBackStack(null).commit();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            Toast.makeText(context,"Item Fragment", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private void collection(String[] s){
        urlStrings=s;
        picasso();
    }


    private void picasso(){
        //0Glide.with(this).load(gg[mPosition]).into(holderImageView);
        Picasso.with(getContext()).load(urlStrings[mPosition]).into(imageView, new com.squareup.picasso.Callback(){
            @Override
            public void onSuccess() {
                pb.setVisibility(View.INVISIBLE);
                buttonsToVisible(urlStrings.length);
            }
            @Override
            public void onError() {

            }
        });

    }


    private void buttonsToVisible(int size){
        for (int i=0; i<size; i++){
            mButtons[i].setVisibility(View.VISIBLE);
        }
    }
}
