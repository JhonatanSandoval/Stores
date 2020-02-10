package pe.kreatec.stores.presentation.ux.details

import com.vikingsen.inject.viewmodel.ViewModelInject
import kotlinx.coroutines.CoroutineScope
import pe.kreatec.stores.presentation.util.ui.scope.ioScope
import pe.kreatec.stores.presentation.util.ui.viewmodel.BaseViewModel

class StoreDetailsViewModel
@ViewModelInject constructor(

) : BaseViewModel<StoreDetailsViewModel.Event>(), CoroutineScope by ioScope() {

    sealed class Event {

    }

}