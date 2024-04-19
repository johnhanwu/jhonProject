package org.example.common.enums;

public enum Seasons {
    SPRING("Spring", "春天",new int[]{3,4,5}),
    SUMMER("Summer","夏天",new int[]{6,7,8}),
    AUTUMN("Autumn","秋天",new int[]{9,10,11}),
    WINTER("Winter","冬天",new int[]{12,1,2});



    private final String seasonEngName;
    private final String seasonChiName;
    private final int[] month;

    private Seasons(String seasonEngName, String seasonChiName,int[] month) {
        this.seasonEngName = seasonEngName;
        this.seasonChiName = seasonChiName;
        this.month=month;
    }

    public String getSeasonEngName() {
        return seasonEngName;
    }

    public String getSeasonChiName() {
        return seasonChiName;
    }

    public int[] getMonth() {
        return month;
    }

    public static String monthInSeason(int month){
        //Seasons.values()取得所有列舉內容 以[]形式產生
        for(Seasons season : Seasons.values()){
            for(int m : season.month){
                if (m==month){
                    return season.getSeasonEngName();
                }
            }
        }
        return null;
    }

}
