package com.example.leaderboard.ui.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leaderboard.R;
import com.example.leaderboard.models.LearningLeader;
import com.example.leaderboard.models.SkillLeader;
import com.example.leaderboard.network.GadsApiService;
import com.example.leaderboard.network.RetrofitInstance;
import com.example.leaderboard.ui.main.dummy.DummyContent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 */
public class SkillTab extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SkillTab() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SkillTab newInstance(int columnCount) {
        SkillTab fragment = new SkillTab();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skill_tab_list, container, false);
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            final RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            GadsApiService gadsApiService = RetrofitInstance.getRetrofitInstance().create(GadsApiService.class);

            Call<List<SkillLeader>> call = gadsApiService.getSkillLeaders();

            call.enqueue(new Callback<List<SkillLeader>>() {
                @Override
                public void onResponse(Call<List<SkillLeader>> call, Response<List<SkillLeader>> response) {
                    recyclerView.setAdapter(new SkillRecyclerViewAdapter(getContext(),response.body()));
                    progressDialog.cancel();
                    Log.d("TAG","Response = "+response.body());
                }

                @Override
                public void onFailure(Call<List<SkillLeader>> call, Throwable t) {
                    Log.e("ERROR",t.getMessage());

                }
            });

        }
        return view;
    }
}