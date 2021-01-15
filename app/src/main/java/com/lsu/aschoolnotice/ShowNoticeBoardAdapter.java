package com.lsu.aschoolnotice;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
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

    private OnItemClickListener mListener = null ;

    // Item의 클릭 상태를 저장할 array 객체
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    // 직전에 클릭됐던 Item의 position
    private int prePosition = -1;

    public ShowNoticeBoardAdapter(ArrayList<ShowNoticeBoardItem> data) {
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.noticeboarditem, parent, false);
        ShowNoticeBoardAdapter.ViewHolder vh = new ShowNoticeBoardAdapter.ViewHolder(view);

        return vh;
    }


    public interface OnItemClickListener {
        void onItemClick(View v, int position) ;
    }

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mData == null) {
            return;
        }
        ShowNoticeBoardItem item = mData.get(position);
        Log.v("KKW", " item ::  " + position);
        holder.mainText.setText(item.getMainTitle());
        holder.subText.setText(item.getSubTitle());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mainText;
        TextView subText;

        ViewHolder(View itemView) {
            super(itemView);

            mainText = itemView.findViewById(R.id.icon_main_text);
            subText = itemView.findViewById(R.id.icon_sub_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO : process click event.
                }
            });
        }
    }
}
