package com.gadg.leaderboard.learner;


public class Learner {

    private  String name;
    private  String country;
    private String badgeUrl;


    public  Learner(String Name, String Country, String Image_Url) {
        this.name = Name;
        this.country = Country;
        this.badgeUrl = Image_Url;
    }


    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
	
    public String getbadgeUrl() {
        return badgeUrl;
    }
}