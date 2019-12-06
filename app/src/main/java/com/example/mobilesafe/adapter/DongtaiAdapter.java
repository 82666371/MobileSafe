package com.example.mobilesafe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mobilesafe.R;
import com.example.mobilesafe.entity.DongtaiItm;
import com.example.mobilesafe.entity.PopItm;

import java.util.ArrayList;

/**
 * @author glsite.com
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class DongtaiAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DongtaiItm> list = new ArrayList<>();

    public DongtaiAdapter(Context context, ArrayList<DongtaiItm> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        DongtaiItm item = list.get(position);
        convertView  = LayoutInflater.from(context).inflate(R.layout.dongtai_item, null);
        TextView tv = convertView.findViewById(R.id.bt);
        tv.setText(item.title);
        TextView nr = convertView.findViewById(R.id.nr);
        nr.setText(item.content);
        return convertView;
    }
}
