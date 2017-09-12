package yhh.tinklabstest.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import yhh.tinklabstest.data.type.BaseType;
import yhh.tinklabstest.data.type.ImageType;
import yhh.tinklabstest.data.type.TextType;

import static yhh.tinklabstest.data.type.BaseType.TYPE_IMAGE;
import static yhh.tinklabstest.data.type.BaseType.TYPE_TEXT;

/**
 * Created by s011208 on 2017/9/12.
 */

public class TestDataSet {
    private TestDataSet() {}

    public static final String FAKE_RAW_DATA = "[\n" +
            "  {\n" +
            "    \"dataType\":\"Text\",\n" +
            "    \"imageUrl\":\"xie_r.jpg\",\n" +
            "    \"text\":\"西門町應該是每個人第一次遊台北都必定會去的行程之一，旅遊書總會形容這個地方是「年青人潮流勝地」，但多年前第一次去的時候感覺真的不太好，簡單來說這就是一個很MK的地方。\",\n" +
            "    \"title\":\"西門紅樓\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"dataType\":\"Image\",\n" +
            "    \"imageUrl\":\"cks.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"dataType\":\"Text\",\n" +
            "    \"imageUrl\":\"ferris_wheel.jpg\",\n" +
            "    \"text\":\"記得還小的時候，常常吵著爸爸跟媽媽要出門玩，加上學校又常常暑假作業都叫我們要拿個存根票。\",\n" +
            "    \"title\":\"新兒童樂園\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"dataType\":\"Text\",\n" +
            "    \"imageUrl\":\"beitou.jpg\",\n" +
            "    \"text\":\"隱身山嵐綠意間的北投麗禧溫泉酒店，得天獨厚地理位置，讓旅人們享受舒淇等明星級般的待遇，床旁大片景觀落地窗與北投最高戶外泡湯池，免出房門就能看著美景入浴，更被選為全台第一好湯！\",\n" +
            "    \"title\":\"北投溫泉\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"dataType\":\"Text\",\n" +
            "    \"imageUrl\":\"rainbow_bridge.jpg\",\n" +
            "    \"text\":\"新興的夜拍景點，算是台北夜拍橋樑中數一數二的\",\n" +
            "    \"title\":\"彩虹橋\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"dataType\":\"Text\",\n" +
            "    \"imageUrl\":\"taipei_101.jpg\",\n" +
            "    \"title\":\"台北101\",\n" +
            "    \"text\":\"台北101（TAIPEI 101）是位於臺灣臺北市信義區的辦公摩天大樓，樓高509.2公尺（1,671英尺），地上樓層共有101層、另有地下5層，總樓地板面積37萬4千平方公尺。\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"dataType\":\"Image\",\n" +
            "    \"imageUrl\":\"c289_1.jpg\"\n" +
            "  }\n" +
            "]";

    public static List<BaseType> constructFakeDataSet() throws JSONException {
        List<BaseType> rtn = new ArrayList<>();
        rtn.add(new TextType(TYPE_TEXT, "xie_r.jpg", "西門町應該是每個人第一次遊台北都必定會去的行程之一，旅遊書總會形容這個地方是「年青人潮流勝地」，但多年前第一次去的時候感覺真的不太好，簡單來說這就是一個很MK的地方。", "西門紅樓"));
        rtn.add(new TextType(TYPE_TEXT, "ferris_wheel.jpg", "記得還小的時候，常常吵著爸爸跟媽媽要出門玩，加上學校又常常暑假作業都叫我們要拿個存根票。", "新兒童樂園"));
        rtn.add(new TextType(TYPE_TEXT, "beitou.jpg", "隱身山嵐綠意間的北投麗禧溫泉酒店，得天獨厚地理位置，讓旅人們享受舒淇等明星級般的待遇，床旁大片景觀落地窗與北投最高戶外泡湯池，免出房門就能看著美景入浴，更被選為全台第一好湯！", "北投溫泉"));
        rtn.add(new TextType(TYPE_TEXT, "rainbow_bridge.jpg", "新興的夜拍景點，算是台北夜拍橋樑中數一數二的", "彩虹橋"));
        rtn.add(new TextType(TYPE_TEXT, "taipei_101.jpg", "台北101（TAIPEI 101）是位於臺灣臺北市信義區的辦公摩天大樓，樓高509.2公尺（1,671英尺），地上樓層共有101層、另有地下5層，總樓地板面積37萬4千平方公尺。", "台北101"));
        rtn.add(new ImageType(TYPE_IMAGE, "c289_1.jpg"));
        rtn.add(new ImageType(TYPE_IMAGE, "cks.jpg"));
        return rtn;
    }
}
