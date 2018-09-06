package com.evil.roundlayout;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

/**
 * Created by SouShin on 2018/8/20842.
 * 通过修改系统参数来适配android设备
 */

public class DensityUtils {
	public static final float DESIGN_WIDTH = 360f;//设计图宽度
	public static final float DESIGN_HEIGHT = 667f;//设计图高度
	private static float appDensity;
	private static float appScaledDensity;
	private static DisplayMetrics appDisplayMetrics;
	private static int barHeight;
	
	/**
	 * 在Application里初始化一下
	 * @param application
	 */
	public static void initDensity(@NonNull final Application application) {
		//获取application的DisplayMetrics
		appDisplayMetrics = application.getResources().getDisplayMetrics();
		//获取状态栏高度
		barHeight = getStatusBarHeight(application);
		
		if (appDensity == 0) {
			//初始化的时候赋值
			appDensity = appDisplayMetrics.density;
			appScaledDensity = appDisplayMetrics.scaledDensity;
			
			//添加字体变化的监听
			application.registerComponentCallbacks(new ComponentCallbacks() {
				@Override
				public void onConfigurationChanged(Configuration newConfig) {
					//字体改变后,将appScaledDensity重新赋值
					if (newConfig != null && newConfig.fontScale > 0) {
						appScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
					}
				}
				
				@Override
				public void onLowMemory() {
				}
			});
		}
	}
	
	/**
	 * 此方法在BaseActivity中做初始化(如果不封装BaseActivity的话,直接用下面那个方法就好)
	 * 在setContentView()之前设置
	 * @param activity
	 */
	public static void adaptActivity(Context activity) {
		setAppOrientation(activity, false);
	}
	
	/**
	 * 此方法用于在某一个Activity里面更改适配的方向
	 * 在setContentView()之前设置
	 * @param activity
	 * @param isBaseHeight 方向值是否以高度为准
	 */
	public static void adaptActivity(Context activity,boolean isBaseHeight) {
		setAppOrientation(activity, isBaseHeight);
	}
	
	/**
	 * targetDensity
	 * targetScaledDensity
	 * targetDensityDpi
	 * 这三个参数是统一修改过后的值
	 * orientation:方向值是否以高度为准
	 */
	private static void setAppOrientation(@Nullable Context context,boolean isBaseHeight) {
		
		float targetDensity;
		
		if (isBaseHeight) {
			targetDensity = (appDisplayMetrics.heightPixels - barHeight) / DESIGN_HEIGHT;//设计图的高度 单位:dp
		} else {
			targetDensity = appDisplayMetrics.widthPixels / DESIGN_WIDTH;//设计图的宽度 单位:dp
		}
		
		float targetScaledDensity = targetDensity * (appScaledDensity / appDensity);
		int targetDensityDpi = (int) (160 * targetDensity);
		
		/**
		 *
		 * 最后在这里将修改过后的值赋给系统参数
		 * 只修改Activity的density值
		 */
		DisplayMetrics activityDisplayMetrics = context.getResources().getDisplayMetrics();
		activityDisplayMetrics.density = targetDensity;
		activityDisplayMetrics.scaledDensity = targetScaledDensity;
		activityDisplayMetrics.densityDpi = targetDensityDpi;
	}
	
	/**
	 * 获取状态栏高度
	 *
	 * @param context
	 * @return
	 */
	public static int getStatusBarHeight(Context context) {
		int result = 0;
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = context.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}
	
	/**
	 * 取消适配
	 */
	public static void cancelAdaptScreen(Context context) {
		final DisplayMetrics systemDm = Resources.getSystem().getDisplayMetrics();
		if (context != null) {
			DisplayMetrics activityDm = context.getResources().getDisplayMetrics();
			activityDm.density = systemDm.density;
			activityDm.scaledDensity = systemDm.scaledDensity;
			activityDm.densityDpi = systemDm.densityDpi;
		}
	}
}
