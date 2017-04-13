package com.travelexperts.travelpackages.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.travelexperts.travelpackages.R;
import com.travelexperts.travelpackages.network.Booking;
import com.travelexperts.travelpackages.network.BookingDetail;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by 468364 on 4/10/2017.
 */

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.BookingViewHolder>{


    private List<Booking> mBookings;
    private final Context mContext;
    private OnBookingClickListener mCallback;

    public void swapBookings(List<Booking> newBookings){
        mBookings = newBookings;
        notifyDataSetChanged();

    }

    public BookingsAdapter(Context context, List<Booking> bookings, OnBookingClickListener
            callback) {
        mBookings = bookings;
        mContext = context;
        mCallback = callback;
    }

    @Override
    public BookingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater li = LayoutInflater.from(mContext);
        View view = li.inflate(R.layout.booking_list_item, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookingViewHolder holder, int position) {
        Booking booking = mBookings.get(position);

        holder.bind(booking);
    }

    @Override
    public int getItemCount() {
        if(mBookings == null){
            return 0;
        }
        return mBookings.size();
    }

    @Override
    public long getItemId(int position) {
        Booking booking = mBookings.get(position);

        return booking.getBookingId();
    }



    public static class BookingViewHolder extends RecyclerView.ViewHolder{

        private final TextView mBookingNoTextView;
        private final TextView mBookingTotalTextView;
        private final TextView mBookingDescription;
        private final TextView mBookingDate;


        public BookingViewHolder(View itemView) {
            super(itemView);

            mBookingNoTextView = (TextView)itemView.findViewById(R.id.tv_booking_no);
            mBookingTotalTextView = (TextView)itemView.findViewById(R.id.tv_booking_total);
            mBookingDescription = (TextView)itemView.findViewById(R.id.tv_booking_description);
            mBookingDate = (TextView)itemView.findViewById(R.id.tv_booking_date);

        }

        public void bind(Booking booking){
            // format date
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = formatter.format(new Date(booking.getBookingDate()));

            //format currency


            mBookingNoTextView.setText(booking.getBookingNo());

            if(booking.getBookingDetails().size() == 0){
                mBookingTotalTextView.setText("N/A");
                mBookingDescription.setText("N/A");
            }
            else {

                Locale locale = Locale.US;
                NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
                Double total = getTotal(booking);
                String priceText = fmt.format(total);

                mBookingTotalTextView.setText(priceText);
                mBookingDescription.setText(booking.getBookingDetails().get(0).getDescription());
            }
            mBookingDate.setText(dateString);
        }

        private Double getTotal(Booking booking) {

            Double total = Double.valueOf(0);

            for(BookingDetail bd : booking.getBookingDetails()){
                total += bd.getBasePrice();
            }

            return total;
        }


    }
}
