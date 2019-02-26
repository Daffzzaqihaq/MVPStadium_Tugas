package com.daffzzaqihaq.mvpstadium.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.daffzzaqihaq.mvpstadium.Model.StadiumData;
import com.daffzzaqihaq.mvpstadium.R;
import com.daffzzaqihaq.mvpstadium.Utils.Constant;
import com.daffzzaqihaq.mvpstadium.View.DetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {


    private Bundle bundle;
    private Context context;
    private List<StadiumData> stadiumDataList;

    public MainAdapter(Context context, List<StadiumData> stadiumDataList) {
        this.context = context;
        this.stadiumDataList = stadiumDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final StadiumData stadiumData =stadiumDataList.get(i);

        viewHolder.txtNamaTeam.setText(stadiumData.getStrStadium());
        Glide.with(context).load(stadiumData.getStrStadiumThumb()).into(viewHolder.imgAvatar);
        RequestOptions options = new RequestOptions().error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_broken_image);
        Glide.with(context).load(stadiumData.getStrStadiumThumb()).apply(options).into(viewHolder.imgAvatar);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putString(Constant.id, stadiumData.getIdTeam());
                context.startActivity(new Intent(context, DetailActivity.class).putExtras(bundle));
            }
        });


    }

    @Override
    public int getItemCount() {
        return stadiumDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgAvatar)
        ImageView imgAvatar;
        @BindView(R.id.txtNamaTeam)
        TextView txtNamaTeam;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
