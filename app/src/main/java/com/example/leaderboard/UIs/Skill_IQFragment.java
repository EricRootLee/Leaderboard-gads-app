package com.example.leaderboard.UIs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leaderboard.Adapters.SkillIqAdapter;
import com.example.leaderboard.DataSetModels.Skill_IQModel;
import com.example.leaderboard.R;
import com.example.leaderboard.retrofitApi.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Skill_IQFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ImageView mImageView;
    private TextView mTextView1;
    private TextView mTextView;



    public Skill_IQFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_skill__i_q,container,false);
        mRecyclerView = view.findViewById(R.id.recycler_view_skill_iq);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mTextView = view.findViewById(R.id.text_view_name);
        mTextView1 = view.findViewById(R.id.text_view_skill_iq_score_and_country);
        mImageView = view.findViewById(R.id.image_view_skill_IQ);

        getSkillIQ();
        return view;
    }


    // TODO: Rename and change types and number of parameters
    private void getSkillIQ() {
        ApiClient.getClient().getSkillIqLeaders().enqueue(new Callback<List<Skill_IQModel>>() {

            @Override
            public void onResponse(Call<List<Skill_IQModel>> call, Response<List<Skill_IQModel>> response) {
                if (response.isSuccessful()){
                    List<Skill_IQModel> skillModelList = response.body();
                    SkillIqAdapter skillIqAdapter = new SkillIqAdapter(getContext(), skillModelList);
                    mRecyclerView.setAdapter(skillIqAdapter);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                }
            }

            @Override
            public void onFailure(Call<List<Skill_IQModel>> call, Throwable t) {
                mTextView.setText(t.getMessage());
                mTextView1.setText(t.getMessage());
                mTextView.setVisibility(View.VISIBLE);
                mTextView1.setVisibility(View.VISIBLE);
            }
        });
    }
}