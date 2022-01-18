package com.example.dicionario.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.ActionMode;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.example.dicionario.R;
import com.example.dicionario.model.Country;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {

    static List<Country> listCountry;
    Context context;
    int position1=0;
    private androidx.appcompat.view.ActionMode contextAction;
    private ViewUserActivity action;

    public RecyclerAdapter(ViewUserActivity action, Context context,List<Country> listCountry){
        this.action = action;
        this.listCountry = listCountry;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_adapter, parent, false);
        RecyclerHolder recyclerHolder = new RecyclerHolder(view);

        recyclerHolder.contentItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dataCountry=new Intent(context,DataCountry.class);
                int position=recyclerHolder.getAdapterPosition();
                dataCountry.putExtra("nameCountry", listCountry.get(position).getCountry());
                dataCountry.putExtra("officialNameCountry",listCountry.get(position).getOfficialName());
                dataCountry.putExtra("Region",listCountry.get(position).getRegion());
                dataCountry.putExtra("Subregion",listCountry.get(position).getSubregion());
                dataCountry.putExtra("Area",listCountry.get(position).getArea());
                dataCountry.putExtra("Population",listCountry.get(position).getPopulation());
                //dataCountry.putExtra("Borders",listCountry.get(position).getBorder());
                dataCountry.putExtra("Flag",listCountry.get(position).getFlag());

                context.startActivity(dataCountry);

            }
        });

        return recyclerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, @SuppressLint("RecyclerView") int position) {
        Country country = listCountry.get(position);
        String image = listCountry.get(position).getFlag();
        holder.rHtextViewCountryName.setText(country.getCountry());
        holder.rHtextViewSubRegion.setText(country.getSubregion());
        holder.rHtextViewPopulation.setText(country.getPopulation());
        Glide.with(context).load(image).into(holder.imageView);

        holder.contentItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (contextAction != null){
                    return false;
                }

                position1 = position;
                contextAction = action.startSupportActionMode(callBack);
                return true;
            }
        });


    }

    private androidx.appcompat.view.ActionMode.Callback callBack = new androidx.appcompat.view.ActionMode.Callback(){
        @Override
        public boolean onCreateActionMode(androidx.appcompat.view.ActionMode actionM, Menu menu){
            actionM.getMenuInflater().inflate(R.menu.menu, menu);
            actionM.setTitle("Delete");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int id = item.getItemId();

            switch (id){
                case R.id.optionDelete:
                    AlertDialog.Builder deleteDialog = new AlertDialog.Builder(context).setTitle("Delete country").setMessage("This country will be eliminated").setIcon(R.drawable.ic_alert)
                            .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    listCountry.remove(position1);
                                    notifyItemRemoved(position1);
                                }
                            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });

                    deleteDialog.show();
                    mode.finish();
                    break;
            }

            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            contextAction = null;
        }


    };

    @Override
    public int getItemCount() {
        return listCountry.size();
    }

    public class RecyclerHolder extends ViewHolder{

        TextView rHtextViewCountryName,rHtextViewSubRegion,rHtextViewLanguage,rHtextViewPopulation;
        ImageView imageView;
        CardView contentItem;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            rHtextViewCountryName = (TextView) itemView.findViewById(R.id.rAtextViewCountryName);
            rHtextViewSubRegion=(TextView)itemView.findViewById(R.id.rAtextViewSubRegion);
            rHtextViewPopulation=(TextView)itemView.findViewById(R.id.rAtextViewPopulation);
            imageView = (ImageView) itemView.findViewById(R.id.rAimageView);

            contentItem=(CardView) itemView.findViewById(R.id.countryAdapter);
        }
    }
}


