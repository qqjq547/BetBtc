package com.betbtc.app.tools;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.core.content.ContextCompat;

public final class ColorUtils {
    /**
     * 通过color资源id获取颜色值
     * @param id color资源id
     * @return
     */
    public static @ColorInt int getColor(@ColorRes int id) {
        return ContextCompat.getColor(AppManager.getsApplication(), id);
    }

    /**
     * 通过color资源获取该颜色对应透明度的颜色值
     * @param id color资源id
     * @param alpha 浮点型透明度 0-1
     * @return
     */
    public static @ColorInt
    int getColor(@ColorRes int id, @FloatRange(from = 0, to = 1) float alpha) {
        return getAlphaColor(ContextCompat.getColor(AppManager.getsApplication(), id), alpha);
    }

    /**
     * 通过color资源获取该颜色对应透明度的颜色值
     * @param id color资源id
     * @param alpha int型透明度 0-255
     * @return
     */
    public static @ColorInt int getColor(@ColorRes int id, @IntRange(from = 0x0, to = 0xFF) int alpha) {
        return getAlphaColor(ContextCompat.getColor(AppManager.getsApplication(), id), alpha);
    }

    public static @ColorInt int getAlphaColor(@ColorInt int color, @FloatRange(from = 0, to = 1) float alpha) {
        return (color & 0x00ffffff) | ((int) (alpha * 255.0f + 0.5f) << 24);
    }

    public static @ColorInt  int getAlphaColor(@ColorInt final int color, @IntRange(from = 0x0, to = 0xFF) int alpha) {
        return (color & 0x00ffffff) | (alpha << 24);
    }
}