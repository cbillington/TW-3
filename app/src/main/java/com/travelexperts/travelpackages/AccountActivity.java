package com.travelexperts.travelpackages;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.travelexperts.travelpackages.network.Customer;
import com.travelexperts.travelpackages.sync.NetworkTasks;
import com.travelexperts.travelpackages.utils.PhoneNumberValidator;
import com.travelexperts.travelpackages.utils.PreferenceUtils;


public class AccountActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener, PlaceSelectionListener {
    private String[] mProvinces;
    private TextInputEditText mFirstNameTextInputEditText;
    private TextInputEditText mLastNameTextInputEditText;
    private TextInputEditText mCityTextInputEditText;
    private Spinner mProvincesSpinner;
    private TextInputEditText mPostalCodeEditText;
    private TextInputEditText mPhoneNumberEditText;
    private ArrayAdapter<String> mProvincesArrayAdapter;
    private TextInputEditText mEmailEditText;
    private Customer mCustomer;
    private PlaceAutocompleteFragment mPlaceAutoCompleteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getReferencesToViews();
        setupProvinceSprinner();
        fillCustomerInfo();
        // register shared preferences changed listener.
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this);
    }

    private void fillCustomerInfo() {
        mCustomer = PreferenceUtils.getCustomer(this);
        // first name
        mFirstNameTextInputEditText.setText(mCustomer.getCustFirstName());
        // last name
        mLastNameTextInputEditText.setText(mCustomer.getCustLastName());
        // address
        mPlaceAutoCompleteFragment.setText(mCustomer.getCustAddress());
        // city
        mCityTextInputEditText.setText(mCustomer.getCustCity());
        // province
        // get the province index from array
        mProvincesSpinner.setSelection(mProvincesArrayAdapter.getPosition(mCustomer.getCustProv()));
        // postalcode
        mPostalCodeEditText.setText(mCustomer.getCustPostal());
        // phone
        String custPhoneFormatted = PhoneNumberUtils.formatNumber(mCustomer.getCustHomePhone());
        mPhoneNumberEditText.setText(custPhoneFormatted);
        //email
        mEmailEditText.setText(mCustomer.getCustEmail());
    }



    @TargetApi(25)
    private void getReferencesToViews() {
        /*
            First name

         */

        mFirstNameTextInputEditText = (TextInputEditText)findViewById(R.id.et_name);
        //mFirstNameTextInputEditText.setFocusable(false);
        mFirstNameTextInputEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    mCustomer.setCustFirstName(mFirstNameTextInputEditText.getText().toString());
                    PreferenceUtils.updateCustomer(getApplicationContext(), mCustomer);
                    //TODO: implement validators for these fields
                }
            }
        });

        /*

            Last Name


         */
        mLastNameTextInputEditText = (TextInputEditText)findViewById(R.id.et_last_name);
        mLastNameTextInputEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    mCustomer.setCustLastName(mLastNameTextInputEditText.getText().toString());
                    PreferenceUtils.updateCustomer(getApplicationContext(), mCustomer);
                    //TODO: implement validators for these fields
                }
            }
        });
        // address

        // city
        mCityTextInputEditText = (TextInputEditText)findViewById(R.id.et_city);
        mCityTextInputEditText.setFocusable(false);
        mCityTextInputEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Please use address search", Toast
                        .LENGTH_LONG).show();
            }
        });
        // provinces
        mProvincesSpinner = (Spinner)findViewById(R.id.spnr_provinces);
        mProvincesSpinner.setFocusable(false);
        mProvincesSpinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getApplicationContext(), "Please use address search", Toast
                        .LENGTH_LONG).show();
                return true;
            }
        });

        // postal code
        mPostalCodeEditText = (TextInputEditText)findViewById(R.id.et_postal_code);
        mPostalCodeEditText.setFocusable(false);
        mPostalCodeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Please use address search", Toast
                        .LENGTH_LONG).show();
            }
        });
        //phone
        mPhoneNumberEditText = (TextInputEditText)findViewById(R.id.et_phone);
        mPhoneNumberEditText.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        mPhoneNumberEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (PhoneNumberValidator.validatePhoneNumber(mPhoneNumberEditText.getText().toString())){
                        mCustomer.setCustHomePhone(mPhoneNumberEditText.getText().toString());
                        PreferenceUtils.updateCustomer(getApplicationContext(), mCustomer);
                    }
                    else{
                        mPhoneNumberEditText.setError("Error: must have 10 digits");
                    }
                }
            }
        });
        //mPhoneNumberEditText.setFocusable(false);
        // email
        mEmailEditText = (TextInputEditText)findViewById(R.id.et_email);
        //mEmailEditText.setFocusable(false);
        mEmailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(PhoneNumberValidator.validateEmail(mEmailEditText.getText().toString())){
                        mCustomer.setCustEmail(mEmailEditText.getText().toString());
                        PreferenceUtils.updateCustomer(getApplicationContext(), mCustomer);
                    }
                    else{
                        mEmailEditText.setError("Invalid email");
                    }
                }
            }
        });
        // places api widget
        // get reference to fragment
        mPlaceAutoCompleteFragment = (PlaceAutocompleteFragment)getFragmentManager()
                .findFragmentById(R.id.place_autocomplete_fragment);

        // create filter
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                .setCountry("CA")
                .build();

        // set filter
        mPlaceAutoCompleteFragment.setFilter(typeFilter);
        // set event listener
        mPlaceAutoCompleteFragment.setOnPlaceSelectedListener(this);

        mPlaceAutoCompleteFragment.setHint("Search Address");

    }

    private void setupProvinceSprinner() {
        mProvinces = new String[]{"AB", "BC", "MB", "NB", "NL", "NS", "NT", "NU", "ON", "PE",
                "QC", "SK", "YT"};
        mProvincesArrayAdapter = new ArrayAdapter<String>(this, R.layout.province_item, R.id
                .tv_province,
                mProvinces);
        mProvincesSpinner.setAdapter(mProvincesArrayAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if(key.equals(PreferenceUtils.KEY_CUSTOMER)){
            Log.d("Customer", PreferenceUtils.getCustomer(this).getCustFirstName());
            NetworkTasks.pushCustomerUpdate(this, mCustomer);
        }
    }

    @Override
    public void onPlaceSelected(Place place) {
        Log.d("location selected: ", String.valueOf(place.getAddress()));
        String fullAddress = place.getAddress().toString();
        String[] addressComponents = fullAddress.split(",");
        for(String component: addressComponents){
            Log.d("component", component.trim());
        }

        // address
        String address = addressComponents[0].trim();

        // city
        String city = addressComponents[1].trim();

        // prov + postal
        String provAndPostal = addressComponents[2].trim();

        String province = provAndPostal.substring(0, 2);
        String postal = provAndPostal.substring(2).trim();

        Log.d("province: ", province);
        Log.d("postal: ", postal);
        // country

        // set view
        mCityTextInputEditText.setText(city);
        mProvincesSpinner.setSelection(mProvincesArrayAdapter.getPosition(postal));
        mPostalCodeEditText.setText(postal);

        // set customer changes
        mCustomer.setCustCity(city);
        mCustomer.setCustProv(province);
        mCustomer.setCustPostal(postal);
        mCustomer.setCustAddress(address);
        Log.d("hello", mCustomer.toString());
        // update customer preference
        NetworkTasks.pushCustomerUpdate(this, mCustomer);
        PreferenceUtils.updateCustomer(this, mCustomer);
    }

    @Override
    public void onError(Status status) {

    }
}
