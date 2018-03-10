package com.example.mujuckyoo.a180309_homework;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.List;

/**
 * Created by mujuckyoo on 2018. 3. 9..
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context context;
    List<Item> items;
    int item_layout;

    public RecyclerAdapter(Context context, List<Item> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Item item = items.get(position);
        //Drawable drawable = ContextCompat.getDrawable(context, (int) item.getImage());
        //holder.image.setBackground(drawable);

        holder.name.setText(item.getName());
        holder.num.setText(item.getNum());
        Bitmap bm = openPhoto(item.getImage());
        if (bm != null) {
            holder.image.setImageBitmap(bm);
        } else {
            holder.image.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher_background));
        }
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, item.getName(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, MainActivity.class);
                i.putExtra("phone_number", item.getNum());
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView num;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            num = itemView.findViewById(R.id.number);
            cardview = itemView.findViewById(R.id.cardview);
        }
    }

    private Bitmap openPhoto(long contactId) {
        Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,
                contactId);
        InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(), contactUri);

        if (input != null) {
            return BitmapFactory.decodeStream(input);
        }

        return null;
    }
}