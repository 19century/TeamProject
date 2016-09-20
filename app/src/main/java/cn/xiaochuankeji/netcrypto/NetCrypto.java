package cn.xiaochuankeji.netcrypto;

import org.json.JSONObject;

/**
 * Created by XCQ on 16/9/8.
 */
public class NetCrypto {
    static {
        System.loadLibrary("net_crypto");
        native_init();
    }

    /**
     * 获取最终的完整网址, 所有的网址会自动包含 sign 参数
     *
     * @param url
     * @param postJson
     * @return
     */
    public static String getRequestUrl(String url, JSONObject postJson) {
        String sign = generateSign(postJson.toString());
        return url + "?sign=" + sign;
    }

    public static native String generateSign(String paramString);

    private static native void native_init();
}
