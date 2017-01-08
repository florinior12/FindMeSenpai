package find;

/**
 * Created by Florin on 1/8/2017.
 */
public class Summoner {
    private String summonerName;
    private String region;
    private RiotApi riotApi;
    private int ID;
    Summoner(String summonerName, String region) {
        this.region = region;
        riotApi = new RiotApi(summonerName,this.region);
        this.summonerName = riotApi.findName();
    }

    int getID() {
        return riotApi.findID();
    }
    String getSummonerName() {
        return summonerName;
    }
    boolean isInMatch() {
        return riotApi.findIsInMatch();
    }
    int getLastMatchID() {
        return riotApi.findLastMatchID();
    }
    String getLastMatchDetails() {
        return riotApi.getMatchDetails();
    }
}
