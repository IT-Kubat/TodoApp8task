package com.example.todoapp.ui.firebase;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todoapp.R;
import com.example.todoapp.model.Work;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirebaseFragment extends Fragment {

    List<Work> works;
    RecyclerView recyclerView;
    FirebaseFirestore firebaseFirestore;
    AdapterFirebase adapterFirebase;
    Work work;


    public FirebaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_firebase, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        work = (Work) getActivity().getIntent().getSerializableExtra("work");
        recyclerView = view.findViewById(R.id.recycler_view_firebase);
        works = new ArrayList<>();
        adapterFirebase = new AdapterFirebase(works);
        recyclerView.setAdapter(adapterFirebase);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("works").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()){
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d: list){
                                Work work = d.toObject(Work.class);
                                works.add(work);
                            }
                            adapterFirebase.notifyDataSetChanged();
                        }
                    }
                });

    }
}
