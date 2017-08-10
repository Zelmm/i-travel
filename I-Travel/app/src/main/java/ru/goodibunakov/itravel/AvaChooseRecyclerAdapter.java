package ru.goodibunakov.itravel;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by GooDi on 10.08.2017.
 */

public class AvaChooseRecyclerAdapter extends RecyclerView.Adapter<AvaChooseRecyclerAdapter.AvaViewHolder> {

    private static final String TAG = "AvaChooseAdapter";

    List<Integer> resourceIds = Arrays.asList(
            R.drawable.avatars_01, R.drawable.avatars_02, R.drawable.avatars_03,
            R.drawable.avatars_04, R.drawable.avatars_05, R.drawable.avatars_06,
            R.drawable.avatars_07, R.drawable.avatars_08, R.drawable.avatars_09,
            R.drawable.avatars_10, R.drawable.avatars_11, R.drawable.avatars_12,
            R.drawable.avatars_13, R.drawable.avatars_14, R.drawable.avatars_15,
            R.drawable.avatars_16, R.drawable.avatars_17, R.drawable.avatars_18,
            R.drawable.avatars_19, R.drawable.avatars_20, R.drawable.avatars_21,
            R.drawable.avatars_22, R.drawable.avatars_23, R.drawable.avatars_24,
            R.drawable.avatars_25, R.drawable.avatars_26, R.drawable.avatars_27,
            R.drawable.avatars_28, R.drawable.avatars_29, R.drawable.avatars_30,
            R.drawable.avatars_31, R.drawable.avatars_32, R.drawable.avatars_33,
            R.drawable.avatars_34, R.drawable.avatars_35, R.drawable.avatars_36,
            R.drawable.avatars_37, R.drawable.avatars_38, R.drawable.avatars_39,
            R.drawable.avatars_40, R.drawable.avatars_41, R.drawable.avatars_42,
            R.drawable.avatars_43, R.drawable.avatars_44, R.drawable.avatars_45,
            R.drawable.avatars_46, R.drawable.avatars_47, R.drawable.avatars_48,
            R.drawable.avatars_49, R.drawable.avatars_50, R.drawable.avatars_51,
            R.drawable.avatars_52, R.drawable.avatars_53, R.drawable.avatars_54,
            R.drawable.avatars_55, R.drawable.avatars_56, R.drawable.avatars_57,
            R.drawable.avatars_58, R.drawable.avatars_59, R.drawable.avatars_60,
            R.drawable.avatars_61, R.drawable.avatars_62, R.drawable.avatars_63,
            R.drawable.avatars_64, R.drawable.avatars_65, R.drawable.avatars_66);


    public static class AvaViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv;

        public AvaViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.ava_item_recycler_imageview);
        }
    }


    @Override
    public AvaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ava_item, parent, false);
        v.setPadding(4, 4, 4, 4);
        return new AvaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AvaViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder position: " + position + " | holder obj:" + holder.toString());
        holder.iv.setImageResource(resourceIds.get(position));
        holder.iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        Picasso.with(holder.iv.getContext())
//                .load(resourceIds.get(position))
//                .fit()
//                .centerInside()
//                .into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return resourceIds.size();
    }

//    public static class ClickableViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        OnClickListener onClickListener;
//
//
//        public ClickableViewHolder(View itemView, OnClickListener onClickListener) {
//            super(itemView);
//            this.onClickListener = onClickListener;
//            itemView.setOnClickListener(this);
//            //itemView.setOnLongClickListener(this);
//        }
//
//        @Override
//        public void onClick(View view) {
//            onClickListener.onClick(view, getPosition());
//        }
//
//        public interface OnClickListener {
//            void onClick(View view, int position);
//        }
//    }
}