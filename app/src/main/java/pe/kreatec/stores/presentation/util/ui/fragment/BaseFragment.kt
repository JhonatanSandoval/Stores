package pe.kreatec.stores.presentation.util.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    protected lateinit var binding: T
    protected abstract fun setUpViewModel()
    protected abstract val layout: Int

    protected fun bindView(inflater: LayoutInflater, parent: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, layout, parent, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
    }
}
