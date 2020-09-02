package com.gadg.leaderboard;


import com.gadg.leaderboard.learner.TopLearner;
import com.gadg.leaderboard.learner.TopSkillLearner;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface LearnerListService {
	//Get the list of the learner leaders
	@GET("hours")
	Call<ArrayList<TopLearner>> getTopLearnersList();

	//Get the list of the Skill IQ leaders
	@GET("skilliq")
	Call<ArrayList<TopSkillLearner>> getTopSkillsIQLearnersList();

	//Because the Base Url is different so we add the Url arguments
	@POST
    @FormUrlEncoded
    Call<Void> sendProject(@Url String url,
	                    @Field("entry.1877115667") String firstName,
                        @Field("entry.2006916086") String lastName,
                        @Field("entry.1824927963") String email,
						@Field("entry.284483984") String projectLink);

}