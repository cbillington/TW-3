package com.travelexperts.travelpackages;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.travelexperts.travelpackages.data.TempPackage;

import java.util.List;

/**
 * Created by 744095 on 3/22/2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.TempPackageViewHolder> {
    List<TempPackage> tempPackages;
    RVAdapter(List < TempPackage > tempPackages){
        this.tempPackages = tempPackages;
    }

    public class TempPackageViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView packageName;
        TextView description;
        ImageView photoId;

        TempPackageViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cvAllTab);
            packageName = (TextView) itemView.findViewById(R.id.tvName);
            description = (TextView) itemView.findViewById(R.id.tvDesc);
            photoId = (ImageView) itemView.findViewById(R.id.ivPhoto);


        }
    }
    @Override
    public TempPackageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(ViewGroup.getContext()).inflate(R.layout.all_tab, viewGroup, false);
        TempPackageViewHolder tpvh = new TempPackageViewHolder(v);
        return tpvh;
    }

    @Override
    public void onBindViewHolder(TempPackageViewHolder tempPackageViewHolder, int i) {

        tempPackageViewHolder.packageName.setText(tempPackages.get(i).packagename);
        tempPackageViewHolder.description.setText(tempPackages.get(i).description);
        tempPackageViewHolder.photoId.setImageResource(tempPackages.get(i).photoId);
    }

    @Override
    public int getItemCount() {
        return tempPackages.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

