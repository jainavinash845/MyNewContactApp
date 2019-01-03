package com.example.avinash.retrofit;



import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

import retrofit2.Callback;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private List<Repo> dataList;
    private RecycleListner listner;


    public RecyclerAdapter(List<Repo> dataList, RecycleListner listner){
        this.dataList = dataList;
        this.listner = listner;

    }

    public void addIteam(List<Repo> insertlist)
    {

        dataList = insertlist;
        dataList.addAll(insertlist );
        this.notifyDataSetChanged();
    }

    public void insertData(List<Repo> newlist,int Pos){

    }


    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent,false);
        return new ViewHolder(view,listner);

    }


        //  notifyDataSetChanged();
        // this.notifyDataSetChanged();

    public void onBindViewHolder(ViewHolder holder, int p) {
        int pos = holder.getAdapterPosition();

        Repo repo = dataList.get(pos);
        holder.name.setText("Name: "+repo.getName());
        holder.forks.setText("forks: "+repo.getForks().toString());
        Picasso.with(holder.imageView.getContext()).load(repo.getOwner().getUrl()).into(holder.imageView);

    }


    //        Glide.with(holder.imageView.getContext).load(repo.get(position).getImage_path()).into(holder.imageView);

//       Picasso.Builder builder = new Picasso.Builder(holder.imageView.getContext());
//        builder.downloader(new OkHttpDownloader(holder.imageView.getContext()));
//
//        builder.build().load(dataList.get(pos).getOwner().getUrl())
//                .placeholder((R.drawable.ic_launcher_background))
//                .error(R.drawable.ic_launcher_background)
//                .into(holder.imageView);




//    public void updateData(List<Repo> dataList) {
   //         datalist.clear();
//        this.dataList =dataList;
//        dataList.addAll(dataList);
//        notifyDataSetChanged();
//
//    }
    @Override
    public int getItemCount() {
//        if(dataList!=null) {
//            return  dataList.size();
//        }
//        else {
//            return 0;

        return dataList.size();
    }



    public  class ViewHolder extends RecyclerView.ViewHolder {


        TextView name,forks;
        SearchView sv;
        private ImageView imageView;


        public ViewHolder(View itemView, final RecycleListner listner) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            forks = itemView.findViewById(R.id.forks);
            imageView = itemView.findViewById(R.id.image);
            sv = itemView.findViewById(R.id.sv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.OnClick(v, getAdapterPosition());
                }
            });

        }

    }

}
