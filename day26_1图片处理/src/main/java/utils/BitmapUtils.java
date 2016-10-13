package utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
public class BitmapUtils {
    //缩放图片
    public Bitmap getScaleImage(Bitmap source, int dstWidth, int dstHeight) {
        return Bitmap.createScaledBitmap(source, dstWidth, dstHeight, true);
    }

    //压缩图片
    public Bitmap getCompress(Context context, int resId, int desW, int desH) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), resId, opts);
        int bitW = opts.outWidth;
        int bitH = opts.outHeight;

        if (bitH >= desH || bitW >= desW) {
            opts.inJustDecodeBounds = false;
            int scaleH = bitH / desH;
            int scaleW = bitW / desW;
            int scale = scaleH > scaleW ? scaleH : scaleW;
            opts.inSampleSize = scale;
            return BitmapFactory.decodeResource(context.getResources(), resId, opts);
        }
        return null;
    }

    //圆角图片
    public Bitmap getRoundBitmap(Bitmap source) {
        Bitmap bitmap = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);

        canvas.drawRoundRect(new RectF(0, 0, source.getWidth(), source.getHeight()), 60, 60, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        return bitmap;
    }

    //图片剪切
    public Bitmap getCutBitmap(Bitmap source, int x, int y, int w, int h) {
        return Bitmap.createBitmap(source, x, y, w, h);
    }
}
