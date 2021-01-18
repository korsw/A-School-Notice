package com.lsu.aschoolnotice;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShowNoticeBoardAdapter extends RecyclerView.Adapter<ShowNoticeBoardAdapter.ViewHolder>{
    private ArrayList<ShowNoticeBoardItem> mData = null;

    private OnItemClickListener mListener = null ;

    private int ClickPosition = 0;

    // 생성자에서 데이터 리스트 객체를 전달받음.
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

    //커스텀 리스너 인터페이스
    public interface OnItemClickListener {
        void onItemClick(View v, int position) ;
    }

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
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

    // getItemCount() - 전체 데이터 갯수 리턴.
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
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        // 리스너 객체의 메서드 호출.
                        if (mListener != null) {
                            mListener.onItemClick(v, pos) ;
                        }
                    }
                }
            });
        }
    }

    public int getPosition(){
        return ClickPosition;
    }
}
