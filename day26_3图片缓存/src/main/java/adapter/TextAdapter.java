package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day26_3.R;

import java.util.List;

import entity.Gift;

/**
 * Created by Administrator on 2016/5/23 0023.
 */
public class TextAdapter extends BaseAdapter {
    private List<Gift> list;
    private LayoutInflater mInflater;

    public TextAdapter(Context context, List<Gift> list) {
        this.list = list;
        mInflater = mInflater.from(context);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView  = mInflater.inflate(R.layout.layout_textadapter,parent,false);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.tv_text = (TextView) convertView.findViewById(R.id.tv_text);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_text.setText(list.get(position).getName());


        return convertView;
    }
    static class ViewHolder{
        ImageView imageView;
        TextView tv_text;
    }
}
