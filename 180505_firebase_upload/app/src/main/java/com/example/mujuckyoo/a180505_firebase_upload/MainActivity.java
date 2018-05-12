package com.example.mujuckyoo.a180505_firebase_upload;

import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    final int PICK_IMAGE_REQUEST = 100;
    Button pick, upload;
    ImageView iv;
    List<Uri> filePath = new ArrayList<Uri>();
    FirebaseStorage storage;
    List<Image> images;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pick = findViewById(R.id.btn_pick);
        upload = findViewById(R.id.btn_upload);
        iv = findViewById(R.id.image_view);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        Log.d("tttt", storage.toString());
        Log.d("tttt", storageReference.toString());

    }

    public void pick_clicked(View view) {

        ImagePicker.create(this)
                .toolbarImageTitle("Tap to select") // image selection title
                .toolbarArrowColor(Color.BLACK) // Toolbar 'up' arrow color
                .multi() // multi mode (default mode)
                .limit(10) // max images can be selected (99 by default)
                .showCamera(true) // show camera or not (true by default)
                .start(); // start image picker activity with request code

        //Intent intent = new Intent();
        //intent.setAction(Intent.ACTION_GET_CONTENT);
        //intent.setType("image*");

        // startActivityForResult(intent, PICK_IMAGE_REQUEST); OLD

        //startActivityForResult(Intent.createChooser(intent, "Select Picture"),PICK_IMAGE_REQUEST);


    }

    public void upload_clicked(View view) {

        for(int i =0; i<filePath.size(); i++) {


            if (filePath.get(i) != null) {

                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Uploading...");
                progressDialog.show();

                StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());

                ref.putFile(filePath.get(i))
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(MainActivity.this, "Upload Done", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                filePath.clear();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "Upload Failed", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount()) * 100;
                                progressDialog.setMessage((int) progress + "%");
                            }
                        });

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            // Get a list of picked images
            images = ImagePicker.getImages(data);
        }

        for(int i = 0; i < images.size(); i++ ) {
            filePath.add(Uri.fromFile(new File(images.get(0).getPath())));
        }

        Log.d("aaa", String.valueOf(filePath.size()));
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data !=null && data.getData() !=null ) {

            Log.d("tttttest", data.getData().toString());

            filePath = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                iv.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }*/
}
