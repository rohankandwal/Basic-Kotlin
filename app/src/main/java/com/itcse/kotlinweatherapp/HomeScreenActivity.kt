package com.itcse.kotlinweatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.itcse.kotlinweatherapp.model.Repository
import com.itcse.kotlinweatherapp.model.data.WeatherData
import kotlinx.android.synthetic.main.activity_home_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val repository = Repository();


        // Press the done button on hardware keyboard on emulator or your device
        etZipCode.setOnEditorActionListener(object: TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    getTemperatureData(repository)
                    return true
                }
                return false
            }
        })
    }

    private fun getTemperatureData(repository: Repository) {
        repository.getApiInterface()
                .getWeatherData("${etZipCode.text},in",
                        "metric",
                        "78b516ad06f6b62c99cad28503fef96e")
                .enqueue(object : Callback<WeatherData> {
                    override fun onFailure(call: Call<WeatherData>?, t: Throwable?) {
                        t?.printStackTrace()
                    }

                    override fun onResponse(call: Call<WeatherData>?, response: Response<WeatherData>?) {
                        val weatherData: WeatherData? = response?.body();
                        weatherData?.let {
                            it.main?.let {
                                tvCurrentTemp.text = it.temp
                                tvHighestTemp.text = it.temp_max
                                tvLowestTemp.text = it.temp_min
                            }

                        }
                    }
                });
    }
}
