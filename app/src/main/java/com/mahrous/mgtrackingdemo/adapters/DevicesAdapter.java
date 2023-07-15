package com.mahrous.mgtrackingdemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mahrous.mgtrackingdemo.R;
import com.mahrous.mgtrackingdemo.data.Device;
import com.mahrous.mgtrackingdemo.ui.map.MapsActivity;

import java.util.List;

public class DevicesAdapter extends RecyclerView.Adapter<DevicesAdapter.ViewHolder> {
    Context context;
    List<Device> list;
    ItemClick itemClick ;
    private int row;

    public DevicesAdapter(Context context, List<Device> list) {
        this.context = context;
        this.list = list;


    }

    public DevicesAdapter(Context context, List<Device> list, ItemClick itemClick) {
        this.context = context;
        this.list = list;
        this.itemClick = itemClick;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_row_device, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Device model = list.get(position);
        holder.name.setText(model.getDriver_name());
        holder.account.setText(model.getAccount());
        holder.serial.setText(model.getSerial());




        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick.updateLocation(model.getSerial());

            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout container;
        TextView name, account, serial;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.user_name);
            account = itemView.findViewById(R.id.account);
            serial = itemView.findViewById(R.id.serial);
            container = itemView.findViewById(R.id.container);

        }
    }

    public interface ItemClick{

        void updateLocation (String serial);

    }

}
