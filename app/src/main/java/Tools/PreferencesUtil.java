package Tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2018/3/20.
 */

public class PreferencesUtil {
    private Context context;
    SharedPreferences sp;
    SharedPreferences.Editor editor ;
    public PreferencesUtil(Context context) {
        this.context = context;
        sp=context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        editor = sp.edit();
    }
    public void saveUserInfo(String email,int userid) {
        editor.putString("usermail",email);
        editor.putInt("userid",userid);
        editor.commit();
    }
    public String getUseremail(){
        return  sp.getString("usermail", null);
    }
    public int getUserId(){
        return sp.getInt("userid",0);
    }
}
