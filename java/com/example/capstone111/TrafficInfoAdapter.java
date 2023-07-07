package com.example.capstone111;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstone111.datamodel.Path;

import java.util.ArrayList;

public class TrafficInfoAdapter extends RecyclerView.Adapter<TrafficInfoAdapter.ViewHolder> {

    Context context;

    ArrayList<Path> items = new ArrayList<Path>();

    OnItemClickListener listener;

    public static interface  OnItemClickListener{
        public void onItemClick(ViewHolder holder, View view, int position);
    }
    public TrafficInfoAdapter(Context context){
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Path item){
        items.add(item);
    }
    public void Clear(){
        items.clear();
    }

    public void addItems(ArrayList<Path> items){
        this.items = items;
    }

    public Path getItem(int position){
        return items.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.list_item2, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Path item = items.get(position);

        holder.setItem(item, position);

        holder.setOnItemClickListener(listener);
    }



    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView TrafficInfo_TextView;
        TextView ToTal_Time_TextView;

        OnItemClickListener listener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TrafficInfo_TextView = (TextView) itemView.findViewById(R.id.TrafficInfo_TextView);
            ToTal_Time_TextView = (TextView) itemView.findViewById(R.id.ToTal_Time_TextView);

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

        public void setItem(Path item, int position){
            TrafficInfo_TextView.setText("");
            ToTal_Time_TextView.setText("");
            ProcessItem(item, position);
        }

        public void println(String str){
            TrafficInfo_TextView.append(str + "\n");
        }

        public void printt(String str){
            TrafficInfo_TextView.append(str + " ");
        }

        public void ProcessItem(Path data, int position){

            int TransitCount = data.getSubPath().length;
            ToTal_Time_TextView.setText(data.getInfo().getTotalTime());

            println((position + 1) + "번째 교통 정보" );
            println("");

            for(int j = 0; j < TransitCount; j++){

                String type = data.getSubPath()[j].getTrafficType();

                if (type.equals("1")){

                    println("지하철 노선명: " +
                            data.getSubPath()[j].getLane()[0].getName());


                    println("승차 역: " + data.getSubPath()[j].getStartName());
                    println("하자 역: " + data.getSubPath()[j].getEndName());

                }else if(type.equals("2")){

                    int lane2 = data.getSubPath()[j].getLane().length;

                    printt("탑승 가능 버스 번호: ");
                    for(int k = 0; k < lane2; k++){

                        printt(data.getSubPath()[j].getLane()[k].getBusNo());


                    }
                    println("");
                    println("승차 역: " + data.getSubPath()[j].getStartName() );
                    println("하차 역: " + data.getSubPath()[j].getEndName());

                }else if(type.equals("3")){

                    println("도보 거리: " + data.getSubPath()[j].getDistance());
                }else{
                    println("에러");
                }

                println("******************");
            }

        }
    }


}
