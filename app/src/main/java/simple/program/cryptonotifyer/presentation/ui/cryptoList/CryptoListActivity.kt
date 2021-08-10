package simple.program.cryptonotifyer.presentation.ui.cryptoList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import simple.program.cryptonotifyer.R
import simple.program.cryptonotifyer.databinding.ActivityMainBinding
import simple.program.data.api.services.CoinGeckoApi
import simple.program.data.util.networkRequestWrapper
import simple.program.domain.model.GenericData
import simple.program.domain.model.UserSession
import javax.inject.Inject

@AndroidEntryPoint
class CryptoListActivity : AppCompatActivity() {

    @Inject
    lateinit var retrofit: Retrofit
    @Inject
    lateinit var coinGeckoApi: CoinGeckoApi

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.IO) {
            val a = coinGeckoApi.showProfile("cryptoblades")
            if (a.isSuccessful) {
                binding.tvTest.text = a.body()?.market_data?.current_price?.usd.toString()
            }
            Log.d("test", a.toString())
//             networkRequestWrapper(
//                fetch = {
//                    coinGeckoApi.showProfile("cryptoblades")
//                },
//                responseToDomain = {
//                    Log.d("test", it.toString())
//                    it
//                }
//            )
        }
    }

}