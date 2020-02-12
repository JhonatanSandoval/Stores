package pe.kreatec.stores.presentation.ux.details

import com.vikingsen.inject.viewmodel.ViewModelInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pe.kreatec.stores.domain.model.Store
import pe.kreatec.stores.domain.usecase.StoreUseCase
import pe.kreatec.stores.presentation.util.ui.scope.ioScope
import pe.kreatec.stores.presentation.util.ui.viewmodel.BaseViewModel

class StoreDetailsViewModel
@ViewModelInject constructor(
    private val storeUseCase: StoreUseCase
) : BaseViewModel<StoreDetailsViewModel.Event>(), CoroutineScope by ioScope() {

    fun loadStoreDetails(storeId: Int) = launch {
        storeUseCase.getStore(storeId).collect { sendEvent(Event.LoadStoreDetails(it)) }
    }

    fun openGoogleMap(latitude: Double?, longitude: Double?) = sendEvent(Event.OpenGoogleMapPosition(latitude, longitude))

    sealed class Event {
        data class LoadStoreDetails(val store: Store) : Event()
        data class OpenGoogleMapPosition(val latitude: Double?, val longitude: Double?) : Event()
    }

}