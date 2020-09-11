package com.example.demo.test;


import com.alibaba.fastjson.JSONArray;

import java.util.*;

public class Test2 {
    public static void main(String[] args) {
//        initString();
        subject2();
    }

    private static void subject2() {
        List<Map<String, Object>> listData = initData3();
        List<Map<String, Object>> result = new LinkedList<>();
        Map<String, Object> son = null;
        List<Map<String, Object>> grandsonList = null;
        for (Map<String, Object> objMap : listData) {
            if ("app:menu".equals(objMap.get("pcode"))) {//查出son
                son = new HashMap<>();
                son.putAll(objMap);
                //查找grandson
                grandsonList = recursion((String) son.get("code"), listData);
                if (grandsonList.size() > 0) {
                    son.put("children", grandsonList);
                }
                result.add(son);
            }
        }
        System.out.println(JSONArray.toJSON(result).toString());
    }

    /**
     * @param code     父级code
     * @param listData
     * @return
     */
    private static List<Map<String, Object>>  recursion(String code, List<Map<String, Object>> listData) {
        Map<String, Object> son = null;
        List<Map<String, Object>> grandsonList = null;
        List<Map<String, Object>> sonList = new ArrayList<>();
        for (Map<String, Object> objMap : listData) {
            if (code.equals(objMap.get("pcode"))) {//查出son
                son = new HashMap<>();
                son.putAll(objMap);
                //查找grandson
                grandsonList = recursion((String) son.get("code"), listData);
                if (grandsonList.size() > 0) {
                    son.put("children", grandsonList);
                }
                sonList.add(son);
            }
        }
        return sonList;
    }

    private static void subject1() {
        List<Map<String, Object>> list = initData();
        Map<String, List<String>> existEntity = new LinkedHashMap<>();
        List<Map<String, Object>> resList = new LinkedList<>();
        Map<String, Object> entityObject = null;
        String name = null;
        String value = null;
        List<String> temp = null;
        for (Map<String, Object> entityMap : list) {
            name = (String) entityMap.get("name");
            value = (String) entityMap.get("value");
            temp = existEntity.get(name);
            if (temp == null) {
                temp = new ArrayList<>();
                existEntity.put(name, temp);
            }
            temp.add(value);
        }

        for (String entity : existEntity.keySet()) {
            entityObject = new HashMap<>();
            entityObject.put("name", entity);
            entityObject.put("value", existEntity.get(entity));
            resList.add(entityObject);
        }
        System.out.println(resList.toString());
    }

    private static void initString() {
        for (int i = 2; i < 120 - 68 + 2; i++) {
            System.out.println("Map<String,String> map" + i + "= new HashMap<>();");
            System.out.println("map" + i + ".put(\"code\",\"\");");
            System.out.println("map" + i + ".put(\"pcode\",\"\");");
            System.out.println("map" + i + ".put(\"pcodes\",\"\");");
            System.out.println("map" + i + ".put(\"name\",\"\");");
            System.out.println("map" + i + ".put(\"url\",\"1\");");
            System.out.println("map" + i + ".put(\"levels\",\"1\");");
            System.out.println("map" + i + ".put(\"num\",\"1\");");
            System.out.println("map" + i + ".put(\"ismenu\",\"1\");");
            System.out.println("map" + i + ".put(\"status\",\"1\");");
            System.out.println("list.add(map" + i + ");");
        }
    }

    public static List<Map<String, Object>> initData() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap();
        map1.put("name", "三元里营销服务中心");
        map1.put("value", "云翠片区");
        list.add(map1);
        Map<String, Object> map2 = new HashMap();
        map2.put("name", "三元里营销服务中心");
        map2.put("value", "云苑片区");
        list.add(map2);
        Map<String, Object> map3 = new HashMap();
        map3.put("name", "三元里营销服务中心");
        map3.put("value", "北站路片区");
        list.add(map3);
        Map<String, Object> map4 = new HashMap();
        map4.put("name", "三元里营销服务中心");
        map4.put("value", "大金钟路片区");
        list.add(map4);
        Map<String, Object> map5 = new HashMap();
        map5.put("name", "三元里营销服务中心");
        map5.put("value", "景泰片区");
        list.add(map5);
        Map<String, Object> map6 = new HashMap();
        map6.put("name", "三元里营销服务中心");
        map6.put("value", "桂花岗片区");
        list.add(map6);
        Map<String, Object> map7 = new HashMap();
        map7.put("name", "三元里营销服务中心");
        map7.put("value", "民航片区");
        list.add(map7);
        Map<String, Object> map8 = new HashMap();
        map8.put("name", "三元里营销服务中心");
        map8.put("value", "走马岗片区");
        list.add(map8);
        Map<String, Object> map9 = new HashMap();
        map9.put("name", "东圃营销服务中心");
        map9.put("value", "中海康城周边");
        list.add(map9);
        Map<String, Object> map10 = new HashMap();
        map10.put("name", "东圃营销服务中心");
        map10.put("value", "兰亭盛荟周边");
        list.add(map10);
        Map<String, Object> map11 = new HashMap();
        map11.put("name", "东圃营销服务中心");
        map11.put("value", "城市假日园周边");
        list.add(map11);
        Map<String, Object> map12 = new HashMap();
        map12.put("name", "东圃营销服务中心");
        map12.put("value", "天力居周边");
        list.add(map12);
        Map<String, Object> map13 = new HashMap();
        map13.put("name", "东圃营销服务中心");
        map13.put("value", "天宸原著周边");
        list.add(map13);
        Map<String, Object> map14 = new HashMap();
        map14.put("name", "东圃营销服务中心");
        map14.put("value", "旭景家园周边");
        list.add(map14);
        Map<String, Object> map15 = new HashMap();
        map15.put("name", "东圃营销服务中心");
        map15.put("value", "时尚名苑周边");
        list.add(map15);
        Map<String, Object> map16 = new HashMap();
        map16.put("name", "东圃营销服务中心");
        map16.put("value", "汇友苑周边");
        list.add(map16);
        Map<String, Object> map17 = new HashMap();
        map17.put("name", "东圃营销服务中心");
        map17.put("value", "盈彩美居周边");
        list.add(map17);
        Map<String, Object> map18 = new HashMap();
        map18.put("name", "东圃营销服务中心");
        map18.put("value", "羊城花园周边");
        list.add(map18);
        Map<String, Object> map19 = new HashMap();
        map19.put("name", "东圃营销服务中心");
        map19.put("value", "美林湖畔周边");
        list.add(map19);
        Map<String, Object> map20 = new HashMap();
        map20.put("name", "东圃营销服务中心");
        map20.put("value", "黄村西周边");
        list.add(map20);
        Map<String, Object> map21 = new HashMap();
        map21.put("name", "东涌营销服务中心");
        map21.put("value", "东涌社区片区");
        list.add(map21);
        Map<String, Object> map22 = new HashMap();
        map22.put("name", "东涌营销服务中心");
        map22.put("value", "鱼窝头社区片区");
        list.add(map22);
        Map<String, Object> map23 = new HashMap();
        map23.put("name", "人和营销服务中心");
        map23.put("value", "人和社区");
        list.add(map23);
        return list;
    }

    public static List<Map<String, Object>> initData3() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("code", "count");
        map.put("pcode", "app:menu");
        map.put("pcodes", "[0],[app:menu],");
        map.put("name", "经营看数");
        map.put("url", "count/countlist.html");
        map.put("levels", "2");
        map.put("num", "10");
        map.put("ismenu", "1");
        map.put("status", "1");
        list.add(map);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("code", "bussiness_app");
        map2.put("pcode", "count");
        map2.put("pcodes", "[0],[app:menu],[count],");
        map2.put("name", "业务报表");
        map2.put("url", "#");
        map2.put("levels", "3");
        map2.put("num", "1");
        map2.put("ismenu", "0");
        map2.put("status", "1");
        list.add(map2);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("code", "inventory_app");
        map3.put("pcode", "count");
        map3.put("pcodes", "[0],[app:menu],[count],");
        map3.put("name", "存量经营");
        map3.put("url", "#");
        map3.put("levels", "3");
        map3.put("num", "2");
        map3.put("ismenu", "0");
        map3.put("status", "1");
        list.add(map3);
        Map<String, Object> map4 = new HashMap<>();
        map4.put("code", "quality_app");
        map4.put("pcode", "count");
        map4.put("pcodes", "[0],[app:menu],[count],");
        map4.put("name", "质量报表");
        map4.put("url", "#");
        map4.put("levels", "1");
        map4.put("num", "1");
        map4.put("ismenu", "1");
        map4.put("status", "1");
        list.add(map4);
        Map<String, Object> map5 = new HashMap<>();
        map5.put("code", "integral_app");
        map5.put("pcode", "count");
        map5.put("pcodes", "[0],[app:menu],[count],");
        map5.put("name", "积分报表");
        map5.put("url", "#");
        map5.put("levels", "1");
        map5.put("num", "1");
        map5.put("ismenu", "1");
        map5.put("status", "1");
        list.add(map5);
        Map<String, Object> map6 = new HashMap<>();
        map6.put("code", "capacity_app");
        map6.put("pcode", "count");
        map6.put("pcodes", "[0],[app:menu],[count],");
        map6.put("name", "渠道产能");
        map6.put("url", "#");
        map6.put("levels", "1");
        map6.put("num", "1");
        map6.put("ismenu", "1");
        map6.put("status", "1");
        list.add(map6);
        Map<String, Object> map7 = new HashMap<>();
        map7.put("code", "commission_app");
        map7.put("pcode", "count");
        map7.put("pcodes", "[0],[app:menu],[count],");
        map7.put("name", "佣金报表");
        map7.put("url", "1");
        map7.put("levels", "1");
        map7.put("num", "1");
        map7.put("ismenu", "1");
        map7.put("status", "1");
        list.add(map7);
        Map<String, Object> map8 = new HashMap<>();
        map8.put("code", "tradingArea_app");
        map8.put("pcode", "count");
        map8.put("pcodes", "[0],[app:menu],[count],");
        map8.put("name", "商圈管理");
        map8.put("url", "1");
        map8.put("levels", "1");
        map8.put("num", "1");
        map8.put("ismenu", "1");
        map8.put("status", "1");
        list.add(map8);
        Map<String, Object> map9 = new HashMap<>();
        map9.put("code", "channelPersonalization_app");
        map9.put("pcode", "count");
        map9.put("pcodes", "[0],[app:menu],[count],");
        map9.put("name", "渠道个性化报表");
        map9.put("url", "1");
        map9.put("levels", "1");
        map9.put("num", "1");
        map9.put("ismenu", "1");
        map9.put("status", "1");
        list.add(map9);
        Map<String, Object> map10 = new HashMap<>();
        map10.put("code", "fullFuse_app");
        map10.put("pcode", "bussiness_app");
        map10.put("pcodes", "[0],[app:menu],[count],[bussiness_app],");
        map10.put("name", "全融合发展量");
        map10.put("url", "1");
        map10.put("levels", "1");
        map10.put("num", "1");
        map10.put("ismenu", "1");
        map10.put("status", "1");
        list.add(map10);
        Map<String, Object> map11 = new HashMap<>();
        map11.put("code", "inventoryManageReport_app");
        map11.put("pcode", "[0],[app:menu],[count],[inventory_app],");
        map11.put("pcodes", "1");
        map11.put("name", "[0],[app:menu],[count],[inventory_app],");
        map11.put("url", "1");
        map11.put("levels", "1");
        map11.put("num", "1");
        map11.put("ismenu", "1");
        map11.put("status", "1");
        list.add(map11);
        Map<String, Object> map12 = new HashMap<>();
        map12.put("code", "qualityReport_app");
        map12.put("pcode", "quality_app");
        map12.put("pcodes", "[0],[app:menu],[count],[quality_app],");
        map12.put("name", "业务质量");
        map12.put("url", "1");
        map12.put("levels", "1");
        map12.put("num", "1");
        map12.put("ismenu", "1");
        map12.put("status", "1");
        list.add(map12);
        Map<String, Object> map13 = new HashMap<>();
        map13.put("code", "integralReport_app");
        map13.put("pcode", "integral_app");
        map13.put("pcodes", "[0],[app:menu],[count],[integral_app],");
        map13.put("name", "积分");
        map13.put("url", "1");
        map13.put("levels", "1");
        map13.put("num", "1");
        map13.put("ismenu", "1");
        map13.put("status", "1");
        list.add(map13);
        Map<String, Object> map14 = new HashMap<>();
        map14.put("code", "capacityReport_app");
        map14.put("pcode", "capacity_app");
        map14.put("pcodes", "[0],[app:menu],[count],[capacity_app],");
        map14.put("name", "渠道产能");
        map14.put("url", "1");
        map14.put("levels", "1");
        map14.put("num", "1");
        map14.put("ismenu", "1");
        map14.put("status", "1");
        list.add(map14);
        Map<String, Object> map15 = new HashMap<>();
        map15.put("code", "commissionReport_app");
        map15.put("pcode", "commission_app");
        map15.put("pcodes", "[0],[app:menu],[count],[commission_app],");
        map15.put("name", "业务佣金");
        map15.put("url", "1");
        map15.put("levels", "1");
        map15.put("num", "1");
        map15.put("ismenu", "1");
        map15.put("status", "1");
        list.add(map15);
        Map<String, Object> map16 = new HashMap<>();
        map16.put("code", "doubleLiftPQ_app");
        map16.put("pcode", "channelPersonalization_app");
        map16.put("pcodes", "[0],[app:menu],[count],[channelPersonalization_app],");
        map16.put("name", "片区双提升报表");
        map16.put("url", "1");
        map16.put("levels", "1");
        map16.put("num", "1");
        map16.put("ismenu", "1");
        map16.put("status", "1");
        list.add(map16);
        Map<String, Object> map17 = new HashMap<>();
        map17.put("code", "mobile_app");
        map17.put("pcode", "bussiness_app");
        map17.put("pcodes", "[0],[app:menu],[count],[bussiness_app],");
        map17.put("name", "移动发展量");
        map17.put("url", "1");
        map17.put("levels", "1");
        map17.put("num", "1");
        map17.put("ismenu", "1");
        map17.put("status", "1");
        list.add(map17);
        Map<String, Object> map18 = new HashMap<>();
        map18.put("code", "businessDevelop_app");
        map18.put("pcode", "channelPersonalization_app");
        map18.put("pcodes", "[0],[app:menu],[count],[channelPersonalization_app],");
        map18.put("name", "片区团队业务发展");
        map18.put("url", "1");
        map18.put("levels", "1");
        map18.put("num", "1");
        map18.put("ismenu", "1");
        map18.put("status", "1");
        list.add(map18);
        Map<String, Object> map19 = new HashMap<>();
        map19.put("code", "monthManager_app");
        map19.put("pcode", "channelPersonalization_app");
        map19.put("pcodes", "[0],[app:menu],[count],[channelPersonalization_app],");
        map19.put("name", "月度运营报告");
        map19.put("url", "1");
        map19.put("levels", "1");
        map19.put("num", "1");
        map19.put("ismenu", "1");
        map19.put("status", "1");
        list.add(map19);
        return list;
    }

}



