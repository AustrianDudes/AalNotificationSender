package com.ilogs.projects.aalNotificationSender;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.ColorRes;
import android.util.StateSet;

/**
 * Major modification history
 * By Pierre Schaschl On 10.07.2017 - Initial creation
 *
 * Adds the button style in the code behind
 * TODO: Superclass for this and WaalterBackgroundDrawable
 */
public class ButtonBackgroundDrawable extends StateListDrawable {

    private boolean mIsBottomFlat;
    private final int mBackgroundColorPressed = R.color.waalter_grey;

    /**
     * Initializes a specific background helper
     * @param c Current context
     * @param colorResId Background color that should be applied
     */
    public ButtonBackgroundDrawable(Context c, @ColorRes int colorResId) {
        this(c, colorResId, false);
    }

    /**
     * Initializes a specific background helper
     * @param c Current context
     * @param colorResId Background color that should be applied
     * @param isBottomFlat If this is set to true, the bottom corners will not be rounded
     */
    private ButtonBackgroundDrawable(Context c, @ColorRes int colorResId, boolean isBottomFlat) {
        mIsBottomFlat = isBottomFlat;
        addState(new int[] {android.R.attr.state_pressed}, getStateDrawable(c, true, colorResId));
        addState(new int[] {-android.R.attr.state_enabled}, getStateDrawable(c, true, colorResId));
        addState(StateSet.WILD_CARD, getStateDrawable(c, false, colorResId));
    }

    /**
     * Gets the drawable for a certain state
     * @param c Context
     * @param isPressed Pressed or not state
     * @param colorResId Color for drawable
     * @return Non-null drawable
     */
    private Drawable getStateDrawable(Context c, boolean isPressed, @ColorRes int colorResId) {
        Resources r = c.getResources();
        int backgroundColorResId = isPressed ? mBackgroundColorPressed : colorResId;

        GradientDrawable buttonGradient = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{ changeColorLuminance(c, backgroundColorResId, 0.25f), r.getColor(backgroundColorResId)});
        buttonGradient.setShape(GradientDrawable.RECTANGLE);

        int cornerRadius = (int) c.getResources().getDimension(R.dimen.cornerRadius);
        if(!mIsBottomFlat) {
            buttonGradient.setCornerRadius(cornerRadius);
        }
        else {
            buttonGradient.setCornerRadii(new float[] {cornerRadius,cornerRadius,cornerRadius,cornerRadius,0,0,0,0});
        }

        buttonGradient.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        Drawable[] drawArray = {buttonGradient};
        LayerDrawable layerDrawable = new LayerDrawable(drawArray);
        return layerDrawable;
    }

    /**
     * Changes the luminance of a given resource color
     * @param context Current context
     * @param colorResId Color resource id
     * @param lum [-1,255] Wenn [-1,0[ dann dunkler, wenn ]0,255] dann heller
     * @return Changed lum
     */
    private int changeColorLuminance(Context context, @ColorRes int colorResId, float lum) {
        int color = context.getResources().getColor(colorResId);
        color = color & 0xFFFFFF;

        int colorB = color & 0x0000FF;
        colorB = Math.min(Math.max(0, colorB + (int)(colorB * lum)), 0xFF);
        int colorG = (color & 0x00FF00) >>> 8;
        colorG = Math.min(Math.max(0, colorG + (int)(colorG * lum)), 0xFF);
        int colorR = (color & 0xFF0000) >>> 16;
        colorR = Math.min(Math.max(0, colorR + (int)(colorR * lum)), 0xFF);

        return Color.rgb(colorR, colorG, colorB);
    }

    @Override
    protected boolean onStateChange(int[] stateSet) {
        return super.onStateChange(stateSet);
    }

}
