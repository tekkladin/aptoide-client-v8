package cm.aptoide.pt.app;

import cm.aptoide.pt.app.view.NewAppViewFragment.OpenType;
import cm.aptoide.pt.dataprovider.model.v7.GetAppMeta;
import cm.aptoide.pt.dataprovider.model.v7.Malware;
import cm.aptoide.pt.dataprovider.model.v7.Obb;
import cm.aptoide.pt.dataprovider.model.v7.store.Store;
import cm.aptoide.pt.search.model.SearchAdResult;
import cm.aptoide.pt.view.app.AppDeveloper;
import cm.aptoide.pt.view.app.AppFlags;
import cm.aptoide.pt.view.app.DetailedAppRequestResult;
import java.util.List;

/**
 * Created by D01 on 07/05/18.
 */

public class AppViewViewModel {

  private final GetAppMeta.Media media;
  private final String modified;
  private final String appAdded;
  private final Obb obb;
  private final GetAppMeta.Pay pay;
  private final String webUrls;
  private final boolean isPaid;
  private final String uniqueName;
  private final OpenType shouldInstall;
  private final double appc;
  private final SearchAdResult minimalAd;
  private final String editorsChoice;
  private final String originTag;
  private final boolean isStoreFollowed;
  private final long appId;
  private final String appName;
  private final Store store;
  private final String storeTheme;
  private final boolean isGoodApp;
  private final Malware malware;
  private final AppFlags appFlags;
  private final List<String> tags;
  private final List<String> usedFeatures;
  private final List<String> usedPermissions;
  private final long fileSize;
  private final String md5;
  private final String path;
  private final String pathAlt;
  private final int versionCode;
  private final String versionName;
  private final String packageName;
  private final long size;
  private final int downloads;
  private final GetAppMeta.Stats.Rating globalRating;
  private final int packageDownloads;
  private final GetAppMeta.Stats.Rating rating;
  private final AppDeveloper appDeveloper;
  private final String graphic;
  private final String icon;
  private final boolean loading;
  private final DetailedAppRequestResult.Error error;

  public AppViewViewModel(long appId, String appName, Store store, String storeTheme,
      boolean isGoodApp, Malware malware, AppFlags appFlags, List<String> tags,
      List<String> usedFeatures, List<String> usedPermissions, long fileSize, String md5,
      String path, String pathAlt, int versionCode, String versionName, String packageName,
      long size, int downloads, GetAppMeta.Stats.Rating globalRating, int packageDownloads,
      GetAppMeta.Stats.Rating rating, AppDeveloper appDeveloper, String graphic, String icon,
      GetAppMeta.Media media, String modified, String appAdded, Obb obb, GetAppMeta.Pay pay,
      String webUrls, boolean isPaid, String uniqueName, OpenType shouldInstall, double appc,
      SearchAdResult minimalAd, String editorsChoice, String originTag, boolean isStoreFollowed) {
    this.appId = appId;
    this.appName = appName;
    this.store = store;
    this.storeTheme = storeTheme;
    this.isGoodApp = isGoodApp;
    this.malware = malware;
    this.appFlags = appFlags;
    this.tags = tags;
    this.usedFeatures = usedFeatures;
    this.usedPermissions = usedPermissions;
    this.fileSize = fileSize;
    this.md5 = md5;
    this.path = path;
    this.pathAlt = pathAlt;
    this.versionCode = versionCode;
    this.versionName = versionName;
    this.packageName = packageName;
    this.size = size;
    this.downloads = downloads;
    this.globalRating = globalRating;
    this.packageDownloads = packageDownloads;
    this.rating = rating;
    this.appDeveloper = appDeveloper;
    this.graphic = graphic;
    this.icon = icon;
    this.media = media;
    this.modified = modified;
    this.appAdded = appAdded;
    this.obb = obb;
    this.pay = pay;
    this.webUrls = webUrls;
    this.isPaid = isPaid;
    this.uniqueName = uniqueName;
    this.shouldInstall = shouldInstall;
    this.appc = appc;
    this.minimalAd = minimalAd;
    this.editorsChoice = editorsChoice;
    this.originTag = originTag;
    this.isStoreFollowed = isStoreFollowed;
    this.loading = false;
    this.error = null;
  }

  public AppViewViewModel(boolean loading) {
    this.loading = loading;
    this.appId = -1;
    this.appName = "";
    this.store = null;
    this.storeTheme = "";
    this.isGoodApp = false;
    this.malware = null;
    this.appFlags = null;
    this.tags = null;
    this.usedFeatures = null;
    this.usedPermissions = null;
    this.fileSize = -1;
    this.md5 = "";
    this.path = "";
    this.pathAlt = "";
    this.versionCode = -1;
    this.versionName = "";
    this.packageName = "";
    this.size = -1;
    this.downloads = -1;
    this.globalRating = null;
    this.packageDownloads = -1;
    this.rating = null;
    this.appDeveloper = null;
    this.graphic = null;
    this.icon = null;
    this.media = null;
    this.modified = null;
    this.appAdded = null;
    this.obb = null;
    this.pay = null;
    this.webUrls = null;
    this.isPaid = false;
    this.uniqueName = "";
    this.shouldInstall = null;
    this.appc = -1;
    this.minimalAd = null;
    this.editorsChoice = "";
    this.originTag = "";
    this.isStoreFollowed = false;
    this.error = null;
  }

  public AppViewViewModel(DetailedAppRequestResult.Error error) {
    this.error = error;
    this.appId = -1;
    this.appName = "";
    this.store = null;
    this.storeTheme = "";
    this.isGoodApp = false;
    this.malware = null;
    this.appFlags = null;
    this.tags = null;
    this.usedFeatures = null;
    this.usedPermissions = null;
    this.fileSize = -1;
    this.md5 = "";
    this.path = "";
    this.pathAlt = "";
    this.versionCode = -1;
    this.versionName = "";
    this.packageName = "";
    this.size = -1;
    this.downloads = -1;
    this.globalRating = null;
    this.packageDownloads = -1;
    this.rating = null;
    this.appDeveloper = null;
    this.graphic = null;
    this.icon = null;
    this.media = null;
    this.modified = null;
    this.appAdded = null;
    this.obb = null;
    this.pay = null;
    this.webUrls = null;
    this.isPaid = false;
    this.uniqueName = "";
    this.shouldInstall = null;
    this.appc = -1;
    this.minimalAd = null;
    this.editorsChoice = "";
    this.originTag = "";
    this.isStoreFollowed = false;
    this.loading = false;
  }

  public boolean isStoreFollowed() {
    return isStoreFollowed;
  }

  public long getAppId() {
    return appId;
  }

  public String getAppName() {
    return appName;
  }

  public Store getStore() {
    return store;
  }

  public String getPackageName() {
    return packageName;
  }

  public long getSize() {
    return size;
  }

  public AppDeveloper getDeveloper() {
    return appDeveloper;
  }

  public String getGraphic() {
    return graphic;
  }

  public String getIcon() {
    return icon;
  }

  public GetAppMeta.Media getMedia() {
    return media;
  }

  public String getModified() {
    return modified;
  }

  public String getAppAdded() {
    return appAdded;
  }

  public Obb getObb() {
    return obb;
  }

  public GetAppMeta.Pay getPay() {
    return pay;
  }

  public int getDownloads() {
    return downloads;
  }

  public GetAppMeta.Stats.Rating getGlobalRating() {
    return globalRating;
  }

  public int getPackageDownloads() {
    return packageDownloads;
  }

  public GetAppMeta.Stats.Rating getRating() {
    return rating;
  }

  public boolean isLoading() {
    return loading;
  }

  public DetailedAppRequestResult.Error getError() {
    return error;
  }

  public boolean hasError() {
    return (error != null);
  }

  public String getWebUrls() {
    return webUrls;
  }

  public AppFlags getAppFlags() {
    return appFlags;
  }

  public List<String> getTags() {
    return tags;
  }

  public List<String> getUsedFeatures() {
    return usedFeatures;
  }

  public List<String> getUsedPermissions() {
    return usedPermissions;
  }

  public long getFileSize() {
    return fileSize;
  }

  public String getMd5() {
    return md5;
  }

  public String getPath() {
    return path;
  }

  public String getPathAlt() {
    return pathAlt;
  }

  public int getVersionCode() {
    return versionCode;
  }

  public String getVersionName() {
    return versionName;
  }

  public boolean isGoodApp() {
    return isGoodApp;
  }

  public Malware getMalware() {
    return malware;
  }

  public boolean isPaid() {
    return isPaid;
  }

  public String getUniqueName() {
    return uniqueName;
  }

  public OpenType getShouldInstall() {
    return shouldInstall;
  }

  public double getAppc() {
    return appc;
  }

  public SearchAdResult getMinimalAd() {
    return minimalAd;
  }

  public String getStoreTheme() {
    return storeTheme;
  }

  public String getEditorsChoice() {
    return editorsChoice;
  }

  public String getOriginTag() {
    return originTag;
  }
}
