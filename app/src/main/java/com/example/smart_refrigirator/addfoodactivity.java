package com.example.smart_refrigirator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector;

import java.util.List;


public class addfoodactivity extends AppCompatActivity {

    private Button capture_image,detact_image;
    private ImageView imageView;
    private TextView textView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfoodactivity);


        capture_image= findViewById(R.id.capture_image);
        detact_image=findViewById(R.id.detact_image);
        imageView=findViewById(R.id.image_view);
        textView=findViewById(R.id.text_display);

        capture_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
                textView.setText("");
            }

        });

        detact_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detactTextFromImmage();
            }

        });
    }




    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }
    private void detactTextFromImmage() {

        FirebaseVisionImage firebaseVisionImage=FirebaseVisionImage.fromBitmap(imageBitmap);
        FirebaseVisionTextDetector firebaseVisionTextDetector = FirebaseVision.getInstance().getVisionTextDetector();
        firebaseVisionTextDetector.detectInImage(firebaseVisionImage).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
            @Override
            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                displayTextfromImage(firebaseVisionText);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(addfoodactivity.this,"error " +e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("error" , e.getMessage());
            }
        });
    }

    private void displayTextfromImage(FirebaseVisionText firebaseVisionText) {

        List<FirebaseVisionText.Block> blockList = firebaseVisionText.getBlocks();
        if(blockList.size() == 0)
        {
            Toast.makeText(this,"No text from image",Toast.LENGTH_SHORT).show();
        }
        else
        {
            for(FirebaseVisionText.Block block : firebaseVisionText.getBlocks())
            {
                String text = block.getText();
                textView.setText(text);
            }
        }
    }
}