package com.betbtc.app.ui;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * @author chenyan@huobi.com
 * @date 2019/8/21 18:07
 * @desp
 */
public class WebSocketManager {


    private static volatile WebSocketManager instance;
    private OkHttpClient mOkHttpClient;
    private EchoWebSocketListener socketListener;
    private WebSocket mWebSocket;
    private OnWebSocketListener mWebSocketListener;
    private boolean isSockeOpen = false;

    public static WebSocketManager getInstance() {
        if(instance == null) {
            synchronized (WebSocketManager.class) {
                if(instance == null) {
                    instance = new WebSocketManager();
                }
            }
        }
        return instance;
    }

    public WebSocketManager() {
        mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        socketListener = new EchoWebSocketListener();
    }


    /**
     * 连接websocket.
     */
    public void connectSocket(OnWebSocketListener onWebSocketListener) {
        if(isSockeOpen) return;

        if(mOkHttpClient != null) {
            mWebSocketListener = onWebSocketListener;
            if(mWebSocket != null) {
                mWebSocket.cancel();
            }
            Request request = new Request.Builder()
                    .url("wss://api.huobi.pro/ws")
                    .build();

            mOkHttpClient.newWebSocket(request, socketListener);
        }
    }

    /**
     * websocket是否连接成功
     * @return
     */
    public boolean isSockeOpen() {
        return isSockeOpen;
    }

    /**
     * 关闭连接
     */
    public void disconnectSocket() {
        if(mWebSocket != null) {
            mWebSocket.cancel();
        }
        if(mWebSocketListener != null) {
            mWebSocketListener = null;
        }
    }

    /**
     * 订阅数据
     */
    public void subMarket1Day(String symbol,String period) {
        if(mWebSocket != null) {
            String sub = "market." + symbol.toLowerCase() + ".kline."+period;
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("sub", sub);
                jsonObject.put("id", "id1");
                mWebSocket.send(jsonObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private final class EchoWebSocketListener extends WebSocketListener {

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            mWebSocket = webSocket;
            isSockeOpen = true;
            if(mWebSocketListener != null) {
                mWebSocketListener.onOpen();
            }
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            super.onMessage(webSocket, bytes);
            try {
                GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(bytes.toByteArray()));
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] buffer =new  byte[1024];
                int len = -1;
                while ((len = gzipInputStream.read(buffer))!=-1) {
                    outStream.write(buffer, 0, len);
                }
                byte[] data = outStream.toByteArray();
                outStream.close();
                gzipInputStream.close();

                String jsonStr = new String(data);
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    if(jsonObject.has("ping")) {
                        jsonObject.put("pong", jsonObject.getInt("ping"));
                        webSocket.send(jsonObject.toString());
                    } else {
                        if(jsonObject.has("ch") && jsonObject.has("tick")) {
                            if (mWebSocketListener != null) {
                                jsonObject.put("symbol","btcusdt");
                                mWebSocketListener.onMessage(jsonObject.toString());
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            super.onMessage(webSocket, text);
            if(mWebSocketListener != null) {
                mWebSocketListener.onMessage(text);
            }
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            super.onClosed(webSocket, code, reason);
            isSockeOpen = false;
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            super.onClosing(webSocket, code, reason);
            isSockeOpen = false;
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            super.onFailure(webSocket, t, response);
            isSockeOpen = false;
        }
    }

    public interface OnWebSocketListener {
        void onOpen();

        void onMessage(String json);
    }

}
