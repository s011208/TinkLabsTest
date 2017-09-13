package yhh.tinklabstest;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import yhh.tinklabstest.data.LoadDataTask;
import yhh.tinklabstest.data.type.BaseType;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class LoadDataTaskTest {

    private static final String DATA = "[\n" +
            "  {\n" +
            "    \"dataType\":\"Text\",\n" +
            "    \"imageUrl\":\"file:///android_asset/xie_r.jpg\",\n" +
            "    \"text\":\"西門町應該是每個人第一次遊台北都必定會去的行程之一，旅遊書總會形容這個地方是「年青人潮流勝地」，但多年前第一次去的時候感覺真的不太好，簡單來說這就是一個很MK的地方。\",\n" +
            "    \"title\":\"西門紅樓\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"dataType\":\"Image\",\n" +
            "    \"imageUrl\":\"file:///android_asset/cks.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"dataType\":\"Text\",\n" +
            "    \"imageUrl\":\"file:///android_asset/ferris_wheel.jpg\",\n" +
            "    \"text\":\"記得還小的時候，常常吵著爸爸跟媽媽要出門玩，加上學校又常常暑假作業都叫我們要拿個存根票。\",\n" +
            "    \"title\":\"新兒童樂園\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"dataType\":\"Text\",\n" +
            "    \"imageUrl\":\"file:///android_asset/beitou.jpg\",\n" +
            "    \"text\":\"隱身山嵐綠意間的北投麗禧溫泉酒店，得天獨厚地理位置，讓旅人們享受舒淇等明星級般的待遇，床旁大片景觀落地窗與北投最高戶外泡湯池，免出房門就能看著美景入浴，更被選為全台第一好湯！\",\n" +
            "    \"title\":\"北投溫泉\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"dataType\":\"Text\",\n" +
            "    \"imageUrl\":\"file:///android_asset/rainbow_bridge.jpg\",\n" +
            "    \"text\":\"新興的夜拍景點，算是台北夜拍橋樑中數一數二的\",\n" +
            "    \"title\":\"彩虹橋\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"dataType\":\"Text\",\n" +
            "    \"imageUrl\":\"file:///android_asset/taipei_101.jpg\",\n" +
            "    \"title\":\"台北101\",\n" +
            "    \"text\":\"台北101（TAIPEI 101）是位於臺灣臺北市信義區的辦公摩天大樓，樓高509.2公尺（1,671英尺），地上樓層共有101層、另有地下5層，總樓地板面積37萬4千平方公尺。\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"dataType\":\"Image\",\n" +
            "    \"imageUrl\":\"file:///android_asset/c289_1.jpg\"\n" +
            "  }\n" +
            "]";

    @Test
    public void generateList_is_correct() throws IOException, JSONException {
        LoadDataTask loadDataTask = new LoadDataTask(InstrumentationRegistry.getTargetContext(), null);
        List<BaseType> baseTypeList = loadDataTask.generateList(DATA);

        assertEquals(7, baseTypeList.size());
    }
}
