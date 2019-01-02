package com.example.avinash.mycontact;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> implements SwipeUtil.ItemTouchHelperAdapter {
    RecycleListener listener;
    private List<Contact> phoneList;
    private Context context;


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public RecycleAdapter(Context context, List<Contact> phoneList) {
        this.phoneList = phoneList;

        this.context = context;


    }


//    public Cursor swapCursor(Cursor cursor1){
//
//        if(cursor1==cur)
//
//        return cursor1;
//    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context1 = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.row, null);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Contact contact = phoneList.get(position);

        holder.tvname.setText(contact.getName());
        holder.tvphone.setText(contact.getPhone().toString());


        holder.setIsRecyclable(true);


    }


    @Override
    public int getItemCount() {
        return phoneList.size();
    }



    @Override
    public boolean onItemMove(int fromPosition,int toPosition) {

                Collections.swap(phoneList, fromPosition, toPosition);

        notifyItemMoved(fromPosition, toPosition);
        return true;


    }

    @Override
    public void onItemDismiss(int position) {

        phoneList.remove(position);
        notifyItemRemoved(position);

    }


    //VIEW HOLDER CLASS
    public class ViewHolder extends RecyclerView.ViewHolder {


        final TextView tvname, tvphone;
       public SearchView searchView;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            tvname = itemView.findViewById(R.id.tvName);
            tvname.setText("no name");
            tvphone = itemView.findViewById(R.id.number_textView);
            searchView = itemView.findViewById(R.id.search);




//
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //istener.OnClick(v, getAdapterPosition());
                    int postion = getAdapterPosition();
                    // String number = tvphone.getText().toString();
                    Toast.makeText(context, tvname.getText(), Toast.LENGTH_SHORT).show();

                    //Intent intent = new Intent(Intent.ACTION_DIAL,Uri.fromParts("tel",number,null));
                    //intent.setData(Uri.parse(number));
                   // context.startActivity(intent);
                    Intent intent = new Intent(context, PhoneDetail.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("number", tvphone.getText().toString());
                    bundle.putString("name", tvname.getText().toString());
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                }
            });


        }


    }

}
