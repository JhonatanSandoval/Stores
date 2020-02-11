package pe.kreatec.stores.presentation.util.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import coil.api.loadAny
import pe.kreatec.stores.presentation.ux.stores.StoresViewModel

object CustomBinders {

    @JvmStatic
    @BindingAdapter("app:loadImageFromUrl")
    fun loadImage(imageView: ImageView, imageUrl: String?) {
        imageUrl?.let {
            imageView.loadAny(it)
        }
    }

    @JvmStatic
    @BindingAdapter("app:refreshing")
    fun refreshing(swipeRefreshLayout: SwipeRefreshLayout, viewModel: StoresViewModel) {
        swipeRefreshLayout.setOnRefreshListener { viewModel.loadStoresFromNetwork(true) }
    }

}