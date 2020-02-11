package pe.kreatec.stores.data.prefs

import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Prefs @Inject constructor() : PrefsContainer(COMMON_NAMESPACE) {
    var developerMode by SharedPref(false, key = PREF_DEVELOPER_MODE)
    private var internalAppInstanceId by SharedPref("", key = PREF_APP_INSTANCE_ID)
    var isFirstTime by SharedPref(true, key = PREF_IS_FIRST_TIME)

    @Synchronized
    fun getAppInstanceId(): String {
        if (internalAppInstanceId.isBlank()) {
            internalAppInstanceId = UUID.randomUUID().toString()
        }
        return internalAppInstanceId
    }

    companion object {
        private const val PREF_DEVELOPER_MODE = "developer_mode"
        private const val PREF_APP_INSTANCE_ID = "app_instance_id"
        private const val PREF_IS_FIRST_TIME = "is_first_time"
    }
}

