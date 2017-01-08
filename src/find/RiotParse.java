package find;

import org.json.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Florin on 1/8/2017.
 */
public class RiotParse {
    private String content;
    private String name;
    private JSONObject object;
    RiotParse(String name, String content) {
        this.content = content;
        this.name = name;
        //System.out.println(content);
        object = (JSONObject) new JSONTokener(content).nextValue();

    }
    RiotParse(String content) {
        this(null,content);
    }
    String parseName() {
        return object.getJSONObject(name).getString("name");
    }
    int parseID() {
        return object.getJSONObject(name).getInt("id");
    }
    int parseLastMatchID() {
        JSONArray jsonArray = object.getJSONArray("matches");
        return jsonArray.getJSONObject(0).getInt("matchId");
    }
    String parseMatchDetails() {
        StringBuilder sb = new StringBuilder();
        Date date = new Date(object.getLong("matchCreation"));
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        sb.append("He played at " + format.format(date) + " with:\n");
        JSONArray jsonArray = object.getJSONArray("participantIdentities");

        for (int i = 0; i<jsonArray.length();i++) {
            sb.append(i+1 + ". " +jsonArray.getJSONObject(i).getJSONObject("player").getString("summonerName"));
            sb.append("\n");
        }
        return sb.toString();
    }


}
