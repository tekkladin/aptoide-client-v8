package cm.aptoide.pt.home.bundles.ads;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cm.aptoide.aptoideviews.skeleton.Skeleton;
import cm.aptoide.aptoideviews.skeleton.SkeletonUtils;
import cm.aptoide.pt.R;
import cm.aptoide.pt.dataprovider.model.v7.Type;
import cm.aptoide.pt.home.bundles.base.AppBundleViewHolder;
import cm.aptoide.pt.home.bundles.base.HomeBundle;
import cm.aptoide.pt.home.bundles.base.HomeEvent;
import cm.aptoide.pt.utils.AptoideUtils;
import cm.aptoide.pt.view.Translator;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import rx.subjects.PublishSubject;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by jdandrade on 13/03/2018.
 */

class AdsBundleViewHolder extends AppBundleViewHolder {

  private final TextView bundleTitle;
  private final Button moreButton;
  private final AdsInBundleAdapter appsInBundleAdapter;
  private final PublishSubject<HomeEvent> uiEventsListener;
  private final RecyclerView appsList;
  private final String marketName;

  private final Skeleton skeleton;

  public AdsBundleViewHolder(View view, PublishSubject<HomeEvent> uiEventsListener,
      DecimalFormat oneDecimalFormatter, PublishSubject<AdHomeEvent> adClickedEvents,
      String marketName) {
    super(view);
    this.marketName = marketName;
    this.uiEventsListener = uiEventsListener;
    bundleTitle = view.findViewById(R.id.bundle_title);
    moreButton = view.findViewById(R.id.bundle_more);
    appsList = view.findViewById(R.id.apps_list);
    appsInBundleAdapter =
        new AdsInBundleAdapter(new ArrayList<>(), oneDecimalFormatter, adClickedEvents);
    LinearLayoutManager layoutManager =
        new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);
    appsList.addItemDecoration(new RecyclerView.ItemDecoration() {
      @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
          RecyclerView.State state) {
        int margin = AptoideUtils.ScreenU.getPixelsForDip(5, view.getResources());
        outRect.set(margin, margin, 0, margin);
      }
    });
    appsList.setLayoutManager(layoutManager);
    appsList.setAdapter(appsInBundleAdapter);
    appsList.setNestedScrollingEnabled(false);
    Resources resources = view.getContext()
        .getResources();
    WindowManager windowManager = (WindowManager) view.getContext()
        .getSystemService(WINDOW_SERVICE);
    skeleton = SkeletonUtils.applySkeleton(appsList, R.layout.app_home_item_skeleton,
        Type.ADS.getPerLineCount(resources, windowManager) * 3);
  }

  @Override public void setBundle(HomeBundle homeBundle, int position) {
    if (!(homeBundle instanceof AdBundle)) {
      throw new IllegalStateException(this.getClass()
          .getName() + " is getting non AdBundle instance!");
    }
    bundleTitle.setText(
        Translator.translate(homeBundle.getTitle(), itemView.getContext(), marketName));

    if (homeBundle.getContent() == null) {
      skeleton.showSkeleton();
    } else {
      skeleton.showOriginal();
      appsInBundleAdapter.updateBundle(homeBundle, position);
      appsInBundleAdapter.update((List<AdClick>) homeBundle.getContent());

      appsList.addOnScrollListener(new RecyclerView.OnScrollListener() {
        @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
          super.onScrolled(recyclerView, dx, dy);
          if (dx > 0) {
            uiEventsListener.onNext(
                new HomeEvent(homeBundle, getAdapterPosition(), HomeEvent.Type.SCROLL_RIGHT));
          }
        }
      });

      moreButton.setOnClickListener(v -> uiEventsListener.onNext(
          new HomeEvent(homeBundle, getAdapterPosition(), HomeEvent.Type.MORE)));
    }
  }
}
