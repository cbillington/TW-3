package com.travelexperts.travelpackages.adapters;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.travelexperts.travelpackages.R;
import com.travelexperts.travelpackages.data.PackagesContract;
import com.travelexperts.travelpackages.data.PackagesProvider;

import java.util.ArrayList;


/**
 * Created by 468364 on 3/31/2017.
 */

public class PackagesAdapter extends RecyclerView.Adapter<PackagesAdapter.PackageViewHolder> {


    private final Context mContext;
    private Cursor mPackages;
    /**
     * This method is used to swap the cursor that contains new package data.
     * @param newCursor: newCursor is the updated packages table.
     *
     */
    public void swapCursor(Cursor newCursor){
        Log.d(PackagesAdapter.class.getSimpleName(), "Cursor Swapped");
        mPackages = newCursor;
        notifyDataSetChanged();
    }

    public PackagesAdapter(Context context, Cursor packages) {
        mContext = context;
        mPackages = packages;

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

        LayoutInflater li = LayoutInflater.from(mContext);
        View viewToReturn = li.inflate(R.layout.package_list_item, parent, false);

        return new PackageViewHolder(viewToReturn, mContext);
    }

    /**
     * onBindViewHolder takes in a blank holder and sets all the data needed for a packages item.
     * @param holder: empty viewholder
     * @param position: position in cursor where this data is located.
     *
     */
    @Override
    public void onBindViewHolder(PackageViewHolder holder, int position) {
        mPackages.moveToPosition(position);
        holder.bind(mPackages);
    }


    @Override
    public int getItemCount() {
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
        return mPackages.getInt(mPackages.getColumnIndex("_id"));
    }


    /**
     * this must be true for this adapter since we are utalizing the fact that the Id's for each
     * item are matching the underlying content provider. This makes it easy to implement methods
     * like nofifyItemInserted() and keeps the nice animations.
     *
     * @param hasStableIds: property of a recyclerview adapter.
     *
     */
    @Override
    public void setHasStableIds(boolean hasStableIds) {
        hasStableIds = true;
    }

    /**
     * This method is called by the content observer when a change to the packages table has been
     * made. It is safe to assume that the uri provided matches the correct Uri since the content
     * observer is responsible for making sure this method is only called at the correct observed
     * changes.
     *
     * @param newCursor: new cursor for the adapter from quering the packages provider.
     * @param uriOfChangedContentProviderEndpoint: should be content://<authority>/packages/insert/#
     *
     */
    public void onPackageInserted(Cursor newCursor, Uri uriOfChangedContentProviderEndpoint) {

        Log.d("hello from adapter", String.valueOf(uriOfChangedContentProviderEndpoint));
        if (PackagesProvider.sUriMatcher.match(uriOfChangedContentProviderEndpoint) ==
                PackagesProvider.INSERTED_PACKAGE_URI_ID){
            String idOfInsertedPackage = uriOfChangedContentProviderEndpoint.getPathSegments()
                    .get(2);
            Log.d("hello from adapter", String.valueOf(uriOfChangedContentProviderEndpoint));
            swapCursor(newCursor);
            notifyItemInserted(Integer.parseInt(idOfInsertedPackage));
        }


    }




    // TODO: implement methods for onPackageModifed, onPackageDeleted



    public static class PackageViewHolder extends RecyclerView.ViewHolder{

        private final Context mContext;
        TextView mPackageDescTextView;
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



        /**
         * Constructor is responsible for caching the view object.
         * @param itemView: this is the inflated view layout for a single item.
         *
         */
        public PackageViewHolder(View itemView, Context context) {
            super(itemView);

            /*
                The view layout will be passed into the constructor of the viewholder as a
                general view object. This is the inflated item object to cache.

             */
            mPackageCardView = (CardView)itemView.findViewById(R.id.cvAllTab);
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
            DropDownLayout = (ConstraintLayout) itemView.findViewById(R.id.cnstDropDown);

            mContext = context;

<<<<<<< HEAD
            // toggle_content
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
                        /*DropDownLayout.animate()
                                .alpha(1.0f)
                                .setDuration(300)
                                .translationY(0);*/

                    }
                    else{//when drop down is down
                        DropDownLayout.setVisibility(View.VISIBLE);
                        mPackageDescTextView.setVisibility(View.VISIBLE);
                        mPackageFavoriteOn.setVisibility(View.VISIBLE);
                        mPackageFavoriteOff.setVisibility(View.VISIBLE);
                        mPackageViewProducts.setVisibility(View.VISIBLE);
                        mPackageToggleOff.setVisibility(View.VISIBLE);
                        mPackageToggleOn.setVisibility(View.GONE);
                        /*DropDownLayout.animate()
                                .alpha(1.0f)
                                .setDuration(300)
                                .translationY(DropDownLayout.getHeight());*/
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
                    /*else{
                        DropDownLayout.setVisibility(View.GONE);
                        mPackageDescTextView.setVisibility(View.GONE);
                        mPackageFavoriteOn.setVisibility(View.GONE);
                        mPackageFavoriteOff.setVisibility(View.GONE);
                        mPackageViewPackage.setVisibility(View.GONE);
                    }*/
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
                    //view products
=======
            mPackageImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

>>>>>>> c02c7c6ea65d9934f5523b18b97af82b55d9c5aa
                }
            });
        }


        /**
         * this method takes a cursor and binds the viewholder object with the data inside the
         * cursor.
         * @param packageRow: data to be binded.
         *
         */
        public void bind(Cursor packageRow) {
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

            Log.d("hello", packageRow.getString(0));
            mPackageNameTextView.setText(packageRow.getString(packageNameIndex));
            mPackagePriceTextView.setText(packageRow.getString(packagePriceIndex));
<<<<<<< HEAD
            mPackageStartDateTextView.setText(packageRow.getString(packageStartDateIndex));
            mPackageEndDateTextView.setText(packageRow.getString(packageEndDateIndex));
            mPackageDescTextView.setText(packageRow.getString(packageDescriptionIndex));
            Glide.with(mContext).load(packageRow.getString(packageImgUrl)).into(mPackageImageView);
=======
            //Glide.with(mContext).load(packageRow.getString(packageImgUrl)).into
            // (mPackageImageView);
>>>>>>> c02c7c6ea65d9934f5523b18b97af82b55d9c5aa

        }



    }
}
