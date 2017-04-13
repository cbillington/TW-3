package com.travelexperts.travelpackages.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.travelexperts.travelpackages.PackageDetailsActivity;
import com.travelexperts.travelpackages.Package_Details;
import com.travelexperts.travelpackages.R;
import com.travelexperts.travelpackages.data.PackagesContract;
import com.travelexperts.travelpackages.data.PackagesDbHelper;
import com.travelexperts.travelpackages.data.PackagesProvider;
import com.travelexperts.travelpackages.network.AddWatchlistedPackage;
import com.travelexperts.travelpackages.network.RestClient;
import com.travelexperts.travelpackages.utils.DateUtils;
import com.travelexperts.travelpackages.utils.PreferenceUtils;
import com.travelexperts.travelpackages.network.Package;
import com.travelexperts.travelpackages.utils.WatchlistedPackages;

import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 468364 on 3/31/2017.
 */

public class PackagesAdapter extends RecyclerView.Adapter<PackagesAdapter.PackageViewHolder> {


    private final Context mContext;
    private Cursor mPackages;
    private WatchlistedPackages mWatchlistedPackages;
    /**
     * This method is used to swap the cursor that contains new package data.
     * @param: newCursor is the updated packages table.
     *
     */
    public void swapCursor(Cursor newCursor){
        Log.d(PackagesAdapter.class.getSimpleName(), "Cursor Swapped");
        /*PackagesDbHelper dbHelper = new PackagesDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor rows = db.query("packages", null, null, null, null, null, null);*/
        mPackages = newCursor;
        notifyDataSetChanged();
    }

    public PackagesAdapter(Context context, Cursor packages) {
        mContext = context;
        mPackages = packages;
        mWatchlistedPackages = PreferenceUtils.getWatchlistedPackages(context);
    }

    /**
     * This method creates an empty view holder
     *
     * @param parent: parent viewgroup
     * @param viewType: no idea what this does
     * @return empty viewholder object for a package item
     *
     */
    @Override
    public PackageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        mWatchlistedPackages = PreferenceUtils.getWatchlistedPackages(mContext);
        LayoutInflater li = LayoutInflater.from(mContext);
        View viewToReturn = li.inflate(R.layout.package_card_item, parent, false);

        PackageViewHolder holder = new PackageViewHolder(viewToReturn, mContext,
                mWatchlistedPackages);
        return holder;
    }

    /**
     * onBindViewHolder takes in a blank holder and sets all the data needed for a packages item.
     * @param holder: empty viewholder
     * @param position: position in cursor where this data is located.
     *
     */
    @Override
    public void onBindViewHolder(PackageViewHolder holder, final int position) {
        mPackages.moveToPosition(position);
        holder.bind(mPackages, holder);
    }


    @Override
    public int getItemCount() {
        if (mPackages == null){
            return 0;
        }
        return mPackages.getCount();
    }


    @Override
    public long getItemId(int position) {
        mPackages.moveToPosition(position);

        /*
            For the recyclerview to properly render a CursorSwap (keep animations) then
            setHasStableIds must be true and this method must be properly overriden to keep
            consistent ids that correspond to the unique id of the data source ( in this case the
             _id column of each cursor row.

         */

        Long itemId = Long.parseLong(mPackages.getString(mPackages.getColumnIndex("PackageId")));
        Log.d("itemId", String.valueOf(itemId));
        return itemId;
    }




    public void onPackageInserted(Cursor newCursor, Uri uriOfChangedContentProviderEndpoint) {
        //swapCursor(newCursor);
        notifyItemChanged(0, getItemCount());



        /*Log.d("hello from adapter", String.valueOf(uriOfChangedContentProviderEndpoint));
        if (PackagesProvider.sUriMatcher.match(uriOfChangedContentProviderEndpoint) ==
                PackagesProvider.INSERTED_PACKAGE_URI_ID){
            String idOfInsertedPackage = uriOfChangedContentProviderEndpoint.getPathSegments()
                    .get(2);
            Log.d("hello from adapter", String.valueOf(uriOfChangedContentProviderEndpoint));
            swapCursor(newCursor);
            notifyItemInserted(Integer.parseInt(idOfInsertedPackage));
        }*/


    }




    // TODO: implement methods for onPackageModifed, onPackageDeleted



    public static class PackageViewHolder extends RecyclerView.ViewHolder{

        private final Context mContext;
        private final TextView mPriceTextView;
        private final TextView mName;
        private final TextView mDate;
        private final RatingBar mRating;
        private final TextView mRatingNum;
        private final TextView mRatingNumLong;
        private final Button mMoreInfoButton;
        private final Button mWatchlistButton;
        private final ImageView mPhoto;
        private final TextView mPackageIdTextView;
        private String mPackageUrlString;
        private WatchlistedPackages mWatchlistedPackages;
        private Integer mPackageId;
        /*TextView mPackageDescTextView;
        TextView mPackageStartDateTextView;
        TextView mPackageEndDateTextView;
        TextView mPackageNameTextView;
        TextView mPackagePriceTextView;
        CardView mPackageCardView;
        ImageView mPackageImageView;
        FloatingActionButton mPackageToggleOn;
        FloatingActionButton mPackageToggleOff;
        FloatingActionButton mPackageFavoriteOn;
        FloatingActionButton mPackageFavoriteOff;
        FloatingActionButton mPackageViewProducts;
        ConstraintLayout DropDownLayout;
        String mPackageUrlString;*/



        /**
         * Constructor is responsible for caching the view object.
         *
         * @param itemView : this is the inflated view layout for a single item.
         * @param mWatchlistedPackages
         *
         */
        public PackageViewHolder(View itemView, final Context context, final WatchlistedPackages
                mWatchlistedPackages) {
            super(itemView);
            this.mWatchlistedPackages = mWatchlistedPackages;
            /*
                The view layout will be passed into the constructor of the viewholder as a
                general view object. This is the inflated item object to cache.

             */
            /*mPackageCardView = (CardView)itemView.findViewById(R.id.cvAllTab);
            mPackageNameTextView = (TextView) itemView.findViewById(R.id.tvTitle);
            mPackageDescTextView = (TextView) itemView.findViewById(R.id.tvDesc);
            mPackageStartDateTextView = (TextView) itemView.findViewById(R.id.tvStart);
            mPackageEndDateTextView = (TextView) itemView.findViewById(R.id.tvEnd);
            mPackagePriceTextView = (TextView) itemView.findViewById(R.id.tvPrice);
            mPackageImageView = (ImageView)itemView.findViewById(R.id.ivPhoto);
            mPackageToggleOn = (FloatingActionButton) itemView.findViewById(R.id.btnToggleOn);
            mPackageToggleOff = (FloatingActionButton) itemView.findViewById(R.id.btnToggleOff);
            mPackageFavoriteOn = (FloatingActionButton) itemView.findViewById(R.id.btnStarOn);
            mPackageFavoriteOff = (FloatingActionButton) itemView.findViewById(R.id.btnStarOff);
            mPackageViewProducts = (FloatingActionButton) itemView.findViewById(R.id.btnPackages);
            DropDownLayout = (ConstraintLayout) itemView.findViewById(R.id.cnstDropDown);*/


            mContext = context;
            mPhoto = (ImageView)itemView.findViewById(R.id.iv_photo);
            mPriceTextView = (TextView)itemView.findViewById(R.id.tv_price);
            mName = (TextView)itemView.findViewById(R.id.tv_package_name);
            mDate = (TextView)itemView.findViewById(R.id.tv_package_date);
            mRating = (RatingBar)itemView.findViewById(R.id.appCompatRatingBar);
            mRatingNum = (TextView)itemView.findViewById(R.id.textView7);
            mRatingNumLong = (TextView)itemView.findViewById(R.id.textView6);
            mMoreInfoButton = (Button)itemView.findViewById(R.id.btn_more_info);
            mWatchlistButton = (Button)itemView.findViewById(R.id.btn_watchlist);
            mPackageIdTextView = (TextView)itemView.findViewById(R.id.tv_package_id);



            // watchlist button action







            /*// toggle_content
            mPackageDescTextView = (TextView) itemView.findViewById(R.id.tvDesc);
            //hides till clicked
            DropDownLayout.setVisibility(View.GONE);
            mPackageToggleOff.setVisibility(View.GONE);


            mPackageToggleOn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(DropDownLayout.isShown()){//when drop down is up
                        DropDownLayout.setVisibility(View.GONE);
                        mPackageDescTextView.setVisibility(View.GONE);
                        mPackageFavoriteOn.setVisibility(View.GONE);
                        mPackageFavoriteOff.setVisibility(View.GONE);
                        mPackageViewProducts.setVisibility(View.GONE);
                        *//*DropDownLayout.animate()
                                .alpha(1.0f)
                                .setDuration(300)
                                .translationY(0);*//*

                    }
                    else{//when drop down is down
                        DropDownLayout.setVisibility(View.VISIBLE);
                        mPackageDescTextView.setVisibility(View.VISIBLE);
                        mPackageFavoriteOn.setVisibility(View.VISIBLE);
                        mPackageFavoriteOff.setVisibility(View.VISIBLE);
                        mPackageViewProducts.setVisibility(View.VISIBLE);
                        mPackageToggleOff.setVisibility(View.VISIBLE);
                        mPackageToggleOn.setVisibility(View.GONE);
                        *//*DropDownLayout.animate()
                                .alpha(1.0f)
                                .setDuration(300)
                                .translationY(DropDownLayout.getHeight());*//*
                    }
                }
            });
            mPackageToggleOff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(DropDownLayout.isShown()){
                        DropDownLayout.setVisibility(View.GONE);
                        mPackageDescTextView.setVisibility(View.GONE);
                        mPackageFavoriteOn.setVisibility(View.GONE);
                        mPackageFavoriteOff.setVisibility(View.GONE);
                        mPackageViewProducts.setVisibility(View.GONE);
                        mPackageToggleOff.setVisibility(View.GONE);
                        mPackageToggleOn.setVisibility(View.VISIBLE);

                    }
                    *//*else{
                        DropDownLayout.setVisibility(View.GONE);
                        mPackageDescTextView.setVisibility(View.GONE);
                        mPackageFavoriteOn.setVisibility(View.GONE);
                        mPackageFavoriteOff.setVisibility(View.GONE);
                        mPackageViewPackage.setVisibility(View.GONE);
                    }*//*
                }
            });
            mPackageFavoriteOff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //input transfer to favorited packages
                    mPackageFavoriteOn.setVisibility(View.VISIBLE);
                    mPackageFavoriteOff.setVisibility(View.GONE);
                }
            });
            mPackageFavoriteOn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //delete package from favorited package
                    mPackageFavoriteOff.setVisibility(View.VISIBLE);
                    mPackageFavoriteOn.setVisibility(View.GONE);
                }
            });
            mPackageViewProducts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
                //view products
            });
            mPackageImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, Package_Details.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("Title", mPackageNameTextView.getText().toString());
                    bundle.putString("Price", mPackagePriceTextView.getText().toString());
                    bundle.putString("Start Date", mPackageStartDateTextView.getText().toString());
                    bundle.putString("End Date", mPackageEndDateTextView.getText().toString());
                    bundle.putString("Description", mPackageDescTextView.getText().toString());
                    bundle.putString("Photo", mPackageUrlString);
                    i.putExtras(bundle);
                    context.startActivity(i);
                }
            });*/
        }


        /**
         * this method takes a cursor and binds the viewholder object with the data inside the
         * cursor.
         * @param packageRow : data to be binded.
         * @param holder
         *
         */
        @TargetApi(16)
        public void bind(Cursor packageRow, PackageViewHolder holder) {
            int packageNameIndex = packageRow.getColumnIndex(PackagesContract.PackageEntry
                    .COLUMN_PACKAGE_NAME);
            int packagePriceIndex = packageRow.getColumnIndex(PackagesContract.PackageEntry
                    .COLUMN_PACKAGE_BASE_PRICE);
            int packageImgUrl = packageRow.getColumnIndex(PackagesContract.PackageEntry.COLUMN_PACKAGE_IMAGE_URL);
            int packageStartDateIndex = packageRow.getColumnIndex(PackagesContract.PackageEntry
                    .COLUMN_PACKAGE_START_DATE);
            int packageEndDateIndex = packageRow.getColumnIndex(PackagesContract.PackageEntry
                    .COLUMN_PACKAGE_END_DATE);
            int packageDescriptionIndex = packageRow.getColumnIndex(PackagesContract.PackageEntry
                    .COLUMN_PACKAGE_DESCRIPTION);
            mWatchlistedPackages = PreferenceUtils.getWatchlistedPackages(mContext);
            mPackageId = Integer.valueOf(packageRow.getString(packageRow
                    .getColumnIndex(PackagesContract.PackageEntry.COLUMN_PACKAGE_ID)));
            Long packageStartDateInMilli = Long.valueOf(packageRow.getString(packageStartDateIndex));
            Long packageEndDateInMilli = Long.valueOf(packageRow.getString(packageEndDateIndex));

            String dateFormatted = DateUtils.fromMilliToFormatted(packageStartDateInMilli) + " - " +
                    "" + DateUtils.fromMilliToFormatted(packageEndDateInMilli);
            mName.setText(packageRow.getString(packageNameIndex));
            mPriceTextView.setText("$ " + packageRow.getString(packagePriceIndex));
            mDate.setText(dateFormatted);
            Log.d("bind", "base price: " + packageRow.getString(packagePriceIndex));
            Glide.with(mContext).load(packageRow.getString(packageImgUrl)).into(mPhoto);
            mPackageUrlString = packageRow.getString(packageImgUrl);
            //Glide.with(mContext).load(packageRow.getString(packageImgUrl)).into
            // (mPackageImageView);
            mPackageIdTextView.setText(packageRow.getString(packageRow.getColumnIndex("PackageId")));
            final Package packageToAppend = new Package();
            packageToAppend.setPackageId((int)(long)holder.getItemId());

            mMoreInfoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent startPackageDetailsActivity =  new Intent(v.getContext(),
                            PackageDetailsActivity.class);

                    startPackageDetailsActivity.putExtra("PackageId", String.valueOf(mPackageId));

                    v.getContext().startActivity(startPackageDetailsActivity);
                }
            });

            mWatchlistButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    PreferenceUtils.appendPackageToWatchlist(packageToAppend, mContext);
                    Snackbar snackbar = Snackbar.make(v, "Package watchlisted", Snackbar
                            .LENGTH_LONG);
                    snackbar.show();

                    RestClient restClient = new RestClient();
                    AddWatchlistedPackage awp = new AddWatchlistedPackage();
                    awp.setPackageId(mPackageId);
                    restClient.getApiService().incrementWatchlist("application/json", awp)
                            .enqueue(new Callback<AddWatchlistedPackage>() {
                                @Override
                                public void onResponse(Call<AddWatchlistedPackage> call, Response<AddWatchlistedPackage> response) {

                                }

                                @Override
                                public void onFailure(Call<AddWatchlistedPackage> call, Throwable t) {

                                }
                            });

                }


            });


        }

    }
}
