package com.evil.roundlayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.evil.rlayout.RoundImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	private RoundImageView mRivGif1;
	private RoundImageView mRivGif2;
	private RoundImageView mRivGif3;
	private RoundImageView mRivGif4;
	private ImageView mRivGif5;
	private ImageView mRivGif6;
	private ImageView mRivGif7;
	private Button mBtColor;
	private RelativeLayout mLayout;
	
	/**
	 * @throws
	 * @Title:main
	 * @Description:生成随机颜色
	 * @param:@param args
	 * @return: void
	 */
	public static String getRandomColor()
	{
		//红色
		String red;
		//绿色
		String green;
		//蓝色
		String blue;
		//生成随机对象
		Random random = new Random();
		//生成红色颜色代码
		red = Integer.toHexString(random.nextInt(256)).toUpperCase();
		//生成绿色颜色代码
		green = Integer.toHexString(random.nextInt(256)).toUpperCase();
		//生成蓝色颜色代码
		blue = Integer.toHexString(random.nextInt(256)).toUpperCase();
		
		//判断红色代码的位数
		red = red.length() == 1 ? "0" + red : red;
		//判断绿色代码的位数
		green = green.length() == 1 ? "0" + green : green;
		//判断蓝色代码的位数
		blue = blue.length() == 1 ? "0" + blue : blue;
		//生成十六进制颜色值
		return "#" + red + green + blue;
	}
	
	public static int getRandomRgbColor()
	{
		//生成随机对象
		Random random = new Random();
		return Color.rgb(random.nextInt(256),random.nextInt(256),random.nextInt(256));
	}
	
	public static int getRandomArgbColor()
	{
		//生成随机对象
		Random random = new Random();
		return Color.argb(random.nextInt(256),random.nextInt(256),random.nextInt(256),random.nextInt(256));
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		DensityUtils.initDensity(getApplication());
		DensityUtils.adaptActivity(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	
	private void initView() {
		//actionBar的设置(使用自定义的设置)
		setTitle("一个圆形的容器Layout");
		
		mRivGif1 = findViewById(R.id.riv_gif1);
		mRivGif2 = findViewById(R.id.riv_gif2);
		mRivGif3 = findViewById(R.id.riv_gif3);
		mRivGif4 = findViewById(R.id.riv_gif4);
		mRivGif5 = findViewById(R.id.riv_gif5);
		mRivGif6 = findViewById(R.id.riv_gif6);
		mRivGif7 = findViewById(R.id.riv_gif7);
		mBtColor = findViewById(R.id.bt_color);
		mLayout = findViewById(R.id.rl_container);
		Glide.with(this).asGif().load(R.mipmap.g01).into(mRivGif1);
		Glide.with(this).asGif().load(R.mipmap.g01).into(mRivGif2);
		Glide.with(this).asGif().load(R.mipmap.g01).into(mRivGif3);
		Glide.with(this).asGif().load(R.mipmap.g01).into(mRivGif4);
		Glide.with(this).asGif().load(R.mipmap.g01).into(mRivGif5);
		Glide.with(this).asGif().load(R.mipmap.g01).into(mRivGif6);
		Glide.with(this).asGif().load(R.mipmap.g01).into(mRivGif7);
		mBtColor.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.bt_color:
				mLayout.setBackgroundColor(getRandomRgbColor());
				break;
		}
	}
}
