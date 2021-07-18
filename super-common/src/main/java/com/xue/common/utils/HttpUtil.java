package com.xue.common.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class HttpUtil {

    private PoolingHttpClientConnectionManager pm;

    public HttpUtil() {
        pm = new PoolingHttpClientConnectionManager();
        pm.setMaxTotal(100);
        pm.setDefaultMaxPerRoute(10);

    }

    /**
     * 下载页面数据
     *
     * @param url
     * @return
     */
    public String doGetHtml(String url) {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(pm).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:62.0) Gecko/20100101 Firefox/62.0");

//        Header header1 = new BasicHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:62.0) Gecko/20100101 Firefox/62.0");
//        Header header2 = new BasicHeader("cookie","unpl=V2_ZzNtbUteQkAnD0Zce0laV2IDRglLVUdHcQ1OBywRVFBnABsJclRCFnUUR1NnGFoUZgIZXkZcRhRFCEFkeR1cDWMGG1VAZ3Mldgh2VUsZWwZnBhBZR1dGFXQIQ1x4H1sHbwsibUVXSiV1C0NQeRtcDFdYRzNFBBcQdgEWBy4ZWAcwMxNtQ2dCJSdmnuLNwfm0e9e40pff1Fh1D0VUfhtYAGcGElxCUksWcw9EXHMpXTVk; shshshfpa=04207645-6f35-310f-170e-22da7e33b8a9-1622996741; __jdu=162143541410149276399; shshshfpb=x1EU54a2sPi4%20hFnkkZa4ng%3D%3D; __jdv=122270672|baidu|-|organic|not set|1623850834480; PCSYCityID=CN_440000_440100_0; areaId=19; user-key=ae24cb0f-3ab9-40c1-aa17-d6ce2eca6ede; ipLoc-djd=19-1601-50258-51885; pinId=TFhIn3nWlE56_rz_RqAY9Q; pin=%E5%93%88%E5%8D%A12046; unick=%E5%93%88%E5%8D%A12046; ceshi3.com=201; _tp=3RKu72sTR%2BYA9%2Fol9MGS5vGGpPgOXffch8PFyldIBbA%3D; _pst=%E5%93%88%E5%8D%A12046; __jda=122270672.162143541410149276399.1621435414.1624726012.1624727832.13; __jdc=122270672; shshshfp=5ef8fb2244d98619f9dfe5c4d99a610f; wlfstk_smdl=fbxjsd7gc4aa7unt6tapqck30zb0d9vt; TrackID=13BDCTXRjoYJDGsKAxpW36t5jkQoIonnbjFEK47LEWU7WTX6iYKYi4NhvgjJNInqr3iO4EJpFAFeXIJ9xYGNwrnrID1-Aa5KfCts_dPyXVR0; thor=AB39148372F819332D76380BD49C60A2C065CC1226C7475F0C3945801F874A9609C23481E62873541CB6B4443A1589882F657E76C5480B316853274C4D45D2E8F60A88AA4852011E0BD26E327AB18D7325CB5BE4F0848EF973A7C06B33DE1548CA8F0CD2DC19A74C49FF8C9894ADE1FE04999D71C1405655CFD70E98C96A0823; shshshsID=000de13314eea0684398db9c244dcf9c_12_1624729530471; __jdb=122270672.40.162143541410149276399|13.1624727832; 3AB9D23F7A4B3C9B=3XPV33F7IIRE4HB4T6CKSYWE6JUQLFLRZDLVRMU6M62M4TYBM54LVYWWZOQLNOS7GIH3DHQSKPUIVJ3AVHR2UQTHYQ");
//        Header[] hs = {header1,header2};
//        httpGet.setHeaders(hs);
        httpGet.setConfig(getRequestConfig());
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                if (response.getEntity() != null) {
                    return EntityUtils.toString(response.getEntity(), "utf8");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 下载图片
     *
     * @param url
     * @return
     */
    public String doGetImage(String url) {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(pm).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:62.0) Gecko/20100101 Firefox/62.0");
//        Header header1 = new BasicHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:62.0) Gecko/20100101 Firefox/62.0");
//        Header header2 = new BasicHeader("cookie","unpl=V2_ZzNtbUteQkAnD0Zce0laV2IDRglLVUdHcQ1OBywRVFBnABsJclRCFnUUR1NnGFoUZgIZXkZcRhRFCEFkeR1cDWMGG1VAZ3Mldgh2VUsZWwZnBhBZR1dGFXQIQ1x4H1sHbwsibUVXSiV1C0NQeRtcDFdYRzNFBBcQdgEWBy4ZWAcwMxNtQ2dCJSdmnuLNwfm0e9e40pff1Fh1D0VUfhtYAGcGElxCUksWcw9EXHMpXTVk; shshshfpa=04207645-6f35-310f-170e-22da7e33b8a9-1622996741; __jdu=162143541410149276399; shshshfpb=x1EU54a2sPi4%20hFnkkZa4ng%3D%3D; __jdv=122270672|baidu|-|organic|not set|1623850834480; PCSYCityID=CN_440000_440100_0; areaId=19; user-key=ae24cb0f-3ab9-40c1-aa17-d6ce2eca6ede; ipLoc-djd=19-1601-50258-51885; pinId=TFhIn3nWlE56_rz_RqAY9Q; pin=%E5%93%88%E5%8D%A12046; unick=%E5%93%88%E5%8D%A12046; ceshi3.com=201; _tp=3RKu72sTR%2BYA9%2Fol9MGS5vGGpPgOXffch8PFyldIBbA%3D; _pst=%E5%93%88%E5%8D%A12046; __jda=122270672.162143541410149276399.1621435414.1624726012.1624727832.13; __jdc=122270672; shshshfp=5ef8fb2244d98619f9dfe5c4d99a610f; wlfstk_smdl=fbxjsd7gc4aa7unt6tapqck30zb0d9vt; TrackID=13BDCTXRjoYJDGsKAxpW36t5jkQoIonnbjFEK47LEWU7WTX6iYKYi4NhvgjJNInqr3iO4EJpFAFeXIJ9xYGNwrnrID1-Aa5KfCts_dPyXVR0; thor=AB39148372F819332D76380BD49C60A2C065CC1226C7475F0C3945801F874A9609C23481E62873541CB6B4443A1589882F657E76C5480B316853274C4D45D2E8F60A88AA4852011E0BD26E327AB18D7325CB5BE4F0848EF973A7C06B33DE1548CA8F0CD2DC19A74C49FF8C9894ADE1FE04999D71C1405655CFD70E98C96A0823; shshshsID=000de13314eea0684398db9c244dcf9c_12_1624729530471; __jdb=122270672.40.162143541410149276399|13.1624727832; 3AB9D23F7A4B3C9B=3XPV33F7IIRE4HB4T6CKSYWE6JUQLFLRZDLVRMU6M62M4TYBM54LVYWWZOQLNOS7GIH3DHQSKPUIVJ3AVHR2UQTHYQ");
//        Header[] hs = {header1,header2};
//        httpGet.setHeaders(hs);
        httpGet.setConfig(getRequestConfig());
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                if (response.getEntity() != null) {
                    String extName = url.substring(url.lastIndexOf("."));
                    String picName = UUID.randomUUID().toString() + extName;
                    OutputStream fileOutputStream = new FileOutputStream(new File("E:\\dev\\images\\" + picName + extName));
                    response.getEntity().writeTo(fileOutputStream);
                    return picName;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private RequestConfig getRequestConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(2000)
                .setConnectionRequestTimeout(3000)
                .setSocketTimeout(10000)
                .build();
    }

//    public static void main(String[] args) {
//        HttpUtil httpUtil = new HttpUtil();
//        String s = httpUtil.doGetHtml("https://p.3.cn/prices/mgets?skuIds=J_10021773961064");
//        System.out.println(s);
//
//    }
}
