package com.example.demo.test.webservice;

import cn.hutool.core.util.XmlUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @Author: zc
 * @Date: 2021/1/4 10:06
 */
public class PsServiceTest {

    public static void main(String[] args) {
        String xmlStr = "";
        JSONObject param = new JSONObject();
        JSONObject root = new JSONObject();
        JSONObject requestdata = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("oa_id", "0");
        data.put("abs_type", "ANN");
        data.put("emplid", "100000416");
        data.put("empl_rcd", "0");
        data.put("bgn_dt", "2021-12-28");
        data.put("end_dt", "2021-12-30");
        data.put("bgn_half", "N");
        data.put("end_half", "N");
        data.put("abs_day", "3");
        requestdata.put("data", data);
        root.put("requestdata", requestdata);
        param.put("root", root);
        xmlStr = xmlHead() + param.toJSONString() + xmlTail();
        System.out.println(xmlStr);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        String psPath = "http://200.4.0.64:8000/";
        String send = HttpRequest.post(psPath + "PSIGW/PeopleSoftServiceListeningConnector/PSFT_HR")
                .header("Content-Type", "text/xml;charset=UTF-8")
                .header("SOAPAction", "GC_OA_ABS_EDIT.v1")
                .body(xmlStr)
                .execute().body();
        System.out.println(send);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Document doc = XmlUtil.parseXml(send);
        NodeList nodeList = doc.getElementsByTagName("GC_INTF_RESPONSE");
        JSONObject returnJson = element(nodeList);
        System.out.println(returnJson.getJSONObject("root").getJSONObject("responsedata").toJSONString());
    }

    public static JSONObject element(NodeList list) {
        JSONObject returnJson = null;
        for (int i = 0; i < list.getLength(); i++) {
            Element element = (Element) list.item(i);
            NodeList childNodes = element.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    //获取节点
                    System.out.print(childNodes.item(j).getNodeName() + ":");
                    //获取节点值
                    System.out.println(childNodes.item(j).getFirstChild().getNodeValue());
                    returnJson = JSON.parseObject(childNodes.item(j).getFirstChild().getNodeValue());
                }
            }

        }
        System.out.println(returnJson.toJSONString());
        return returnJson;
    }

    public static void node(NodeList list) {
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            NodeList childNodes = node.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    System.out.print(childNodes.item(j).getNodeName() + ":");
                    System.out.println(childNodes.item(j).getFirstChild().getNodeValue());
                }
            }
        }
    }

    private static String xmlHead() {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:gc=\"http://xmlns.oracle.com/Enterprise/Tools/schemas/GC_OA_ABS_EDIT_REQ.V1\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <gc:GC_INTF_REQUEST>\n" +
                "         <gc:json_string>";
    }

    private static String xmlTail() {
        return "</gc:json_string>\n" +
                "      </gc:GC_INTF_REQUEST>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }
}
