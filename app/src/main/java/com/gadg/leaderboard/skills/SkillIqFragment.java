package com.gadg.leaderboard.skills;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gadg.leaderboard.LearnerListService;
import com.gadg.leaderboard.R;
import com.gadg.leaderboard.ServiceBuilder;
import com.gadg.leaderboard.leaders.LearningFragment;
import com.gadg.leaderboard.learner.TopLearner;
import com.gadg.leaderboard.learner.TopSkillLearner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillIqFragment extends Fragment {
    private ArrayList<TopSkillLearner> mLearner;
    private RecyclerView mRecyclerView;
    private LearningSkillIQAdapter mAdapter;
    private ProgressBar mProgressBar;


    public SkillIqFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learning, container, false);
        mRecyclerView = view.findViewById(R.id.learning_recycler);
        mProgressBar = view.findViewById(R.id.progress_bar);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mLearner = new ArrayList<TopSkillLearner>();
        mAdapter = new LearningSkillIQAdapter(getActivity(), mLearner);
        mRecyclerView.setAdapter(mAdapter);
        printTheListOfTopLearners();
        // Inflate the layout for this fragment
        return view;
    }

    private void printTheListOfTopLearners() {
        LearnerListService taskService = ServiceBuilder.buildeService(LearnerListService.class);
        Call<ArrayList<TopSkillLearner>> call = taskService.getTopSkillsIQLearnersList();
        call.enqueue(new Callback<ArrayList<TopSkillLearner>>() {
            @Override
            public void onResponse(Call<ArrayList<TopSkillLearner>> request, Response<ArrayList<TopSkillLearner>> response) {
                if (response.isSuccessful()) {
                    mLearner = response.body();
                    Collections.sort(mLearner, new Comparator<TopSkillLearner>() {
                        @Override
                        public int compare(TopSkillLearner t1, TopSkillLearner t2) {
                            return Integer.parseInt(t2.getscore()) - Integer.parseInt(t1.getscore());
                        }
                    });
                    mAdapter.notifyDataSetChanged();
                    mAdapter = new LearningSkillIQAdapter(getActivity(), mLearner);
                    mRecyclerView.setAdapter(mAdapter);

                } else if (response.code() == 401) {
                    Toast.makeText(getContext(), "Your session has expired", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Failed to retrieve items", Toast.LENGTH_LONG).show();
                }
                mProgressBar.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<ArrayList<TopSkillLearner>> request, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                if (t instanceof IOException) {
                    Toast.makeText(getContext(), "A connection error occured", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Failed to retrieve items", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        printTheListOfTopLearners();
    }

}