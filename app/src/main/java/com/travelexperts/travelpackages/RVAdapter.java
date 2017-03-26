package com.travelexperts.travelpackages;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.travelexperts.travelpackages.fragments.all_tab;

/**
 * Created by 744095 on 3/22/2017.
 */

/*public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
   Context context;
   String[] items;
    public Adapter(Context context, String[]items){
        this.items=items;
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View all = inflater.inflate(R.layout.all_tab, parent, false);
        Item item = new Item(all);
        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((Item)holder).textView.setText(items[position]);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }
    public class Item extends RecyclerView.ViewHolder {
        TextView textView;
        public Item(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.item);
        }
    }
}*/
