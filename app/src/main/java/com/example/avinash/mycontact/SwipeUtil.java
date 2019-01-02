package com.example.avinash.mycontact;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.menu.ListMenuItemView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class SwipeUtil extends ItemTouchHelper.Callback {

    private RecycleAdapter.ViewHolder viewHolder;
    private Context context;
    private RecycleAdapter adapter;
    public View ItemView;
    public List<Contact> phoneList;
    public TextView textView;


    public SwipeUtil(Context context,RecycleAdapter adapter) {
        //Isuper(dragDirg,swipeDirs);
        this.context = context;
        this.adapter = adapter;
    }

    public interface ItemTouchHelperAdapter {
        public boolean onItemMove(int fromPosition, int toPosition);

        public void onItemDismiss(int position);
    }


    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }


    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull ViewHolder viewHolder) {
        int dragFlag = ItemTouchHelper.UP| ItemTouchHelper.DOWN;
       int swipFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlag,swipFlag );
    }



    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull ViewHolder dragged, @NonNull ViewHolder target) {
        adapter.onItemMove(dragged.getAdapterPosition(),target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull ViewHolder viewHolder, int position) {

            adapter.onItemDismiss(viewHolder.getAdapterPosition());





    }
}
