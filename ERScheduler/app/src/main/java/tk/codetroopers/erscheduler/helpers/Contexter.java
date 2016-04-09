package tk.codetroopers.erscheduler.helpers;

import android.content.Context;

public interface Contexter {
    String getStringValue(int resId);
    Context getGlobalContext();
}
