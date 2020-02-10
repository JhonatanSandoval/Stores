package pe.kreatec.stores.presentation.ux.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.vikingsen.inject.viewmodel.savedstate.SavedStateViewModelFactory
import pe.kreatec.stores.R
import pe.kreatec.stores.databinding.FragmentStoreDetailsBinding
import pe.kreatec.stores.inject.Injector
import pe.kreatec.stores.presentation.util.ui.fragment.BaseFragment
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
        validateArguments()
    }

    private fun validateArguments() {

    }

    override fun setUpViewModel() {

    }

}