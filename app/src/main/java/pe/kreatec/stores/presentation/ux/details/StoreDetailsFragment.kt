package pe.kreatec.stores.presentation.ux.details

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.vikingsen.inject.viewmodel.savedstate.SavedStateViewModelFactory
import kotlinx.coroutines.launch
import pe.kreatec.stores.R
import pe.kreatec.stores.databinding.FragmentStoreDetailsBinding
import pe.kreatec.stores.inject.Injector
import pe.kreatec.stores.presentation.util.ui.fragment.BaseFragment
import timber.log.Timber
import javax.inject.Inject

class StoreDetailsFragment : BaseFragment<FragmentStoreDetailsBinding>() {

    override val layout: Int = R.layout.fragment_store_details

    @Inject lateinit var viewModelFactory: SavedStateViewModelFactory.Factory
    private val viewModel by viewModels<StoreDetailsViewModel> { viewModelFactory.create(this) }

    private val detailsArgs: StoreDetailsFragmentArgs by navArgs()

    init {
        Injector.get().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindView(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadStoreDetails(detailsArgs.storeId)
    }

    override fun setUpViewModel() {
        binding.viewModel = viewModel

        lifecycleScope.launch {
            for (event in viewModel.eventChannel) {
                when (event) {
                    is StoreDetailsViewModel.Event.LoadStoreDetails -> {
                        binding.store = event.store
                        activity?.title = event.store.name
                    }
                    is StoreDetailsViewModel.Event.OpenGoogleMapPosition -> {
                        val (latitude, longitude) = event
                        openGoogleMapPosition(latitude, longitude)
                    }
                }
            }
        }
    }

    private fun openGoogleMapPosition(latitude: Double?, longitude: Double?) {
        val url = "http://maps.google.com/maps?daddr=${latitude},${longitude}&navigate=yes"

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity")

        try {
            context?.startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            try {
                val unrestrictedIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context?.startActivity(unrestrictedIntent)
            } catch (innerEx: ActivityNotFoundException) {
                Timber.e("Must install Google Maps :)")
            }
        }
    }

}