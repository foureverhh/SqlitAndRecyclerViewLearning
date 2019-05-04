package com.nackademin.foureverhh.sqlitandrecyclerviewlearning;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.nackademin.foureverhh.sqlitandrecyclerviewlearning.GroceryContract.*;

public class GroceryAdapter extends RecyclerView.Adapter <GroceryAdapter.GroceryViewHolder>{

    private Context mContext;
    private Cursor mCursor;

    public GroceryAdapter(Context context, Cursor cursor){
       mContext = context;
       mCursor = cursor;
    }

    public class GroceryViewHolder extends RecyclerView.ViewHolder{

        public TextView nameText;
        public TextView countText;

        public GroceryViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.textView_name_item);
            countText = itemView.findViewById(R.id.textView_amount_item);
        }
    }

    @NonNull
    @Override
    public GroceryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.grocery_item,viewGroup,false);
        return new GroceryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryViewHolder groceryViewHolder, int i) {
        if(!mCursor.moveToPosition(i)){
            return;
        }

        String name = mCursor.getString(mCursor.getColumnIndex(GroceryEntry.COLUMN_NAME));
        int amount = mCursor.getInt(mCursor.getColumnIndex(GroceryEntry.COLUMN_AMOUNT));
        long id = mCursor.getLong(mCursor.getColumnIndex(GroceryEntry._ID));

        groceryViewHolder.nameText.setText(name);
        groceryViewHolder.countText.setText(String.valueOf(amount));
        groceryViewHolder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if(mCursor != null){
            mCursor.close();
        }

        mCursor = newCursor;

        if(newCursor != null){
            notifyDataSetChanged();
        }
    }
}
