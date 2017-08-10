package ru.goodibunakov.itravel;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import co.ceryle.radiorealbutton.RadioRealButton;
import co.ceryle.radiorealbutton.RadioRealButtonGroup;

public class CreateNewTripActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    ElegantNumberButton numberButton;
    private DatePickerDialog chooseDate;
    AlertDialog.Builder ad;
    private EditText dateFrom, dateTo;
    DataBaseDestinationHelper dataBaseDestinationHelper;
    AutoCompleteTextView autoCompleteTextViewCountry;
    AutoCompleteTextView autoCompleteTextViewCity;
    String chosenCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_trip);

        numberButton = (ElegantNumberButton) findViewById(R.id.elegant_number_button);
        Button createTrip = (Button) findViewById(R.id.btn_create_trip);
        createTrip.setClickable(false);
        autoCompleteTextViewCountry = (AutoCompleteTextView) findViewById(R.id.country);
        autoCompleteTextViewCity = (AutoCompleteTextView) findViewById(R.id.city);
        dateFrom = (EditText) findViewById(R.id.date_from);
        dateFrom.setOnFocusChangeListener(this);
        dateTo = (EditText) findViewById(R.id.date_to);
        dateTo.setOnFocusChangeListener(this);


        dataBaseDestinationHelper = new DataBaseDestinationHelper(this);
        ArrayList<String> allCountries = dataBaseDestinationHelper.getAllCountries();
        ArrayAdapter<String> adapterAllCountries = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, allCountries);
        autoCompleteTextViewCountry.setAdapter(adapterAllCountries);
        autoCompleteTextViewCountry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                autoCompleteTextViewCity.getText().clear();
                chosenCountry = autoCompleteTextViewCountry.getText().toString();
                ArrayList<String> neededCities = dataBaseDestinationHelper.getNeededCities(chosenCountry);
                ArrayAdapter<String> adapterNeededCities = new ArrayAdapter<String>(CreateNewTripActivity.this, android.R.layout.simple_dropdown_item_1line, neededCities);
                autoCompleteTextViewCity.setAdapter(adapterNeededCities);
            }
        });
        autoCompleteTextViewCountry.setOnFocusChangeListener(this);
        autoCompleteTextViewCity.setOnFocusChangeListener(this);

        final RadioRealButton btnBus = (RadioRealButton) findViewById(R.id.transport_bus);
        final RadioRealButton btnCar = (RadioRealButton) findViewById(R.id.transport_car);
        final RadioRealButton btnPlain = (RadioRealButton) findViewById(R.id.transport_plain);
        final RadioRealButton btnShip = (RadioRealButton) findViewById(R.id.transport_ship);
        final RadioRealButton btnTrain = (RadioRealButton) findViewById(R.id.transport_train);
        final RadioRealButton btnNogami = (RadioRealButton) findViewById(R.id.transport_nogami);
        RadioRealButtonGroup group = (RadioRealButtonGroup) findViewById(R.id.transport_group);

        // onClickButton listener detects any click performed on buttons by touch
        group.setOnClickedButtonListener(new RadioRealButtonGroup.OnClickedButtonListener()

        {
            @Override
            public void onClickedButton(RadioRealButton button, int position) {
                Toast.makeText(CreateNewTripActivity.this, "Clicked! Position: " + (position + 1), Toast.LENGTH_SHORT).show();
            }
        });

        //обработка кнопки "Создать поездку"
        createTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        numberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener()

        {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                // Добавляем новый ImageView
                if (oldValue < newValue) {
                    Intent newPerson = new Intent(CreateNewTripActivity.this, PersonEditActivity.class);
                    startActivityForResult(newPerson, 0);
//                    View imageView = new ImageView(CreateNewTripActivity.this);
//                    View.setImageResource(R.drawable.i_travel_logo_bird);
//                    ViewGroup.LayoutParams imageViewLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                    imageViewLayoutParams.height = 300;
//                    imageViewLayoutParams.width = 300;
//                    imageView.setLayoutParams(imageViewLayoutParams);
                    //flowLayout.addView(imageView);
                } else {
                    //Удаляем
                    ad = new AlertDialog.Builder(CreateNewTripActivity.this);
                    ad.setTitle(getResources().getString(R.string.title_delete_person));  // заголовок
                    ad.setMessage(getResources().getString(R.string.dialog_aushure)); // сообщение
                    ad.setPositiveButton(getResources().getString(R.string.button_ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int arg1) {
                            Toast.makeText(CreateNewTripActivity.this, getResources().getString(R.string.person_delete),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                    ad.setNegativeButton(getResources().getString(R.string.button_cancel), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {
                            Toast.makeText(CreateNewTripActivity.this, getResources().getString(R.string.person_ok), Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                    ad.setCancelable(false);
                    ad.show();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_new_trip_activity, menu);
        return true;
    }

    //инициализация диалога выбора даты
    private void initDateDialog(final View view) throws ParseException {
        String dateFromString;
        Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        chooseDate = new DatePickerDialog(CreateNewTripActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                Calendar newCalendar = Calendar.getInstance();
                newCalendar.set(year, monthOfYear, dayOfMonth);
                switch (view.getId()) {
                    case R.id.date_from:
                        dateFrom.setText(dateFormat.format(newCalendar.getTime()));
                        break;
                    case R.id.date_to:
                        dateTo.setText(dateFormat.format(newCalendar.getTime()));
                        break;
                }

            }
        }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        if (view.getId() == R.id.date_to) {
            if (!dateFrom.getText().toString().isEmpty()) {
                dateFromString = dateFrom.getText().toString();
                SimpleDateFormat dateFormatTemp = new SimpleDateFormat("dd.MM.yyyy");
                Date convertedDate = new Date();
                try {
                    convertedDate = dateFormatTemp.parse(dateFromString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //ограничение. нельзя выбрать дату отъзда ранее даты приезда
                chooseDate.getDatePicker().setMinDate(convertedDate.getTime());
            }
        }
        if (view.getId() == R.id.date_from) {
            //ограничение. нельзя выбрать дату приезда ранее сегодняшней даты
            chooseDate.getDatePicker().setMinDate(calendar.getTimeInMillis());
        }
    }


    //вызывается когда сменился фокус на EditText
    //Потом проверка, если в фокусе, то инициализировать диалог выбора даты и показать
    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        switch (view.getId()) {
            case R.id.country:
                if (hasFocus) {
                    if (autoCompleteTextViewCountry.length() > 0) {
                        autoCompleteTextViewCountry.getText().clear();
                        autoCompleteTextViewCity.getText().clear();
                    }
                }
                break;
            case R.id.city:
                if (hasFocus) {
                    if (autoCompleteTextViewCountry.length() == 0) {
                        autoCompleteTextViewCity.setError(getResources().getString(R.string.error_city));
                    }
                }
                break;
            case R.id.date_from:
            case R.id.date_to:
                try {
                    initDateDialog(view);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (hasFocus) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                    switch (view.getId()) {
                        case R.id.date_from:
                            chooseDate.show();
                            break;
                        case R.id.date_to:
                            if (dateFrom.getText().toString().isEmpty()) {
                                Toast.makeText(CreateNewTripActivity.this, getResources().getString(R.string.warning_enter_date_from), Toast.LENGTH_SHORT).show();
                            } else chooseDate.show();
                            break;

                    }
                }
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataBaseDestinationHelper.close();
    }
}