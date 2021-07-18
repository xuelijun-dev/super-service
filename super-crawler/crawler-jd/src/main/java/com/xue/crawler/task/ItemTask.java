package com.xue.crawler.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xue.common.utils.HttpUtil;
import com.xue.crawler.pojo.Item;
import com.xue.crawler.service.ItemService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

@Component
public class ItemTask {
    @Autowired
    private HttpUtil httpUtil;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ObjectMapper objectMapper;

    @Scheduled(fixedDelay = 100 * 1000)
    public void itemTask() throws Exception {
        String url = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&wq=%E6%89%8B%E6%9C%BA&pvid=cbecf7aa293746dba7b1a4c56b5ccb6b&s=56&click=0&page=";

        for (int i = 1; i < 10; i = i + 2) {
            String html = httpUtil.doGetHtml(url + i);
            parse(html);
            Thread.sleep(1000);
        }
    }

    private void parse(String html) throws JsonProcessingException {
        //1.解释html获取document
        Document document = Jsoup.parse(html);
        //2.获取spu元素
        Elements spuEles = document.select("div#J_goodsList > ul > li");
        for (Element spuEle : spuEles) {
            String spuId = spuEle.attr("data-spu");
            if (ObjectUtils.isEmpty(spuId)) {
                continue;
            }
            long spu = Long.parseLong(spuId);
            //3.通过spu元素获取sku元素
            Elements skuEles = spuEle.select("li.ps-item");
            for (Element skuEle : skuEles) {
                //4.获取skuId
                String skuId = skuEle.select("img[data-sku]").first().attr("data-sku");
                long sku = Long.parseLong(skuId);
                Item item = new Item();
                item.setSku(sku);
                List<Item> items = itemService.findAll(item);
                if (!CollectionUtils.isEmpty(items)) {
                    continue;
                }
                //5.获取spuUrl
                String skuUrl = skuEle.select("img[data-sku]").first().attr("data-url");
                if (ObjectUtils.isEmpty(skuUrl)) {
                skuUrl = "https://item.jd.com/" + sku + ".html";
                }else{
                    skuUrl = skuUrl.replace("//","https://");
                }
                //6.图片地址
                String picUrl = skuEle.select("img[data-sku]").first().attr("data-lazy-img");
                picUrl = picUrl.replace("/n7/","/n1/").replace("//","http://");
                System.out.println("picUrl#############################"+ picUrl);
                String picName = httpUtil.doGetImage(picUrl);
                String priceUrl = "https://p.3.cn/prices/mgets?skuIds=J_"+skuId;
                //7.获取sku价格
                System.out.println("priceUrl#############################"+ priceUrl);
                String priceJson = httpUtil.doGetHtml(priceUrl);
                double price = objectMapper.readTree(priceJson).get(0).get("p").asDouble();
                System.out.println("price############################"+ price);
                //8.获取spu标题
                String skuDetailHtml= httpUtil.doGetHtml(skuUrl);
                Document skuDetailDocument = Jsoup.parse(skuDetailHtml);
//                String skuTitle = skuDetailDocument.select("div.sku-name").first().text();
                System.out.println("skuTitle1##############"+skuDetailHtml);
                String skuTitle = skuEle.select("a[title]").first().attr("title");
                System.out.println("skuTitle2##############"+skuTitle);
                item = new Item();
                item.setSpu(spu);
                item.setSku(sku);
                item.setTitle(skuTitle);
                item.setPrice(price);
                item.setPic(picUrl);
                item.setUrl(skuUrl);
                item.setCreated(new Date());
                item.setUpdated(new Date());
                itemService.save(item);
            }





        }

    }
}
