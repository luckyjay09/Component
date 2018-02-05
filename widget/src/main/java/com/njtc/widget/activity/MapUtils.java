package com.njtc.widget.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Jay on 2018/2/5.
 */

public class MapUtils {
    private static final String TAG = "MapUtils";

    public static final int MAP_BAIDU = 1;
    public static final int MAP_GAODE = 2;
    public static final int MAP_TENCENT = 3;

    /**
     * @param type 类型
     * @param lng  经度
     * @param lat  纬度
     */
    public static void startToNavagation(Context context, int type, double lng, double lat) throws Exception{
        Intent intent = new Intent();
        String uri = "";
        if (type == MAP_BAIDU) {
            double[] doubles = GPSUtils.gcj02_To_Bd09(lat, lng);
            lat = GPSUtils.retain6(doubles[0]);
            lng = GPSUtils.retain6(doubles[1]);
            uri = "baidumap://map/direction?destination="
                    + lat + "," + lng
                    + "&mode=driving";
        } else if (type == MAP_GAODE) {
            uri = "androidamap://navi?sourceApplication=app&lat=" + lat
                    + "&lon=" + lng + "&dev=0&style=2";
            uri = "amapuri://route/plan/?dlat=" + lat + "&dlon=" + lng + "&dev=0&t=0";
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setPackage("com.autonavi.minimap");
        }

        intent.setData(Uri.parse(uri));
        context.startActivity(intent);

    }
}
