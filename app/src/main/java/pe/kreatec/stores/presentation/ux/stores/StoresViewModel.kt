package pe.kreatec.stores.presentation.ux.stores

import com.vikingsen.inject.viewmodel.ViewModelInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pe.kreatec.stores.data.remote.util.ApiResponse
import pe.kreatec.stores.domain.model.Store
import pe.kreatec.stores.domain.usecase.StoreUseCase
import pe.kreatec.stores.presentation.util.ui.scope.ioScope
import pe.kreatec.stores.presentation.util.ui.viewmodel.BaseViewModel
import timber.log.Timber

class StoresViewModel
@ViewModelInject constructor(
    private val storeUseCase: StoreUseCase
) : BaseViewModel<StoresViewModel.Event>(), CoroutineScope by ioScope() {

    fun loadStoresFromDb() = launch {
        storeUseCase.getStoresFromDb().collect {
            sendEvent(Event.LoadStores(it))
        }
    }

    fun loadStoresFromNetwork() = launch {
        when (val result = storeUseCase.getStoresFromNetwork()) {
            is ApiResponse.Success -> {
                Timber.i("success called: ${result.items}")
                sendEvent(Event.LoadStores(result.items))
            }
            is ApiResponse.Error -> {
                Timber.e("error called: ${result.errorMessage}")
            }
            is ApiResponse.Exception -> {
                Timber.e("exception called: ${result.exception}")
            }
        }
    }

    fun selectStore() {
        sendEvent(Event.SelectStore(123))
    }

    sealed class Event {
        data class ShowLoader(val loader: Boolean) : Event()
        data class LoadStores(val stores: List<Store>?) : Event()
        data class SelectStore(val storeId: Int) : Event()
    }

}