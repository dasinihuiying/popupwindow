package task;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import entity.Gift;
import utils.HttpUitls;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
public class TextTask extends AsyncTask<String, Void, List<Gift>> {
    private List<Gift> list = new ArrayList<>();
    private TextCallback textCallback;

    public interface TextCallback {
        public void callBack(List<Gift> gifts);
    }

    public TextTask(TextCallback textCallback) {
        this.textCallback = textCallback;
    }

    @Override
    protected List<Gift> doInBackground(String... params) {
        String json = HttpUitls.doGetToString(params[0]);
        //json解析
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray info = obj.getJSONArray("info");

            for (int i = 0; i < info.length(); i++) {
                JSONObject jsonObject = info.getJSONObject(i);
                String name = jsonObject.getString("name");
                String icon = jsonObject.getString("icon");
                list.add(new Gift(name, icon));
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Gift> gifts) {
        textCallback.callBack(gifts);
    }
}
