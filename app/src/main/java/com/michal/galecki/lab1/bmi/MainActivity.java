package com.michal.galecki.lab1.bmi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {
    GetInfoAboutBMI bmi_info = new GetInfoAboutBMI();

    @BindView(R.id.button_main_count)
    Button count_button;
    @BindView(R.id.switch_main_units)
    Switch unit_switch;
    @BindView(R.id.textview_bmi_result)
    TextView bmi_result;
    @BindView(R.id.textview_bmi_yourbmi)
    TextView bmi_yourbmi;
    @BindView(R.id.textview_bmi_description)
    TextView bmi_description;
    @BindView(R.id.edittext_main_mass)
    EditText mass;
    @BindView(R.id.edittext_main_height)
    EditText height;

    MenuItem share_menu;
    MenuItem save_menu;
    String result_mass;
    String result_height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        restorePreferences();
    }

    public void restorePreferences() {
        SharedPreferences shared_preferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        mass.setText(shared_preferences.getString(getString(R.string.all_mass), ""));
        height.setText(shared_preferences.getString(getString(R.string.all_height), ""));
    }

    public boolean savePreferences() {
        if (isResultShown()) {
            SharedPreferences shared_preferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = shared_preferences.edit();
            editor.putString(getString(R.string.all_mass), result_mass);
            editor.putString(getString(R.string.all_height), result_height);
            editor.apply();
            return true;
        }
        return false;
    }

    public boolean isResultShown() {
        return bmi_yourbmi.getVisibility() == View.VISIBLE && bmi_result.getVisibility() == View.VISIBLE && bmi_description.getVisibility() == View.VISIBLE;
    }

    @OnCheckedChanged(R.id.switch_main_units)
    public void onChecked(CompoundButton view, boolean isChecked) {
        if (isChecked) {
            unit_switch.setText(R.string.imperial_units);
        } else {
            unit_switch.setText(R.string.metric_units);
        }
    }

    @OnTextChanged(R.id.edittext_main_height)
    public void AddDotIfRequired(CharSequence text) {
        String result = text.toString();
        if (result.length() >= 2 && !result.contains(".")) {
                result = result.charAt(0) + "." + result.subSequence(1, result.length());
            height.setText(result);
            height.setSelection(result.length());
        }
    }

    @OnClick(R.id.button_main_count)
    public void Count(View view) {
        try {
            int color = bmi_info.getColorOfBMI(getValueOfBMI());
            result_mass = mass.getText().toString();
            result_height = height.getText().toString();
            showResultView(color,getValueOfBMI());
        } catch (IllegalArgumentException exception) {
            defaultResultView();
            Toast.makeText(this, getInvalidDataMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public float getValueOfBMI() {
        if (unit_switch.isChecked()) {
            CountImperialBMI imperial_bmi = new CountImperialBMI();
            return imperial_bmi.countBMI(Float.parseFloat(mass.getText().toString()), Float.parseFloat(height.getText().toString()));
        } else {
            CountMetricBMI metric_bmi = new CountMetricBMI();
            return metric_bmi.countBMI(Float.parseFloat(mass.getText().toString()), Float.parseFloat(height.getText().toString()));
        }
    }

    public void showResultView(int color, float result) {
        bmi_result.setText(String.format(Locale.US, "%.2f", result));
        bmi_result.setTextColor(color);
        bmi_description.setText(getString(bmi_info.getDescriptionAboutBMI(result)));
        bmi_description.setTextColor(color);
        bmi_yourbmi.setVisibility(View.VISIBLE);
        bmi_result.setVisibility(View.VISIBLE);
        bmi_description.setVisibility(View.VISIBLE);
    }

    public void defaultResultView() {
        bmi_yourbmi.setVisibility(View.GONE);
        bmi_result.setVisibility(View.GONE);
        bmi_description.setVisibility(View.GONE);
    }

    public String getInvalidDataMessage() {
        if (unit_switch.isChecked()) {
            CountImperialBMI imperial_bmi = new CountImperialBMI();
            return imperial_bmi.getInvalidDataDescription(this);
        } else {
            CountMetricBMI metric_bmi = new CountMetricBMI();
            return metric_bmi.getInvalidDataDescription(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);
        share_menu = menu.findItem(R.id.action_share);
        save_menu = menu.findItem(R.id.action_save);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        if (bmi_result.getVisibility() == View.VISIBLE) {
            share_menu.setEnabled(true);
            save_menu.setEnabled(true);
        } else {
            share_menu.setEnabled(false);
            save_menu.setEnabled(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_share:
                shareBMI();
                return true;
            case R.id.action_about: {
                openAboutActivity();
                return true;
            }case R.id.action_save: {
                if (savePreferences()) {
                    String successful_message = getString(R.string.saved_successfully) + ".";
                    Toast.makeText(this, successful_message, Toast.LENGTH_SHORT).show();
                } else {
                    String unsuccessful_message = getString(R.string.cant_save) + ".";
                    Toast.makeText(this, unsuccessful_message, Toast.LENGTH_SHORT).show();
                }
            } default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void shareBMI() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.here_is_my_bmi_value) + bmi_result.getText().toString());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    public void openAboutActivity() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("Color", bmi_result.getCurrentTextColor());
        savedInstanceState.putString("Result", bmi_result.getText().toString());
        savedInstanceState.putBoolean("isResultShown", isResultShown());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            super.onRestoreInstanceState(savedInstanceState);
            if (savedInstanceState.getBoolean("isResultShown")) {
                showResultView(savedInstanceState.getInt("Color"), Float.parseFloat(savedInstanceState.getString("Result")));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
