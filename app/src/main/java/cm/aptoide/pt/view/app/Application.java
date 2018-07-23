package cm.aptoide.pt.view.app;

/**
 * Created by trinkes on 18/10/2017.
 */

public class Application {
  private final String name;
  private final String icon;
  private final float rating;
  private final int downloads;
  private final long appId;
  private final String packageName;
  private final String tag;
  private final boolean hasAppcIab;
  private final boolean hasAppcAds;

  public Application(String name, String icon, float rating, int downloads, String packageName,
      long appId, String tag, boolean hasBilling, boolean hasAdvertising) {
    this.name = name;
    this.icon = icon;
    this.rating = rating;
    this.downloads = downloads;
    this.appId = appId;
    this.packageName = packageName;
    this.tag = tag;
    this.hasAppcIab = hasBilling;
    this.hasAppcAds = hasAdvertising;
  }

  public Application() {
    name = null;
    icon = null;
    rating = -1;
    downloads = -1;
    appId = -1;
    packageName = null;
    tag = "";
    hasAppcIab = false;
    hasAppcAds = false;
  }

  public long getAppId() {
    return appId;
  }

  public String getPackageName() {
    return packageName;
  }

  public String getIcon() {
    return icon;
  }

  public float getRating() {
    return rating;
  }

  public int getDownloads() {
    return downloads;
  }

  public String getName() {
    return name;
  }

  public String getTag() {
    return tag;
  }

  public boolean hasAppcIab() {
    return hasAppcIab;
  }

  public boolean hasAppcAds() {
    return hasAppcAds;
  }
}
