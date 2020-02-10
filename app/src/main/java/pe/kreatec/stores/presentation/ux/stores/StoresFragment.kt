package pe.kreatec.stores.presentation.ux.stores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.vikingsen.inject.viewmodel.savedstate.SavedStateViewModelFactory
import kotlinx.coroutines.launch
import pe.kreatec.stores.R
import pe.kreatec.stores.databinding.FragmentStoresBinding
import pe.kreatec.stores.inject.Injector
import pe.kreatec.stores.presentation.util.ui.fragment.BaseFragment
import javax.inject.Inject

class StoresFragment : BaseFragment<FragmentStoresBinding>() {

    private val navController by lazy { NavHostFragment.findNavController(this) }
    override val layout: Int = R.layout.fragment_stores

    @Inject lateinit var viewModelFactory: SavedStateViewModelFactory.Factory
    private val viewModel by viewModels<StoresViewModel> { viewModelFactory.create(this) }

    init {
        Injector.get().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindView(inflater, container)
        return binding.root
    }

    override fun setUpViewModel() {
        binding.viewModel = viewModel

        lifecycleScope.launch {
            for (event in viewModel.eventChannel) {
                when (event) {
                    is StoresViewModel.Event.ShowLoader -> {

                    }
                    is StoresViewModel.Event.LoadStores -> {

                    }
                    is StoresViewModel.Event.SelectStore -> {
                        val action = StoresFragmentDirections.actionStoresFragmentToStoreDetailsFragment(event.storeId)
                        navController.navigate(action)
                    }
                }
            }
        }

        viewModel.loadStoresFromNetwork()
    }

}