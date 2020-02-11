package pe.kreatec.stores.presentation.ux

import com.vikingsen.inject.viewmodel.ViewModelInject
import pe.kreatec.stores.presentation.util.ui.viewmodel.BaseViewModel
import pe.kreatec.stores.work.WorkScheduler

class MainViewModel
@ViewModelInject constructor(
    private val workScheduler: WorkScheduler
) : BaseViewModel<MainViewModel.Event>() {

    init {
        workScheduler.scheduleSyncWorkerRepeating()
    }

    sealed class Event {}

}