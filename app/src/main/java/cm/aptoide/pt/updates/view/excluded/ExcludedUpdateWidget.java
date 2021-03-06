package cm.aptoide.pt.updates.view.excluded;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import cm.aptoide.pt.R;
import cm.aptoide.pt.database.room.RoomUpdate;
import cm.aptoide.pt.networking.image.ImageLoader;
import cm.aptoide.pt.view.recycler.widget.Widget;

/**
 * Created on 15/06/16.
 */
public class ExcludedUpdateWidget extends Widget<ExcludedUpdateDisplayable> {

  private ImageView icon;
  private TextView name;
  private TextView versionCode;
  private TextView packageName;
  private CheckBox isExcluded;

  public ExcludedUpdateWidget(View itemView) {
    super(itemView);
  }

  @Override protected void assignViews(View itemView) {
    icon = itemView.findViewById(R.id.icon);
    name = itemView.findViewById(R.id.name);
    versionCode = itemView.findViewById(R.id.version_code);
    packageName = itemView.findViewById(R.id.apk_id);
    isExcluded = itemView.findViewById(R.id.is_excluded);
  }

  @Override public void bindView(final ExcludedUpdateDisplayable displayable, int position) {
    final RoomUpdate excludedUpdate = displayable.getPojo();

    ImageLoader.with(getContext())
        .load(excludedUpdate.getIcon(), icon);
    name.setText(excludedUpdate.getLabel());
    versionCode.setText(excludedUpdate.getUpdateVersionName());
    packageName.setText(excludedUpdate.getPackageName());

    isExcluded.setOnCheckedChangeListener((buttonView, isChecked) -> {
      displayable.setSelected(isChecked);
    });

    isExcluded.setChecked(displayable.isSelected());
  }
}
