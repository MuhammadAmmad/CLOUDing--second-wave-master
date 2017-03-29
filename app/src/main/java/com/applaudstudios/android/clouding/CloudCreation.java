package com.applaudstudios.android.clouding;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wjplaud83 on 2/18/17.
 */

public class CloudCreation extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    String mCurrentPhotoPath;

    private EditText cloudNameView;
    private EditText cloudRatingView;
    //   private EditText cloudQuantityView;
    private EditText cloudDescriptionView;
    private ImageView cloudImage;
    private boolean imageAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_creation);

        imageAdded = false;

        cloudNameView = (EditText) findViewById(R.id.edit_cloud_name);
        cloudRatingView = (EditText) findViewById(R.id.edit_cloud_rating);
        //   cloudQuantityView = (EditText) findViewById(R.id.edit_pro_quantity);
        cloudDescriptionView = (EditText) findViewById(R.id.edit_cloud_description);

        cloudImage = (ImageView) findViewById(R.id.add_image);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            imageAdded = true;
            cloudImage.setImageBitmap(ImageTools.imageProcess(mCurrentPhotoPath));

        }
    }

    public void addCloud(View view) {

        String cloudName = cloudNameView.getText().toString();
        String cloudRating = cloudRatingView.getText().toString();
         // String cloudQuantity = cloudQuantityView.getText().toString();
        String cloudDescription = cloudDescriptionView.getText().toString();

        if (cloudName.isEmpty() || cloudDescription.isEmpty() || cloudRating.isEmpty() || !imageAdded) {
            Toast.makeText(this, R.string.empty_warning, Toast.LENGTH_LONG).show();
        } else {
            DatabaseHelper dbHelp = new DatabaseHelper(this);

            dbHelp.addCloud(
                    -1,
                    cloudName,
                    cloudDescription,
                    Float.valueOf(cloudRating),
                    mCurrentPhotoPath);

            this.finish();
        }

    }

    public void addImage(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }
}
