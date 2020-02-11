package pe.kreatec.stores.presentation.util.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.loadAny

object CustomBinders {

    @JvmStatic
    @BindingAdapter("app:loadImageFromUrl")
    fun loadImage(imageView: ImageView, imageUrl: String?) {
        imageUrl?.let {
            imageView.loadAny(it)
        }
    }

}