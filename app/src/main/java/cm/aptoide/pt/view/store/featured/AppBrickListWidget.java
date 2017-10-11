/*
 * Copyright (c) 2016.
 * Modified by Neurophobic Animal on 06/07/2016.
 */

package cm.aptoide.pt.view.store.featured;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import cm.aptoide.pt.AptoideApplication;
import cm.aptoide.pt.R;
import cm.aptoide.pt.crashreports.CrashReport;
import cm.aptoide.pt.dataprovider.model.v7.listapp.App;
import cm.aptoide.pt.networking.image.ImageLoader;
import cm.aptoide.pt.view.recycler.widget.Widget;
import com.jakewharton.rxbinding.view.RxView;

/**
 * Created by neuro on 09-05-2016.
 */
public class AppBrickListWidget extends Widget<AppBrickListDisplayable> {

  private TextView name;
  private ImageView graphic;
  private RatingBar ratingBar;

  public AppBrickListWidget(View itemView) {
    super(itemView);
  }

  @Override protected void assignViews(View itemView) {
    name = (TextView) itemView.findViewById(R.id.app_name);
    graphic = (ImageView) itemView.findViewById(R.id.featured_graphic);
    ratingBar = (RatingBar) itemView.findViewById(R.id.ratingbar);
  }

  @Override public void bindView(AppBrickListDisplayable displayable) {
    App app = displayable.getPojo();

    ImageLoader.with(getContext())
        .load(app.getGraphic(), R.drawable.placeholder_brick, graphic);
    name.setText(app.getName());
    ratingBar.setRating(app.getStats()
        .getRating()
        .getAvg());
    compositeSubscription.add(RxView.clicks(itemView)
        .subscribe(v -> {
          getFragmentNavigator().navigateTo(AptoideApplication.getFragmentProvider()
              .newAppViewFragment(app.getId(), app.getPackageName(), app.getStore()
                  .getAppearance()
                  .getTheme(), app.getStore()
                  .getName(), displayable.getTag(), getAdapterPosition()), true);
        }, throwable -> CrashReport.getInstance()
            .log(throwable)));
  }
}
