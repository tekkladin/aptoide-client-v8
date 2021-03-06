/*
 * Copyright (c) 2016.
 * Modified on 12/08/2016.
 */

package cm.aptoide.pt.app.view.displayable;

import cm.aptoide.pt.R;
import cm.aptoide.pt.dataprovider.model.v7.listapp.App;
import cm.aptoide.pt.themes.ThemeManager;
import cm.aptoide.pt.view.recycler.displayable.DisplayablePojo;

/**
 * Created on 07/07/16.
 */
public class OtherVersionDisplayable extends DisplayablePojo<App> {

  private ThemeManager themeManager;

  public OtherVersionDisplayable() {
  }

  public OtherVersionDisplayable(App pojo, ThemeManager themeManager) {
    super(pojo);
    this.themeManager = themeManager;
  }

  @Override protected Configs getConfig() {
    return new Configs(1, true);
  }

  @Override public int getViewLayout() {
    return R.layout.other_version_row;
  }

  public ThemeManager getThemeManager() {
    return themeManager;
  }
}
