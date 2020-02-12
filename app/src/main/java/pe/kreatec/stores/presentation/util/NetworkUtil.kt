package pe.kreatec.stores.presentation.util

import android.Manifest
import android.Manifest.permission
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.annotation.RequiresPermission
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NetworkUtil
@Inject constructor(
    private val context: Context
) {

    @RequiresPermission(permission.ACCESS_NETWORK_STATE)
    fun isConnected(): Boolean {
        val info = getNetworkInfo(context)
        return info != null && info.isConnected
    }

    @RequiresPermission(permission.ACCESS_NETWORK_STATE)
    fun getNetworkInfo(context: Context): NetworkInfo? {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo
    }

}