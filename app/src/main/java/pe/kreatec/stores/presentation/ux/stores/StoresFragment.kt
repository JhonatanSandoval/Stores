package pe.kreatec.stores.presentation.ux.stores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.vikingsen.inject.viewmodel.savedstate.SavedStateViewModelFactory
import kotlinx.coroutines.launch
import pe.kreatec.stores.R
import pe.kreatec.stores.databinding.FragmentStoresBinding
import pe.kreatec.stores.inject.Injector
import pe.kreatec.stores.presentation.util.ui.fragment.BaseFragment
import pe.kreatec.stores.presentation.util.ui.list.GridSpacingItemDecoration
import javax.inject.Inject

class StoresFragment : BaseFragment<FragmentStoresBinding>() {

    private val navController by lazy { NavHostFragment.findNavController(this) }
    override val layout: Int = R.layout.fragment_stores

    @Inject lateinit var viewModelFactory: SavedStateViewModelFactory.Factory
    private val viewModel by viewModels<StoresViewModel> { viewModelFactory.create(this) }

    private val adapter by lazy { StoresAdapter(viewModel) }

    init {
        Injector.get().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindView(inflater, container)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.rvStores.layoutManager = GridLayoutManager(context, 2)
        binding.rvStores.addItemDecoration(GridSpacingItemDecoration())
        binding.rvStores.adapter = adapter
    }

    override fun setUpViewModel() {
        binding.viewModel = viewModel

        lifecycleScope.launch {
            for (event in viewModel.eventChannel) {
                when (event) {
                    is StoresViewModel.Event.ShowLoader -> {
                        if (!event.show) binding.swipeRefresh.isRefreshing = false
                    }
                    is StoresViewModel.Event.LoadStores -> event.stores?.let { adapter.stores = it }
                    is StoresViewModel.Event.SelectStore -> {
                        val action = StoresFragmentDirections.actionStoresFragmentToStoreDetailsFragment(event.storeId)
                        navController.navigate(action)
                    }
                }
            }
        }

    }

}