package com.example.anhthunguyen.map_count;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.Value;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;

import java.util.concurrent.TimeUnit;

public class RunActivity extends ActionBarActivity
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mClient = null;
//    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;

//    private OnDataPointListener mListener;
    private static final String TAG = "FitActivity";

    private static final int REQUEST_OAUTH = 1;

    private TextView tw_step;
    int mInitialNumberOfSteps = 0;

    private ImageView img;
    private boolean mFirstCount = true;


    int step;
    double num_calo;

    TextView calo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        // lấy ActionBar
        //ActionBar actionBar = getActionBar();
        // hiển thị nút Up ở Home icon
        //actionBar.setDisplayHomeAsUpEnabled(true);
        // set màu cho actionBar
       // actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#76FF03")));
        step = 0;
        tw_step = (TextView) findViewById(R.id.step);
        calo = (TextView)findViewById(R.id.calo);

        connectFitness();

    }

    private void connectFitness() {
        Log.i(TAG, "Connecting...");
        Toast.makeText(getBaseContext(), "Connecting...", Toast.LENGTH_LONG).show();

        mClient = new GoogleApiClient.Builder(this)
                .addApi(Fitness.SENSORS_API)
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE))
                .addScope(new Scope(Scopes.FITNESS_BODY_READ_WRITE))
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mClient.connect();
    }


    @Override
    public void onConnected(Bundle bundle) {
        Log.i(TAG, "Connected");
        Toast.makeText(getBaseContext(), "Connected", Toast.LENGTH_LONG).show();

        invokeFitnessAPI();
    }

    @Override
    public void onConnectionSuspended(int i) {
        if (i == ConnectionCallbacks.CAUSE_NETWORK_LOST) {
            Log.i(TAG, "Connect lost: Cause Network Lost");
            Toast.makeText(getBaseContext(), "Connect lost: Cause Network Lost", Toast.LENGTH_LONG).show();
        } else if (i == ConnectionCallbacks.CAUSE_SERVICE_DISCONNECTED) {
            Log.i(TAG, "Connect lost: Service Disconnected");
            Toast.makeText(getBaseContext(), "Connect lost: Service Disconnected", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.getErrorCode() == ConnectionResult.SIGN_IN_REQUIRED ||
                connectionResult.getErrorCode() == FitnessStatusCodes.NEEDS_OAUTH_PERMISSIONS) {
            try {
                connectionResult.startResolutionForResult(this, REQUEST_OAUTH);
            } catch (IntentSender.SendIntentException e) {
                Log.e(TAG, "Exception connecting to the fitness service", e);
                Toast.makeText(getBaseContext(), "Exception connecting to the fitness service", Toast.LENGTH_LONG).show();
            }
        } else {
            Log.e(TAG, "Unknow connection issue: " + connectionResult.getErrorCode());
            Toast.makeText(getBaseContext(), "Unknow connection issue: ", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_OAUTH) {
            if (resultCode == RESULT_OK) {
                mClient.connect();
            }
        }
    }

    private void invokeFitnessAPI() {

        OnDataPointListener listener = new OnDataPointListener() {
            @Override
            public void onDataPoint(DataPoint dataPoint) {
                for (Field field : dataPoint.getDataType().getFields()) {
                    Value val = dataPoint.getValue(field);
                    updateTextView(val.asInt());
                }
            }
        };

        DataSourcesRequest request = new DataSourcesRequest.Builder()
                .setDataSourceTypes(DataSource.TYPE_DERIVED)
                .setDataTypes(DataType.AGGREGATE_STEP_COUNT_DELTA)
                .build();

//        PendingResult<DataSourcesResult> pendingResult = Fitness.SensorsApi.findDataSources(mClient, request);

        SensorRequest sensorRequest = new SensorRequest.Builder()
                .setDataType(DataType.AGGREGATE_STEP_COUNT_DELTA)
                .setSamplingRate(1, TimeUnit.SECONDS)
                .build();

        PendingResult<Status> regResult = Fitness.SensorsApi.add(mClient, sensorRequest, listener);

        regResult.setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(Status status) {
                if (status.isSuccess()) {
                    Log.d(TAG, "Listener registered");
                    Toast.makeText(getBaseContext(), "Listener registered", Toast.LENGTH_LONG).show();
                } else {
                    Log.d(TAG, "Listener not registered");
                    Toast.makeText(getBaseContext(), "Listener not registered", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void updateTextView (final int val) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(getBaseContext(), "On Datapoint!", Toast.LENGTH_SHORT).show();
                step = step + val;
//                if (mFirstCount && (val != 0)) {
//                    mInitialNumberOfSteps = val;
//                    mFirstCount = false;
//                }
                num_calo=step*0.08;
                calo.setText(Double.toString(num_calo));
                tw_step.setText(Integer.toString(step));
            }
        });
    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mFirstCount = true;
//        mInitialNumberOfSteps = 0;
//        if (mClient == null || mClient.isConnected()) {
//            connectFitness();
//        }
//    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mClient.isConnected() || mClient.isConnecting()) mClient.disconnect();

        mInitialNumberOfSteps = 0;
        mFirstCount = true;
    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putBoolean(AUTH_PENDING, authInProgress);
//    }
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_setting_, menu);
    return true;
}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
