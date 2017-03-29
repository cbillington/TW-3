package com.travelexperts.travelpackages;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.travelexperts.travelpackages.data.getTempPackage;

import java.util.List;

/**
 * Created by 744095 on 3/22/2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.TempPackageViewHolder> {
    List<getTempPackage> getTempPackages;
    public RVAdapter(List <getTempPackage> getTempPackages){
        this.getTempPackages = getTempPackages;
    }

    public class TempPackageViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView tvpackageName;
        TextView tvdescription;
        ImageView ivphotoId;

        TempPackageViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cvAllTab);
            tvpackageName = (TextView) itemView.findViewById(R.id.tvName);
            tvdescription = (TextView) itemView.findViewById(R.id.tvDesc);
            ivphotoId = (ImageView) itemView.findViewById(R.id.ivPhoto);


        }
    }
    @Override
    public TempPackageViewHolder onCreateViewHolder (ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.all_tab, viewGroup, false);
        TempPackageViewHolder tpvh = new TempPackageViewHolder(v);
        return tpvh;
    }

    @Override
    public void onBindViewHolder(TempPackageViewHolder tempPackageViewHolder, int i) {

        tempPackageViewHolder.tvpackageName.setText(getTempPackages.get(i).packagename);
        tempPackageViewHolder.tvdescription.setText(getTempPackages.get(i).description);
        tempPackageViewHolder.ivphotoId.setImageResource(getTempPackages.get(i).photoId);
    }

    @Override
    public int getItemCount() {
        return getTempPackages.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

