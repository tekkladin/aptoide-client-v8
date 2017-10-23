package cm.aptoide.pt.view.app;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import cm.aptoide.pt.AptoideApplication;
import cm.aptoide.pt.R;
import cm.aptoide.pt.analytics.ScreenTagHistory;
import cm.aptoide.pt.crashreports.CrashReport;
import cm.aptoide.pt.database.AccessorFactory;
import cm.aptoide.pt.database.realm.Store;
import cm.aptoide.pt.dataprovider.WebService;
import cm.aptoide.pt.store.StoreCredentialsProviderImpl;
import cm.aptoide.pt.utils.AptoideUtils;
import cm.aptoide.pt.view.BackButtonFragment;
import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.subjects.PublishSubject;

/**
 * Created by trinkes on 17/10/2017.
 */

public class ListStoreAppsFragment extends BackButtonFragment implements ListStoreAppsView {

  public static final String STORE_ID = "cm.aptoide.pt.ListStoreAppsFragment.storeId";
  public static final int LOAD_THRESHOLD = 5;
  private ListStoreAppsAdapter adapter;
  private long storeId;
  private PublishSubject<Application> appClicks;
  private PublishSubject<Void> refreshEvent;
  private RecyclerView recyclerView;
  private GridLayoutManager layoutManager;
  private ProgressBar startingLoadingLayout;
  private SwipeRefreshLayout swipeRefreshLayout;

  public static Fragment newInstance(long storeId) {
    Bundle args = new Bundle();
    args.putLong(STORE_ID, storeId);
    Fragment fragment = new ListStoreAppsFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    appClicks = PublishSubject.create();
    storeId = getArguments().getLong(STORE_ID);
    refreshEvent = PublishSubject.create();
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    recyclerView.setVisibility(View.GONE);
    swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
    swipeRefreshLayout.setOnRefreshListener(() -> refreshEvent.onNext(null));
    setupToolbar(view);
    adapter = new ListStoreAppsAdapter(new ArrayList<>(), appClicks);
    recyclerView.setAdapter(adapter);
    int spanSize = getSpanSize(3);
    layoutManager = new GridLayoutManager(getContext(), spanSize);
    layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
      @Override public int getSpanSize(int position) {
        if (adapter != null && adapter.getItem(position) instanceof AppLoading) {
          return spanSize;
        }
        return 1;
      }
    });
    recyclerView.setLayoutManager(layoutManager);
    startingLoadingLayout = (ProgressBar) view.findViewById(R.id.progress_bar);
    startingLoadingLayout.setVisibility(View.VISIBLE);

    recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
      @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
          RecyclerView.State state) {
        int margin = AptoideUtils.ScreenU.getPixelsForDip(5, getResources());
        outRect.set(margin, margin, margin, margin);
      }
    });

    AptoideApplication applicationContext =
        (AptoideApplication) getContext().getApplicationContext();
    int limit = spanSize * 10;
    attachPresenter(new ListStoreAppsPresenter(this, storeId, AndroidSchedulers.mainThread(),
        new AppCenter(new AppService(new StoreCredentialsProviderImpl(
            AccessorFactory.getAccessorFor(
                ((AptoideApplication) getContext().getApplicationContext()
                    .getApplicationContext()).getDatabase(), Store.class)),
            applicationContext.getBodyInterceptorPoolV7(), applicationContext.getDefaultClient(),
            WebService.getDefaultConverter(), applicationContext.getTokenInvalidator(),
            applicationContext.getDefaultSharedPreferences(), 0, limit)), CrashReport.getInstance(),
        getFragmentNavigator()), savedInstanceState);
  }

  @Override public ScreenTagHistory getHistoryTracker() {
    return ScreenTagHistory.Builder.build(this.getClass()
        .getSimpleName());
  }

  @Override public void onDestroyView() {
    recyclerView = null;
    adapter = null;
    layoutManager = null;
    super.onDestroyView();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    setHasOptionsMenu(true);
    return inflater.inflate(R.layout.list_store_apps_fragment_layout, container, false);
  }

  @Override public void addApps(List<Application> appsList) {
    adapter.addApps(appsList);
  }

  @Override public Observable<Application> getAppClick() {
    return appClicks;
  }

  @Override public Observable<Object> reachesBottom() {
    return RxRecyclerView.scrollEvents(recyclerView)
        .distinctUntilChanged()
        .filter(scroll -> isEndReached())
        .cast(Object.class);
  }

  @Override public void hideLoading() {
    adapter.hideLoading();
  }

  @Override public void showLoading() {
    adapter.showLoading();
  }

  @Override public void hideStartingLoading() {
    startingLoadingLayout.setVisibility(View.GONE);
    recyclerView.setVisibility(View.VISIBLE);
  }

  @Override public PublishSubject<Void> getRefreshEvent() {
    return refreshEvent;
  }

  @Override public void hideRefreshLoading() {
    swipeRefreshLayout.setRefreshing(false);
  }

  @Override public void setApps(List<Application> applications) {
    adapter.setApps(applications);
  }

  private boolean isEndReached() {
    return layoutManager.getItemCount() - layoutManager.findLastVisibleItemPosition()
        <= LOAD_THRESHOLD;
  }

  public int getSpanSize(int defaultSpan) {
    return (int) (AptoideUtils.ScreenU.getScreenWidthInDip(
        (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE),
        getContext().getResources()) / AptoideUtils.ScreenU.REFERENCE_WIDTH_DPI * defaultSpan);
  }

  public void setupToolbar(View view) {
    Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
    ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);
    toolbar.setTitle(R.string.list_store_apps_fragment_title);
    actionBar.setTitle(toolbar.getTitle());
  }
}
