package cm.aptoide.pt.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import cm.aptoide.pt.R;
import cm.aptoide.pt.view.app.AppViewHolder;
import cm.aptoide.pt.view.app.Application;
import java.text.DecimalFormat;
import java.util.List;
import rx.subjects.PublishSubject;

public class TopBundleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private static final int TOP_APP = R.layout.top_app_item;

  private final PublishSubject<HomeEvent> appClickedEvents;
  private final DecimalFormat oneDecimalFormatter;
  private List<Application> apps;
  private int bundlePosition;
  private HomeBundle homeBundle;

  TopBundleAdapter(List<Application> apps, DecimalFormat oneDecimalFormatter,
      PublishSubject<HomeEvent> appClickedEvents) {
    this.apps = apps;
    this.oneDecimalFormatter = oneDecimalFormatter;
    this.appClickedEvents = appClickedEvents;
    this.homeBundle = null;
    this.bundlePosition = -1;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new TopAppViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(TOP_APP, parent, false), appClickedEvents, oneDecimalFormatter);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    ((AppViewHolder) holder).setApp(apps.get(position), homeBundle, bundlePosition, position);
  }

  @Override public int getItemViewType(int position) {
    return TOP_APP;
  }

  @Override public int getItemCount() {
    return apps.size();
  }

  public void update(List<Application> apps) {
    this.apps = apps;
    notifyDataSetChanged();
  }

  public void updateBundle(HomeBundle homeBundle, int position) {
    this.homeBundle = homeBundle;
    this.bundlePosition = position;
  }
}