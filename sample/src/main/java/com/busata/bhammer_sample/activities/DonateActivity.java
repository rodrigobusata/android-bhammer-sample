package com.busata.bhammer_sample.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.busata.bhammer.activities.BAppCompatActivity;
import com.busata.bhammer.dialogs.BDialog;
import com.busata.bhammer.utils.BViewUtil;
import com.busata.bhammer_sample.R;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

/**
 * For sample mobile backend interactions, see
 * https://github.com/paypal/rest-api-sdk-python/tree/master/samples/mobile_backend
 */
public class DonateActivity extends BAppCompatActivity {
    private static final String TAG = "paymentDonate";

    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_PRODUCTION;

    private static final String CONFIG_CLIENT_ID = "AWy75S8jXaEOm_2gjD4asQtIUaeBeZC6veGyiQFBNF4UYNycmBvVqQQsQBsTbH86OTqu0iJOYu7B1S0M";

    private static final int REQUEST_CODE_PAYMENT = 1;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(CONFIG_ENVIRONMENT)
            .clientId(CONFIG_CLIENT_ID);

    private EditText mEtDonateValue;
    private RadioButton mRbUsd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        setToolBar("Donation");

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);

        mEtDonateValue = (EditText) findViewById(R.id.etDonateValue);
        mRbUsd = (RadioButton) findViewById(R.id.rbUsd);
    }

    public void onBuyPressed(View pressed) {

        if (!isDouble(mEtDonateValue.getText().toString())) {
            BViewUtil.createDialogInfo(this, getString(R.string.warning), getString(R.string.the_typed_value_is_invalid));
        } else {
            PayPalPayment thingToBuy = getThingToBuy(PayPalPayment.PAYMENT_INTENT_SALE);

            Intent intent = new Intent(DonateActivity.this, PaymentActivity.class);

            // send the same configuration for restart resiliency
            intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

            intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);

            startActivityForResult(intent, REQUEST_CODE_PAYMENT);
        }
    }

    private PayPalPayment getThingToBuy(String paymentIntent) {
        String currency = mRbUsd.isChecked() ? "USD" : "BRL";
        return new PayPalPayment(new BigDecimal(mEtDonateValue.getText().toString()), currency, getString(R.string.donate),
                paymentIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                PaymentConfirmation confirm =
                        data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirm != null) {
                    try {
                        Log.i(TAG, confirm.toJSONObject().toString(4));
                        Log.i(TAG, confirm.getPayment().toJSONObject().toString(4));

                        mEtDonateValue.setText("");

                        final BDialog dialog = new BDialog(this, "", getString(R.string.thank_you));
                        dialog.addButton(getString(R.string.ok), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                finish();
                            }
                        }, true).show();

                    } catch (JSONException e) {
                        Log.e(TAG, "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i(TAG, "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i(
                        TAG,
                        "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }
    }

    @Override
    public void onDestroy() {
        // Stop service when done
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    private boolean isDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}