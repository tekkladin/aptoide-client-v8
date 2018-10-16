package cm.aptoide.pt.discovery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import cm.aptoide.analytics.implementation.navigation.ScreenTagHistory;
import cm.aptoide.pt.R;
import cm.aptoide.pt.view.fragment.NavigationTrackFragment;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

public class VideosFragment extends NavigationTrackFragment implements VideosView {

  @Inject VideosPresenter presenter;
  private RecyclerView list;
  private VideoAdapter adapter;
  private LinearLayout videosEmptyState;
  private Button discoveryOptionButton;
  private Toolbar toolbar;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    adapter = new VideoAdapter(Collections.emptyList());
    return inflater.inflate(R.layout.fragment_videos, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getFragmentComponent(savedInstanceState).inject(this);
    list = (RecyclerView) view.findViewById(R.id.fragment_videos_list);
    videosEmptyState = (LinearLayout) view.findViewById(R.id.video_empty_state);

    Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);
    toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());

    toolbar.inflateMenu(R.menu.menu_discovery_fragment);

    setHasOptionsMenu(true);

    list.setAdapter(adapter);
    list.setLayoutManager(new LinearLayoutManager(getContext()));
    attachPresenter(presenter);
  }

  @Override public ScreenTagHistory getHistoryTracker() {
    return ScreenTagHistory.Builder.build(this.getClass()
        .getSimpleName());
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
  }

  @Override public void showVideos(List<Video> videos) {
    adapter.add(videos);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    switch (id) {

      case R.menu.menu_discovery_fragment:
        toolbar.inflateMenu(R.menu.menu_discovery_fragment);
        return true;
      // TODO: 12/09/2018

      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
