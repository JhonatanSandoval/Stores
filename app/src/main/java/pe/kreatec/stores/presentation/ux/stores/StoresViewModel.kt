package pe.kreatec.stores.presentation.ux.stores

import com.vikingsen.inject.viewmodel.ViewModelInject
import kotlinx.coroutines.CoroutineScope
import pe.kreatec.stores.presentation.util.ui.scope.ioScope
import pe.kreatec.stores.presentation.util.ui.viewmodel.BaseViewModel

class StoresViewModel
@ViewModelInject constructor(

) : BaseViewModel<StoresViewModel.Event>(), CoroutineScope by ioScope() {

    fun selectStore() {
        sendEvent(Event.SelectStore(123))
    }

    sealed class Event {
        data class SelectStore(val storeId: Int) : Event()
    }

}