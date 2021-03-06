package cm.aptoide.aptoideviews.filters

import android.widget.Button
import androidx.core.content.ContextCompat
import cm.aptoide.aptoideviews.R
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.fa.epoxysample.bundles.models.base.BaseViewHolder

@EpoxyModelClass
abstract class ClearFiltersModel : EpoxyModelWithHolder<ClearFiltersModel.CardHolder>() {

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var eventListener: FilterEventListener? = null

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var textColorStateList: Int? = null

  override fun bind(holder: CardHolder) {
    super.bind(holder)

    holder.clearButton.setOnClickListener {
      eventListener?.onFilterEvent(FilterEventListener.EventType.CLEAR_EVENT_CLICK, null)
    }
    textColorStateList?.let { color ->
      holder.clearButton.setTextColor(
          ContextCompat.getColorStateList(holder.itemView.context, color))
    }
  }

  override fun getDefaultLayout(): Int {
    return R.layout.clear_filters_item
  }

  class CardHolder : BaseViewHolder() {
    val clearButton by bind<Button>(R.id.clear_button)
  }
}