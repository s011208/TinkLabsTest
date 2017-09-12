package yhh.tinklabstest.util;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utilities {

    private Utilities() {
    }

    public static String readStringFromAssets(@NonNull final Context context, @NonNull final String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName)));
        StringBuilder sb = new StringBuilder();
        String mLine = reader.readLine();
        while (mLine != null) {
            sb.append(mLine);
            mLine = reader.readLine();
        }
        reader.close();
        return sb.toString();
    }

}
