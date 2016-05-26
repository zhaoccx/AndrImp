package com.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class TouchActivity extends Activity {
	/* ����ImageView���� */
	private ImageView mImageView01;
	/* ������ر�����Ϊ�洢ͼƬ����,λ��ʹ�� */
	private int intWidth, intHeight, intDefaultX, intDefaultY;
	private float mX, mY;
	/* �����洢��Ļ�ķֱ��ʱ��� */
	private int intScreenX, intScreenY;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		/* ȡ����Ļ���� */
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		/* ȡ����Ļ�������� */
		intScreenX = dm.widthPixels;
		intScreenY = dm.heightPixels;

		/* ����ͼƬ�Ŀ��� */
		intWidth = 100;
		intHeight = 100;
		/* ͨ��findViewById����������ImageView���� */
		mImageView01 = (ImageView) findViewById(R.id.myImageView1);
		/* ��ͼƬ��Drawable��ֵ��ImageView������ */
		mImageView01.setImageResource(R.drawable.baby);

		/* ��ʼ����ťλ�þ��� */
		RestoreButton();

		/* �����ImageView����ԭ��ʼλ�� */
		mImageView01.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				RestoreButton();
			}
		});
	}

	/* ���Ǵ����¼� */
	public boolean onTouchEvent(MotionEvent event) {
		/* ȡ����ָ������Ļ��λ�� */
		float x = event.getX();
		float y = event.getY();

		try {
			/* �����¼��Ĵ��� */
			switch (event.getAction()) {
			/* �����Ļ */
			case MotionEvent.ACTION_DOWN:
				picMove(x, y);
				break;
			/* �ƶ�λ�� */
			case MotionEvent.ACTION_MOVE:
				picMove(x, y);
				break;
			/* �뿪��Ļ */
			case MotionEvent.ACTION_UP:
				picMove(x, y);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/* �ƶ�ͼƬ�ķ��� */
	private void picMove(float x, float y) {
		/* Ĭ��΢��ͼƬ��ָ������λ�� */
		mX = x - (intWidth / 2);
		mY = y - (intHeight / 2);

		/* ��ͼƬ������Ļ����ش��� */
		/* ��ֹ��Ļ���ҳ�����Ļ */
		if ((mX + intWidth) > intScreenX) {
			mX = intScreenX - intWidth;
		}
		/* ��ֹ��Ļ���󳬹���Ļ */
		else if (mX < 0) {
			mX = 0;
		}
		/* ��ֹ��Ļ���³�����Ļ */
		else if ((mY + intHeight) > intScreenY) {
			mY = intScreenY - intHeight;
		}
		/* ��ֹ��Ļ���ϳ�����Ļ */
		else if (mY < 0) {
			mY = 0;
		}
		/* ͨ��log ���鿴ͼƬλ�� */
		Log.i("jay", Float.toString(mX) + "," + Float.toString(mY));
		/* ��setLayoutParams���������°���Layout�ϵ�λ�� */
		mImageView01.setLayoutParams(new AbsoluteLayout.LayoutParams(intWidth, intHeight, (int) mX, (int) mY));
	}

	/* ��ԭImageViewλ�õ��¼����� */
	public void RestoreButton() {
		intDefaultX = ((intScreenX - intWidth) / 2);
		intDefaultY = ((intScreenY - intHeight) / 2);
		/* Toast��ԭλ������ */
		mMakeTextToast("(" + Integer.toString(intDefaultX) + "," + Integer.toString(intDefaultY) + ")", true);

		/* ��setLayoutParams���������°���Layout�ϵ�λ�� */
		mImageView01.setLayoutParams(new AbsoluteLayout.LayoutParams(intWidth, intHeight, intDefaultX, intDefaultY));
	}

	/* �Զ���һ������Ϣ�ķ��� */
	public void mMakeTextToast(String str, boolean isLong) {
		if (isLong == true) {
			Toast.makeText(TouchActivity.this, str, Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(TouchActivity.this, str, Toast.LENGTH_SHORT).show();
		}
	}
}