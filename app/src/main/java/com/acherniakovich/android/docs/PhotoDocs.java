package com.acherniakovich.android.docs;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.widget.ImageButton;
import android.widget.ImageView;
import com.acherniakovich.android.R;

import me.iwf.photopicker.PhotoPicker;

public class PhotoDocs extends AppCompatActivity {

    private Bitmap photo;
    private ImageView setView;

    private String ts_1;
    private String ts_2;
    private String ts_3;
    private String ts_4;
    private String ts_5;

    private String phone_number = "";

    private ArrayList<File>listFile;
    private ArrayList <Bitmap> listWithBitmaps;
    private ArrayList<Uri> file_uris;


    public String imagePath = "";
    private ArrayList<String>filePaths;
    public static final String LOG_TAG = "MyLogs";

    private int ID = 0;

    public static final int PERMISSIONS_REQUEST_CODE = 0;
    public static final int FILE_PICKER_REQUEST_CODE = 1;

    private ImageView photo_docs_back,photo_docs_image_license,photo_docs_image_driver_root,photo_docs_image_root_reverse,
    photo_docs_image_sts,photo_docs_image_root_translation;
    private ImageButton button_upload_photo;
    private String changeImagePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_docs);
        init();
    }

    private void init() {
        file_uris = new ArrayList<>(5);
        listWithBitmaps = new ArrayList<>(5);
        listFile = new ArrayList<>(5);

        filePaths = new ArrayList<>(5);

        Intent intent = getIntent();
        phone_number = intent.getStringExtra("number");

        photo_docs_back = (ImageView)findViewById(R.id.photo_docs_back);
        photo_docs_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back
                finish();
            }
        });
        //photo_docs_info = (ImageView)findViewById(R.id.photo_docs_info);
        // button information

        photo_docs_image_license = (ImageView)findViewById(R.id.photo_docs_image_license);
        photo_docs_image_license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d(LOG_TAG,"id = "+photo_docs_image_license.getId());
                questionFromImage();
                ID = photo_docs_image_license.getId();

                //_setMicroImage(photo_docs_image_license);
            }
        });

        photo_docs_image_driver_root = (ImageView)findViewById(R.id.photo_docs_image_driver_root);
        photo_docs_image_driver_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ID = photo_docs_image_driver_root.getId();
                questionFromImage();

            }
        });

        photo_docs_image_root_reverse = (ImageView)findViewById(R.id.photo_docs_image_root_reverse);
        photo_docs_image_root_reverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ID = photo_docs_image_root_reverse.getId();
                questionFromImage();
            }
        });

        photo_docs_image_sts = (ImageView)findViewById(R.id.photo_docs_image_sts);
        photo_docs_image_sts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ID = photo_docs_image_sts.getId();
                questionFromImage();
            }
        });

        photo_docs_image_root_translation = (ImageView)findViewById(R.id.photo_docs_image_root_translation);
        photo_docs_image_root_translation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ID = photo_docs_image_root_translation.getId();
                questionFromImage();
            }
        });

        button_upload_photo = (ImageButton)findViewById(R.id.button_upload_photo);
        button_upload_photo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start send email ASyncTask
                sendEmail(filePaths);
            }
        });
    }

    private void questionFromImage() {
        checkPermissionsAndOpenFilePicker();
    }

    private void checkPermissionsAndOpenFilePicker() {
        String permission = Manifest.permission.READ_EXTERNAL_STORAGE;

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                showError();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{permission}, PERMISSIONS_REQUEST_CODE);
            }
        } else {
            openFilePicker();
        }
    }

    private void openFilePicker() {

        PhotoPicker.builder()
                .setPhotoCount(1)
                .setShowCamera(true)
                .setShowGif(true)
                .setPreviewEnabled(false)
                .start(this, PhotoPicker.REQUEST_CODE);
    }

    private void showError() {
        Toast.makeText(this, "Allow external storage reading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openFilePicker();
                } else {
                    showError();
                }
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if (data != null){
            ArrayList<String> photos =
                    data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            filePaths.add(photos.get(0));
            changeImagePath = "";
            changeImagePath = photos.get(0);
            if (ID!=0){
                Bitmap bitmap = BitmapFactory.decodeFile(changeImagePath);
                bitmap = Bitmap.createScaledBitmap(bitmap,photo_docs_image_license.getWidth(),photo_docs_image_license.getHeight(),true);
                ((ImageView)findViewById(ID)).setImageBitmap(bitmap);
            }
            Log.d(LOG_TAG,"photos = " + photos.get(0));
        }
    }

    private void sendEmail(ArrayList<String>files){
        BackgroundMail.newBuilder(this)
                .withAttachments(files)
                .withUsername("yantaxregmos@gmail.com")
                .withPassword("Yanreg123")
                .withMailto("Autopraim@gmail.com")
                .withType(BackgroundMail.TYPE_PLAIN)
                .withSubject("Theme")
                .withBody("Номер телефона: " + phone_number)
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                    @Override
                    public void onSuccess() {
                        //do some magic
                        Log.d(LOG_TAG,"good");
                        gotoInfoScreen();
                    }
                })
                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                    @Override
                    public void onFail() {
                        //do some magic
                        Log.d(LOG_TAG,"fail");
                    }
                })
                .send();
    }

    private void gotoInfoScreen() {
        Intent intent = new Intent(PhotoDocs.this,Info.class);
        startActivity(intent);
    }
}
