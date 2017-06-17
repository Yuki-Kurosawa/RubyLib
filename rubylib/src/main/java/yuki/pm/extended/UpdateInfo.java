package yuki.pm.extended;

import org.json.JSONObject;

/**
 * Update Information Struct class.
 */
public class UpdateInfo {
    private String versionName;
    private int versionCode;
    private String updateInfo;
    private String updater;
    private String updateTime;
    private String packageName;
    private String updateURL;

    /**
     * Init Update Info via JSON
     *
     * @param jsonUpdateData Update JSON Data
     * @throws Exception Json Parse Error
     */
    public UpdateInfo(JSONObject jsonUpdateData) throws Exception {
        versionName = jsonUpdateData.getString("versionName");
        versionCode = jsonUpdateData.getInt("versionCode");
        updateInfo = jsonUpdateData.getString("updateInfo");
        updater = jsonUpdateData.getString("updater");
        updateTime = jsonUpdateData.getString("updateTime");
        packageName = jsonUpdateData.getString("packageName");
        updateURL = jsonUpdateData.getString("updateURL");
    }

    /**
     * Init Update Info via Parameters
     *
     * @param versionName Version Name
     * @param versionCode Version Code
     * @param packageName Package ID
     */
    public UpdateInfo(String versionName, int versionCode, String packageName) {
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.packageName = packageName;
    }

    /**
     * Return Package ID
     *
     * @return Pakcage ID
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Return Version Name
     *
     * @return Version Name
     */
    public String getVersionName() {
        return versionName;
    }

    /**
     * Return Version Code
     *
     * @return Version Code
     */
    public int getVersionCode() {
        return versionCode;
    }

    /**
     * Return Update Description
     *
     * @return Update Description
     */
    public String getUpdateInfo() {
        return updateInfo;
    }

    /**
     * Return Update Packager
     *
     * @return Update Packager
     */
    public String getUpdater() {
        return updater;
    }

    /**
     * Return Update Time
     *
     * @return Update Time
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * Return Update URL
     *
     * @return Update URL
     */
    public String getUpdateURL(){
        return updateURL;
    }
}
