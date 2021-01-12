package com.lsu.aschoolnotice;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShowNoticeBoardAdapter extends RecyclerView.Adapter<ShowNoticeBoardAdapter.ViewHolder>{
    private ArrayList<ShowNoticeBoardItem> mData = null;

    public ShowNoticeBoardAdapter(ArrayList<ShowNoticeBoardItem> data) {
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.noticeboarditem, parent, false);
        ShowNoticeBoardAdapter.ViewHolder vh = new ShowNoticeBoardAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mData == null) {
            return;
        }
        ShowNoticeBoardItem item = mData.get(position);
        Log.v("KKW", " item ::  " + position);
        holder.imageView.setBackground(item.getIconDrawable());
        holder.mainText.setText(item.getMainTitle());
        holder.subText.setText(item.getSubTitle());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView mainText;
        TextView subText;

        ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.item_image);
            mainText = itemView.findViewById(R.id.icon_main_text);
            subText = itemView.findViewById(R.id.icon_sub_text);
        }
    }
}
