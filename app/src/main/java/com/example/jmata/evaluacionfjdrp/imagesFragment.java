package com.example.jmata.evaluacionfjdrp;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jmata on 17/03/2017.
 */
public class imagesFragment extends Fragment {


    private RecyclerView rvNumbers;
    RecyclerAdapter rAdapter;
    RecyclerView.LayoutManager layoutManager;

    String[] invitaciones = {"Boda","Bautizo","Infantil","Despedidas","Graduacion","Evento Privado"};
    Drawable[] images;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.imagesfragment_layout,container,false);
        rvNumbers = (RecyclerView)rootview.findViewById(R.id.rvNumbers);

        for(int i = 0; i<images.length; i++){
            images[i] = new BitmapDrawable(String.valueOf(R.drawable.vine));
        }


        int numberOfColumns = 3;
        layoutManager = new GridLayoutManager(getContext(),numberOfColumns);
        rvNumbers.setLayoutManager(layoutManager);
        rAdapter = new RecyclerAdapter(invitaciones);
        rvNumbers.setAdapter(rAdapter);

        return rootview;
    }

    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
        private LayoutInflater mInflater;
        private String[] data;

        public RecyclerAdapter(String[] info) {
            //this.mInflater = LayoutInflater.from(c);
            this.data = info;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_item,parent,false);
            //View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.textTitle.setText(data[position]);
            holder.textTitle.setTextColor(Color.BLACK);
            //holder.textTitle.setText("Tipo A");
        }

        @Override
        public int getItemCount() {
            return data.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            ImageView mImage;
            TextView textTitle;

            public ViewHolder(View itemView) {
                super(itemView);
                mImage = (ImageView)itemView.findViewById(R.id.imageProducto);
                textTitle = (TextView) itemView.findViewById(R.id.textTitulo);
            }

            @Override
            public void onClick(View v) {

            }
        }
    }
}
