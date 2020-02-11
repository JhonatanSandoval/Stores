package pe.kreatec.stores.presentation.ux

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.vikingsen.inject.viewmodel.savedstate.SavedStateViewModelFactory
import pe.kreatec.stores.R
import pe.kreatec.stores.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.mainNavigationFragment) }

    @Inject lateinit var viewModelFactory: SavedStateViewModelFactory.Factory
    private val viewModel by viewModels<MainViewModel> { viewModelFactory.create(this) }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()

}
