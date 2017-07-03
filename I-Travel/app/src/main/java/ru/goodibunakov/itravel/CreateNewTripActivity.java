package ru.goodibunakov.itravel;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.nex3z.flowlayout.FlowLayout;

public class CreateNewTripActivity extends AppCompatActivity {

    ElegantNumberButton numberButton;
    AlertDialog.Builder ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_trip);

        numberButton = (ElegantNumberButton) findViewById(R.id.elegant_number_button);
        final FlowLayout flowLayout = (FlowLayout) findViewById(R.id.flow_layout);

        //добавление 1 аватара с самого начала ибо в поездке минимум 1 человек
        ImageView imageView = new ImageView(CreateNewTripActivity.this);
        imageView.setImageResource(R.drawable.i_travel_logo_1);
        ViewGroup.LayoutParams imageViewLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageViewLayoutParams.height = 300;
        imageViewLayoutParams.width = 300;
        imageView.setLayoutParams(imageViewLayoutParams);
        flowLayout.addView(imageView);

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
                    flowLayout.addView(imageView);
                } else {
                    //Удаляем
                    ad = new AlertDialog.Builder(CreateNewTripActivity.this);
                    ad.setTitle(getResources().getString(R.string.title_delete_person));  // заголовок
                    ad.setMessage(getResources().getString(R.string.dialog_aushure)); // сообщение
                    ad.setPositiveButton(getResources().getString(R.string.button_ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int arg1) {
                            Toast.makeText(CreateNewTripActivity.this, getResources().getString(R.string.person_ok),
                                    Toast.LENGTH_LONG).show();
                            flowLayout.removeViewAt(flowLayout.getChildCount() - 1);
                        }
                    });
                    ad.setNegativeButton(getResources().getString(R.string.button_cancel), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {
                            Toast.makeText(CreateNewTripActivity.this, getResources().getString(R.string.person_delete), Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                    ad.setCancelable(false);
                    ad.show();
                }
            }
        });



    }

//    public void onClick(View v) {
//        ad.show();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_new_trip_activity, menu);
        return true;
    }
}
