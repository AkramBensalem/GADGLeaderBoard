package com.gadg.leaderboard.learner;


public class TopSkillLearner extends Learner{
    private  String score;

    public  TopSkillLearner(String Name, String Country, String Score,String Image_Url) {
        super(Name,Country,Image_Url);
        this.score = Score;
    }

    public String getscore() {
        return score;
    }

    // Get the score of learning and the country with same sentence
	public String getDetail() {
        return score+ " skill IQ Scores, "+super.getCountry();
    }

}