package pe.kreatec.stores.presentation.ux.stores

import com.vikingsen.inject.viewmodel.ViewModelInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pe.kreatec.stores.data.prefs.Prefs
import pe.kreatec.stores.data.remote.util.ApiResponse
import pe.kreatec.stores.domain.model.Store
import pe.kreatec.stores.domain.usecase.StoreUseCase
import pe.kreatec.stores.presentation.util.ui.scope.ioScope
import pe.kreatec.stores.presentation.util.ui.viewmodel.BaseViewModel
import timber.log.Timber

class StoresViewModel
@ViewModelInject constructor(
    private val storeUseCase: StoreUseCase,
    private val prefs: Prefs
) : BaseViewModel<StoresViewModel.Event>(), CoroutineScope by ioScope() {

    init {
        if (prefs.isFirstTime) {
            loadStoresFromNetwork(true)
        } else {
            loadStoresFromDb()
        }
    }

    fun loadStoresFromDb() = launch {
        Timber.i("loading from database")
        storeUseCase.getStoresFromDb().collect {
            sendEvent(Event.LoadStores(it))
        }
    }

    fun loadStoresFromNetwork(save: Boolean = false) = launch {
        Timber.i("loading from network | saving? $save")
        sendEvent(Event.ShowLoader(true))
        when (val result = storeUseCase.getStoresFromNetwork(save)) {
            is ApiResponse.Success -> {
                if (save) prefs.isFirstTime = false
                loadStoresFromDb()
            }
            is ApiResponse.Error -> {
                Timber.e("error called: ${result.errorMessage}")
            }
            is ApiResponse.Exception -> {
                Timber.e("exception called: ${result.exception}")
            }
        }
        sendEvent(Event.ShowLoader(false))
    }

    fun selectStore(storeId: Int) {
        sendEvent(Event.SelectStore(storeId))
    }

    sealed class Event {
        data class ShowLoader(val show: Boolean) : Event()
        data class LoadStores(val stores: List<Store>?) : Event()
        data class SelectStore(val storeId: Int) : Event()
    }

}