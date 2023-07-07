package com.example.capstone111;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    Context context;

    // 하나의 리스트에 들어 갈 데이터 모델 또는 아이템
    ArrayList<TrevelDataModel> items = new ArrayList<TrevelDataModel>();

    // 터치 이벤트 용 리스너
    OnItemClickListener listener;

    public static interface  OnItemClickListener{
        public void onItemClick(ViewHolder holder, View view, int position);
    }
    public ListAdapter(Context context){
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    // 아이템 크기 반환 메서드
    @Override
    public int getItemCount() {
        return items.size();
    }

    // "하나의" 아이템 추가 메서드
    public void addItem(TrevelDataModel item){
        items.add(item);
    }

    // 아이템 전체 초기화 메서드
    public void Clear(){
        items.clear();
    }


    // "아이템 묶음" 추가 메서드
    public void addItems(ArrayList<TrevelDataModel> items){
        this.items = items;
    }

    // "하나의" 아이템 반환
    public TrevelDataModel getItem(int position){
        return items.get(position);
    }

    // "아이템 묶음" 반환
    public ArrayList<TrevelDataModel> getItems(){
        return items;
    }

    @NonNull
    @Override // ViewHolder 인스턴스를 생성하고 초기화 후 반환하는 메서드
              // 초기에 한 번 호출되서 ViewHolder를 생성함
              // 자동 호출
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.list_item, parent, false);

        return new ViewHolder(itemView);
    }

              // * onCreateViewHolder() 동작 후 onBindViewHolder() 동작

    @Override // 생성된 인스턴스에 실제 값 세팅하는 메서드( 하나의 리사이클러 뷰 아이템 완성)
              // 리사이클러 뷰의 각 아이템이 표시될 때마다 호출
              // 자동 호출
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                                            // ^ onCreateViewHolder() 메서드로 생성된 부 홀더 인스턴스
        // N 번째 아이템 모델을 생성된 뷰홀더 객체에 추가
        TrevelDataModel item = items.get(position);
        holder.setItem(item);

        holder.setOnItemClickListener(listener);
    }

    // 리사이클러 뷰에 표시 될 하나의 아이템
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView Lat;
        TextView Lag;
        TextView Destination_Name;

        OnItemClickListener listener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Lat = (TextView) itemView.findViewById(R.id.Lat_value);
            Lag = (TextView) itemView.findViewById(R.id.Lag_value);
            Destination_Name = (TextView) itemView.findViewById(R.id.Destination_Name);

            // 터치 이벤트 용 리스너
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAbsoluteAdapterPosition();
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });

        }

        public void setOnItemClickListener(OnItemClickListener listener){
            this.listener = listener;
        }

        // 뷰 홀더에 실제 값 세팅 메서드
        public void setItem(TrevelDataModel item){
            Lat.setText(String.valueOf(item.getLat()));
            Lag.setText(String.valueOf(item.getLag()));
            Destination_Name.setText(item.getAddress());
        }


    }


}
