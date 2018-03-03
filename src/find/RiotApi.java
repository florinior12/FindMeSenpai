package find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



/**
 * Created by Florin on 1/6/2017.
 */
public class RiotApi {
    private final String key = "API_KEY";
    private String region;
    private final String website;
    private URL url = null;
    private BufferedReader bufferedReader = null;
    private RiotParse idParser;


    RiotApi(String summonerName, String region) {
        website = "https://" + region + ".api.pvp.net/";
        this.region = region;
        try {
            //If connection is ok => response code = 200
            url = new URL(website + "api/lol/" + region + "/v1.4/summoner/by-name/" + summonerName + "?api_key=" + key);
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            idParser = new RiotParse(summonerName,bufferedReader.readLine());
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String findName() {
        return idParser.parseName();
    }

    boolean findIsInMatch() {
        String platformID = null;
        int code = 0;
        switch (region){
            case "eune":
                platformID = "EUN1";
                break;
            default:
                break;
        }
        try {
            url = new URL(website + "observer-mode/rest/consumer/getSpectatorGameInfo/" + platformID + "/" + this.findID() + "?api_key=" + key );
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.connect();
            code = http.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (code==404) {
            return false;
        }
        else return true;

    }

    int findID() {
        return idParser.parseID();
    }


    int findLastMatchID() {
        RiotParse matchesParser = null;
        try {
            url = new URL(website + "api/lol/eune/v2.2/matchlist/by-summoner/" + this.findID() + "?api_key=" + key);
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            matchesParser = new RiotParse(bufferedReader.readLine());
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (matchesParser != null) {
            return matchesParser.parseLastMatchID();
        }
        return 0;

    }
    String getMatchDetails() {
        RiotParse matchParser = null;
        try {
            url = new URL(website + "api/lol/eune/v2.2/match/" + this.findLastMatchID() + "?api_key=" + key);
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            matchParser = new RiotParse(bufferedReader.readLine());
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchParser.parseMatchDetails();

    }
}
