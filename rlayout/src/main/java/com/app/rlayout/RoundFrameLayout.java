/*
 * Copyright 2018 GcsSloop
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Last modified 2018-04-13 23:18:56
 *
 * GitHub: https://github.com/GcsSloop
 * WeiBo: http://weibo.com/GcsSloop
 * WebSite: http://www.gcssloop.com
 */

package com.app.rlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.app.rlayout.helper.RoundAttrs;
import com.app.rlayout.helper.RoundHelper;


/**
 * 作用：圆角相对布局
 * 作者：GcsSloop
 */
public class RoundFrameLayout extends FrameLayout implements RoundAttrs {
    RoundHelper mRCHelper;

    public RoundFrameLayout(@NonNull Context context) {
        super(context);
        mRCHelper = new RoundHelper();
        mRCHelper.initAttrs(context, null);
    }

    public RoundFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        mRCHelper = new RoundHelper();
        mRCHelper.initAttrs(context, attrs);
    }

    public RoundFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs,
            int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        mRCHelper = new RoundHelper();
        mRCHelper.initAttrs(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RoundFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs,
            int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        mRCHelper = new RoundHelper();
        mRCHelper.initAttrs(context, attrs);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRCHelper.onSizeChanged(this, w, h);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(mRCHelper.mLayer, null, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
        mRCHelper.openHardware(this);
        mRCHelper.onClipDraw(canvas,true);
        canvas.restore();
    }

    @Override
    public void draw(Canvas canvas) {
        mRCHelper.refreshRegion(this);
        if (mRCHelper.mClipBackground) {
            canvas.save();
            canvas.clipPath(mRCHelper.mClipPath);
            super.draw(canvas);
            canvas.restore();
        } else {
            super.draw(canvas);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //        int action = ev.getAction();
        //        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_UP) {
        //            refreshDrawableState();
        //        } else if (action == MotionEvent.ACTION_CANCEL) {
        //            setPressed(false);
        //            refreshDrawableState();
        //        }
        //        if (!mRCHelper.mAreaRegion.contains((int) ev.getX(), (int) ev.getY())) {
        //            setPressed(false);
        //            refreshDrawableState();
        //            return false;
        //        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void refreshDrawableState() {
        super.refreshDrawableState();
        mRCHelper.drawableStateChanged(this);
    }
    //--- 公开接口 ----------------------------------------------------------------------------------

    public void setClipBackground(boolean clipBackground) {
        mRCHelper.mClipBackground = clipBackground;
        invalidate();
    }

    public void setRoundAsCircle(boolean roundAsCircle) {
        mRCHelper.mRoundAsCircle = roundAsCircle;
        invalidate();
    }

    public void setRadius(int radius) {
        for (int i = 0; i < mRCHelper.radii.length; i++) {
            mRCHelper.radii[i] = radius;
        }
        invalidate();
    }

    public void setTopLeftRadius(int topLeftRadius) {
        mRCHelper.radii[0] = topLeftRadius;
        mRCHelper.radii[1] = topLeftRadius;
        invalidate();
    }

    public void setTopRightRadius(int topRightRadius) {
        mRCHelper.radii[2] = topRightRadius;
        mRCHelper.radii[3] = topRightRadius;
        invalidate();
    }

    public void setBottomLeftRadius(int bottomLeftRadius) {
        mRCHelper.radii[4] = bottomLeftRadius;
        mRCHelper.radii[5] = bottomLeftRadius;
        invalidate();
    }

    public void setBottomRightRadius(int bottomRightRadius) {
        mRCHelper.radii[6] = bottomRightRadius;
        mRCHelper.radii[7] = bottomRightRadius;
        invalidate();
    }

    public void setStrokeWidth(int strokeWidth) {
        mRCHelper.mStrokeWidth = strokeWidth;
        invalidate();
    }

    public void setStrokeColor(int strokeColor) {
        mRCHelper.mStrokeColor = strokeColor;
        invalidate();
    }

    public boolean isClipBackground() {
        return mRCHelper.mClipBackground;
    }

    public boolean isRoundAsCircle() {
        return mRCHelper.mRoundAsCircle;
    }

    public float getTopLeftRadius() {
        return mRCHelper.radii[0];
    }

    public float getTopRightRadius() {
        return mRCHelper.radii[2];
    }

    public float getBottomLeftRadius() {
        return mRCHelper.radii[4];
    }

    public float getBottomRightRadius() {
        return mRCHelper.radii[6];
    }

    public int getStrokeWidth() {
        return mRCHelper.mStrokeWidth;
    }

    public int getStrokeColor() {
        return mRCHelper.mStrokeColor;
    }


    //--- Selector 支持 ----------------------------------------------------------------------------

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        mRCHelper.drawableStateChanged(this);
    }
    //
    //    @Override
    //    public void setChecked(boolean checked) {
    //        if (mRCHelper.mChecked != checked) {
    //            mRCHelper.mChecked = checked;
    //            refreshDrawableState();
    //            if (mRCHelper.mOnCheckedChangeListener != null) {
    //                mRCHelper.mOnCheckedChangeListener.onCheckedChanged(this, mRCHelper.mChecked);
    //            }
    //        }
    //    }

    //    @Override
    //    public boolean isChecked() {
    //        return mRCHelper.mChecked;
    //    }
    //
    //    @Override
    //    public void toggle() {
    //        setChecked(!mRCHelper.mChecked);
    //    }
    //
    //    public void setOnCheckedChangeListener(RoundHelper.OnCheckedChangeListener listener) {
    //        mRCHelper.mOnCheckedChangeListener = listener;
    //    }
}
