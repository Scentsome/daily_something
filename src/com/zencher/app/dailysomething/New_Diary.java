package com.zencher.app.dailysomething;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;

/**
 * Created by kevin on 2015/7/8.
 */
public class New_Diary extends Activity {
    ImageView mImg;
    DisplayMetrics mPhone;
    private final static int CAMERA = 66 ;
    private final static int PHOTO = 99 ;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_content);
        setTitle("New Diary");
        mPhone = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mPhone);
        mImg = (ImageView) findViewById(R.id.iV);
        Button mCamera = (Button) findViewById(R.id.button5);
        Button mPhoto = (Button) findViewById(R.id.button4);
        mCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues value = new ContentValues();
                value.put(MediaStore.Video.Media.MIME_TYPE, "image/jpeg");
                Uri uri= getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, value);
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri.getPath());
                startActivityForResult(intent, CAMERA);
            }
        });
        mPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PHOTO);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        //�ǥ�requestCode�P�_�O�_���}�Ҭ۾��ζ}�Ҭ�ï�өI�s���A�Bdata����null
        if ((requestCode == CAMERA || requestCode == PHOTO ) && data != null) {
            //���o�Ӥ����|uri
            Uri uri = data.getData();
            ContentResolver cr = this.getContentResolver();
            try {
                //Ū���Ӥ��A���A��Bitmap
                BitmapFactory.Options mOptions = new BitmapFactory.Options();
                //Size=2���N��l�Ϥ��Y�p1/2�ASize=4��1/4�A�H������
                mOptions.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri),null,mOptions);
                //�P�_�Ӥ�����V�Ϊ̬����V�A�öi�JScalePic�P�_�Ϥ��O�_�n�i���Y��
                if(bitmap.getWidth()>bitmap.getHeight())ScalePic(bitmap,
                        mPhone.heightPixels);
                else ScalePic(bitmap,mPhone.widthPixels);
            }
            catch (FileNotFoundException e) {
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void ScalePic(Bitmap bitmap,int phone) {
        //�Y���ҹw�]��1
        float mScale = 1 ;
        //�p�G�Ϥ��e�פj�����e�׫h�i���Y��A�_�h�����N�Ϥ���JImageView��
        if(bitmap.getWidth() > phone ) {
            //�P�_�Y����
            mScale = (float)phone/(float)bitmap.getWidth();
            Matrix mMat = new Matrix() ;
            mMat.setRotate(90);
            mMat.setScale(mScale, mScale);
            Bitmap mScaleBitmap = Bitmap.createBitmap(bitmap,
                    0,
                    0,
                    bitmap.getWidth(),
                    bitmap.getHeight(),
                    mMat,
                    false);
            mImg.setImageBitmap(mScaleBitmap);
        }
        else mImg.setImageBitmap(bitmap);
    }
}