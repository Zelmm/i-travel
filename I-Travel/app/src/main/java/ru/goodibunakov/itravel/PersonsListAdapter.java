package ru.goodibunakov.itravel;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;


public class PersonsListAdapter extends RecyclerView.Adapter<PersonsListAdapter.PersonsViewHolder> {

    protected List<HashMap<String, String>> persons;

    public PersonsListAdapter(List persons) {
        this.persons = persons;
    }

    public static class PersonsViewHolder extends RecyclerView.ViewHolder {

        protected ImageView ava;
        protected TextView name;

        public PersonsViewHolder(View itemView) {
            super(itemView);
            ava = (ImageView) itemView.findViewById(R.id.personlist_ava);
            name = (TextView) itemView.findViewById(R.id.personlist_name);
        }
    }

    @Override
    public PersonsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_and_name_avatar, parent, false);
        return new PersonsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PersonsViewHolder holder, int position) {
        //holder.ava.setImageDrawable(persons.get(position).get(CreateNewTripActivity.AVA).);
        holder.ava.setAdjustViewBounds(true);
        holder.name.setText(persons.get(position).get(CreateNewTripActivity.NAME));
        Log.e("адаптер", String.valueOf(persons));
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}
