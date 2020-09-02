package com.gadg.leaderboard.learner;


public class TopLearner extends Learner {
    private  String hours;

    public  TopLearner(String Name, String Country, String Hours,String Image_Url) {
        super(Name,Country,Image_Url);
        this.hours = Hours;
    }

    public String getHours() {
        return hours;
    }

    // Get the hours of learning and the country with same sentence
	public String getDetail() {
        return hours+ " learning hours, "+super.getCountry();
    }

}