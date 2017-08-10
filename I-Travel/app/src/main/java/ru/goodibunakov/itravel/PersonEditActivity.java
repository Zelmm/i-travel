package ru.goodibunakov.itravel;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class PersonEditActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_person_edit));
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbarlayout);


        ImageView ava = (ImageView) findViewById(R.id.imageview_ava);
        ava.setImageResource(R.drawable.avatars_man);
        ava.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.imageview_ava):
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.title_ava_dialog);
                final View view2 = getLayoutInflater().inflate(R.layout.dialog_ava_choose, null);
                builder.setView(view2);
                RecyclerView avaRecycler = (RecyclerView) view2.findViewById(R.id.ava_recycler);
                avaRecycler.setLayoutManager(new GridLayoutManager(PersonEditActivity.this, 6));
                avaRecycler.setHasFixedSize(true);
                avaRecycler.setItemAnimator(new DefaultItemAnimator());
                avaRecycler.setAdapter(new AvaChooseRecyclerAdapter());
                builder.setPositiveButton(R.string.btn_ava_dialog_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNegativeButton(R.string.btn_ava_dialog_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog chooseAva = builder.create();
                chooseAva.show();
                break;
        }
    }
}
