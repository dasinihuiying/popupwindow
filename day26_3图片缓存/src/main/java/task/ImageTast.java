package task;

import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * Created by Administrator on 2016/5/24 0024.
 */
public class ImageTast extends AsyncTask<String,Void,Bitmap> {

    @Override
    protected Bitmap doInBackground(String... params) {
       // String
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
    }
}
