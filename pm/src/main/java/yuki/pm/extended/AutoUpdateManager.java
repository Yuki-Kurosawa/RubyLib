package yuki.pm.extended;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import org.json.JSONObject;
import yuki.control.extended.HttpAsyncClient;
import yuki.control.extended.OnGetString;



/**
 * Auto Update Class
 */
public final class AutoUpdateManager {
    private Context mContext = null;
    private Activity mActivity = null;
    private Handler mUpdateHandler = null;

    public AutoUpdateManager(Context context, Activity activity, Handler updateHandler) {
        mContext = context;
        mActivity = activity;
        mUpdateHandler = updateHandler;
    }

    /**
     * Check Network Info
     *
     * @param sharepref Config File
     * @param key       Config Key
     */
    public void NetworkEnvironmentCheck(final String sharepref, final String key) {

        final SharedPreferences mobilenetwork = mContext.getSharedPreferences(sharepref, Context.MODE_PRIVATE);
        ConnectivityManager mConnectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        boolean lte = false;
        if (NetworkManager.GetNetworkType(mContext) != NetworkManager.NO_NETWORK) {
            if (NetworkManager.GetNetworkType(mContext) == ConnectivityManager.TYPE_MOBILE) {
                lte = true;
            }
        } else {
            AlertDialog.Builder b = new AlertDialog.Builder(mActivity);
            b.setTitle("网络不可用");
            b.setMessage("没有可用的网络连接，程序将会关闭");
            b.setPositiveButton("关闭程序", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
                }
            });
            b.show();
            return;
        }
        if ((mobilenetwork == null || !mobilenetwork.contains(key) && lte)) {
            AlertDialog.Builder b = new AlertDialog.Builder(mActivity);
            b.setTitle("移动网络连接");
            b.setMessage("使用移动网络连接会产生流量费用，仍然通过移动网络打开程序?");
            b.setPositiveButton("允许移动网络连接", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SharedPreferences.Editor e = mobilenetwork.edit();
                    e.putString("lte", "");
                    e.apply();
                }
            });
            b.setNeutralButton("允许本次连接", null);
            b.setNegativeButton("退出程序", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
                }
            });
            b.create().show();
        }
    }

    /**
     * Check Update Via Update.JSON
     *
     * @param updateServer Update Server
     * @param pidUpdate    Package ID Change Handler
     * @param pmUpdate     Package Update Found Handler
     */
    public void UpdateCheck(final String updateServer, final UpdateClickListener pidUpdate,
                            final UpdateClickListener pmUpdate) {
        UpdateCheck(updateServer, "update.json", pidUpdate, pmUpdate);
    }

    /**
     * Check Update Via Network
     *
     * @param updateServer UpdateServer
     * @param updateFile   Update Config File
     * @param pidUpdate    Package ID Change Handler
     * @param pmUpdate     Package Update Found Handler
     */
    public void UpdateCheck(final String updateServer, final String updateFile, final UpdateClickListener pidUpdate,
                            final UpdateClickListener pmUpdate) {
        HttpAsyncClient.AsyncGetString(updateServer + "/" + updateFile, mActivity, new OnGetString() {
            @Override
            public void Get(String data) {
                try {
                    final UpdateInfo newUpdateInfo = new UpdateInfo(new JSONObject(data));
                    final UpdateInfo oldUpdateInfo = new UpdateInfo(pm.GetCurrentVersionName(mContext),
                            pm.GetCurrentVersionCode(mContext), pm.GetCurrentPackageName(mContext));
                    if (oldUpdateInfo.getPackageName() != newUpdateInfo.getPackageName()) {
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                builder.setTitle("程序包标识符更新");
                                builder.setMessage("程序包的标识符更新，更新后需手动卸载旧版本。");
                                builder.setNegativeButton("取消更新", null);
                                pidUpdate.setInfo(newUpdateInfo);
                                builder.setPositiveButton("立即更新", pidUpdate);
                                builder.create().show();
                            }
                        });
                    } else if (oldUpdateInfo.getVersionCode() < newUpdateInfo.getVersionCode()) {
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                builder.setTitle("程序包更新可用");
                                builder.setMessage("有新的程序包更新可供安装\n" + "新版本: " +
                                        newUpdateInfo.getVersionName() + "(" + newUpdateInfo.getVersionCode() + ")" +
                                        "\n更新时间: " + newUpdateInfo.getUpdateTime() + "\n更新人:\n" + newUpdateInfo.getUpdater()
                                        + "\n更新内容:\n" + newUpdateInfo.getUpdateInfo());
                                builder.setNegativeButton("取消更新", null);
                                pmUpdate.setInfo(newUpdateInfo);
                                builder.setPositiveButton("立即更新", pmUpdate);
                                builder.create().show();
                            }
                        });
                    }
                } catch (Exception e) {
                    final String Data = data;
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            builder.setTitle("更新出错");
                            builder.setMessage("无法解析更新数据包\n" + Data);
                            builder.setNegativeButton("关闭", null);
                            builder.create().show();
                        }
                    });
                }
            }
        });
    }
}
