package pe.kreatec.stores.presentation.ux.stores

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import pe.kreatec.stores.R
import pe.kreatec.stores.databinding.ItemStoreBinding
import pe.kreatec.stores.domain.model.Store
import pe.kreatec.stores.presentation.util.ext.inflater
import pe.kreatec.stores.presentation.util.ui.list.BindingViewHolder

class StoresAdapter(
    private val storesViewModel: StoresViewModel
) : RecyclerView.Adapter<StoresAdapter.StoreHolder>() {

    var stores = listOf<Store>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreHolder = StoreHolder(parent)

    override fun onBindViewHolder(holder: StoreHolder, position: Int) {
        val store = getItem(position) ?: return
        holder.binding.store = store
        holder.binding.storesViewModel = storesViewModel
    }

    private fun getItem(position: Int): Store? = stores[position]
    override fun getItemCount(): Int = stores.size

    class StoreHolder(
        parent: ViewGroup,
        val binding: ItemStoreBinding = DataBindingUtil.inflate(parent.inflater(), R.layout.item_store, parent, false)
    ) : BindingViewHolder<ItemStoreBinding>(binding.root, {})

}
