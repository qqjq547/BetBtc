package com.betbtc.app.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.betbtc.app.http.exception.ApiErrorOperator;
import com.betbtc.app.http.response.HttpResponse;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by codeest on 2016/8/3.
 */
public class RxUtil {

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> schedulersTransformer() {    //compose简化线程
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T extends HttpResponse> ObservableTransformer<T, T> exceptionIoTransformer() {
        return upstream ->
                upstream.lift(new ApiErrorOperator<T>())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
    }
    public static ObservableTransformer<ResponseBody, Bitmap> transformerBitmap() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map((Function<ResponseBody, Bitmap>) responseBody -> {
                            Bitmap bitmap=null;
                            try {
                                bitmap= BitmapFactory.decodeStream(responseBody.byteStream());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return bitmap;
                        });
    }
    
    public static Observable<Long> countdown(int time) {
        if (time < 0) time = 0;
        final int countTime = time;
        return Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .map((Function<Long, Long>) integer -> countTime - integer.longValue())
                .take(countTime + 1);

    }

}
