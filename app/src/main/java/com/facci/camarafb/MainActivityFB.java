package com.facci.camarafb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivityFB extends AppCompatActivity {

    Button boton;
    Button boton2;
    ImageView image;
    Bitmap bitma;
    Intent cosita;
    File foto;
    private final String rfotos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/AlmbumFB/";
    private File file = new File(rfotos);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        file.mkdirs();
        setContentView(R.layout.activity_main_activity_fb);
        boton = (Button)findViewById(R.id.fotoBT);
        boton2 = (Button)findViewById(R.id.almacenarBT);
        image=(ImageView)findViewById(R.id.imagenCapturadaIV);

    }

    public void click(View v){
        int identificador = v.getId();
        switch (identificador){
            case R.id.fotoBT:
                    cosita = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    String file = rfotos + getCode() + ".jpg";
                    foto = new File( file );
                    try {
                        foto.createNewFile();
                    } catch (IOException ex) {
                        Toast.makeText(this,"cojo cojito cojeando "+ex,Toast.LENGTH_LONG).show();
                    }
                    startActivityForResult(cosita,0);

                break;
            case R.id.almacenarBT:
                Uri uri = Uri.fromFile( foto );
                cosita.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                Toast.makeText(this,"cojo cojito",Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requesCode, int requesResult, Intent data){
        Toast.makeText(this,"entra a onActibityResult",Toast.LENGTH_LONG).show();
        super.onActivityResult(requesCode,requesResult,data);
        if(requesResult == Activity.RESULT_OK){
            Toast.makeText(this,"puto el que lo lea",Toast.LENGTH_LONG).show();
            Bundle extra = data.getExtras();
            bitma = (Bitmap)extra.get("data");
            image.setImageBitmap(bitma);

        }
    }

    @SuppressLint("SimpleDateFormat")
    private String getCode()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFormat.format(new Date() );
        String photoCode = "pic_" + date;
        return photoCode;
    }
}
