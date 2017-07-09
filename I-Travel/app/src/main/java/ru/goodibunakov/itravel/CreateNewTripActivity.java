package ru.goodibunakov.itravel;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

public class CreateNewTripActivity extends AppCompatActivity {

    ElegantNumberButton numberButton;
    AlertDialog.Builder ad;
    String[] cities = {"Москва, писка", "Самара", "Вологда", "Волгоград", "Саратов", "Воронеж"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_trip);

        numberButton = (ElegantNumberButton) findViewById(R.id.elegant_number_button);
        Button createTrip = (Button) findViewById(R.id.btn_create_trip);
        createTrip.setClickable(false);
        AutoCompleteTextView autoCompleteTextViewCountry = (AutoCompleteTextView) findViewById(R.id.country);
        EditText dateFrom = (EditText) findViewById(R.id.date_from);
        dateFrom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                DialogFragment dateDialog = new DatePicker();
                dateDialog.show(getSupportFragmentManager(), "datePicker");
            }
        });

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, cities);
        //autoCompleteTextViewCountry.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        autoCompleteTextViewCountry.setAdapter(adapter);

        //обработка кнопки "Создать поездку"
        createTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        numberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                // Добавляем новый ImageView
                if (oldValue < newValue) {
                    ImageView imageView = new ImageView(CreateNewTripActivity.this);
                    imageView.setImageResource(R.drawable.i_travel_logo_1);
                    ViewGroup.LayoutParams imageViewLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    imageViewLayoutParams.height = 300;
                    imageViewLayoutParams.width = 300;
                    imageView.setLayoutParams(imageViewLayoutParams);
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
}
