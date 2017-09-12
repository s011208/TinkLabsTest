package yhh.tinklabstest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import yhh.tinklabstest.util.Utilities;

import static junit.framework.Assert.assertEquals;

/**
 * Created by s011208 on 2017/9/12.
 */

@RunWith(AndroidJUnit4.class)
public class UtilitiesTest {

    @Test
    public void readStringFromAssets_is_correct() throws IOException {
        final String expectedResult = "[  {    \"dataType\":\"Text\",    \"imageUrl\":\"bd_eat.jpg\",    \"text\":\"博多拉麵內湖成功路店位於東湖圓環這邊，這裡旁邊還有臻饌麻辣鍋吃到飽，就在博多拉麵的旁邊。\",    \"title\":\"博多拉麵\"  },  {    \"dataType\":\"Image\",    \"imageUrl\":\"deloin.jpg\"  },  {    \"dataType\":\"Text\",    \"imageUrl\":\"f_chicken.jpg\",    \"text\":\"我喜歡他的炸雞還可以選部位，不像肯德基麥當勞每次拿到雞翅都要肚爛一下XD我們這天就直接點了五號全家餐，NT259，有8塊炸雞唷！\",    \"title\":\"胖老爹炸雞\"  },  {    \"dataType\":\"Text\",    \"imageUrl\":\"lamb.jpg\",    \"text\":\"莫宰羊的紅燒羊肉爐湯底很優，整體表現不錯。但價位著實高了點，只能偶爾為之囉！\",    \"title\":\"莫宰羊精緻羊肉料理 \"  },  {    \"dataType\":\"Text\",    \"imageUrl\":\"second_floor.jpg\",    \"text\":\"我喜歡貳樓餐廳的氣氛、採光很好的裝潢，舒適的用餐環境。當然也還有美味的餐點！這邊的商業午餐很受歡迎。平日下午去也是超多人的！我之前就去過內湖小貳樓、也去過南港CITYLINK的貳樓，印象都不賴。也是間對兒童友善的餐廳、還有對寵物也很友善，還會有免費餐點呢！\",    \"title\":\"貳樓餐廳\"  },  {    \"dataType\":\"Text\",    \"imageUrl\":\"td_hp.jpg\",    \"text\":\"記得那天小沁跟一群部落格好友一起約在新勵進，但因為我塞車遲到所以就沒拍這間好吃的酸菜白肉鍋。\",    \"title\":\"新勵進酸菜白肉火鍋\"  },  {    \"dataType\":\"Image\",    \"imageUrl\":\"breakfast.jpg\"  }]";

        final Context appContext = InstrumentationRegistry.getTargetContext();
        final String actualResult = Utilities.readStringFromAssets(appContext, "eat");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void eatDataJSONArray_length_is7() throws IOException, JSONException {
        final Context appContext = InstrumentationRegistry.getTargetContext();
        final String rawData = Utilities.readStringFromAssets(appContext, "eat");
        final JSONArray expectedJSONArray = new JSONArray(rawData);

        assertEquals(7, expectedJSONArray.length());
    }
}
