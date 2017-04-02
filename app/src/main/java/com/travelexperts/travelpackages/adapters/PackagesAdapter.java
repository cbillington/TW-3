package com.travelexperts.travelpackages.adapters;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.travelexperts.travelpackages.R;
import com.travelexperts.travelpackages.data.PackagesContract;
import com.travelexperts.travelpackages.data.PackagesProvider;
import com.travelexperts.travelpackages.sync.PackagesContentObserver;

/**
 * Created by 468364 on 3/31/2017.
 */

public class PackagesAdapter extends RecyclerView.Adapter<PackagesAdapter.PackageViewHolder>
        implements PackagesContentObserver.OnPackagesChangedListener {


    private final Context mContext;
    private Cursor mPackages;

    /**
     * This method is used to swap the cursor that contains
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
     */
    @Override
    public PackageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater li = LayoutInflater.from(mContext);
        View viewToReturn = li.inflate(R.layout.package_list_item, parent, false);

        return new PackageViewHolder(viewToReturn);
    }

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

    @Override
    public void onPackagesChanged(Uri uriOfChangedContentProviderEndpoint) {

        // TODO: implement these methods
        /*this.notifyItemChanged();
        this.notifyItemInserted();
        this.notifyItemRemoved();*/
    }

    public static class PackageViewHolder extends RecyclerView.ViewHolder {

        TextView mPackageNameTextView;
        TextView mPackagePriceTextView;

        /**
         * Constructor is responsible for caching the view object.
         * @param itemView: this is the inflated view layout for a single item.
         *
         */
        public PackageViewHolder(View itemView) {
            super(itemView);

            /*
                The view layout will be passed into the constructor of the viewholder as a
                general view object. This is the inflated item object to cache.

             */
            mPackageNameTextView = (TextView) itemView.findViewById(R.id.tv_package_name);
            mPackagePriceTextView = (TextView) itemView.findViewById(R.id.tv_package_price);
        }


        public void bind(Cursor packageRow) {
            int packageNameIndex = packageRow.getColumnIndex(PackagesContract.PackageEntry
                    .COLUMN_PACKAGE_NAME);
            int packagePriceIndex = packageRow.getColumnIndex(PackagesContract.PackageEntry
                    .COLUMN_PACKAGE_BASE_PRICE);
            Log.d("hello", packageRow.getString(0));
            mPackageNameTextView.setText(packageRow.getString(packageNameIndex));
            mPackagePriceTextView.setText(packageRow.getString(packagePriceIndex));

        }


    }
}
