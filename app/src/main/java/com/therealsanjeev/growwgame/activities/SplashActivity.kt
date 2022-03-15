package com.therealsanjeev.growwgame.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.therealsanjeev.growwgame.R
import com.therealsanjeev.growwgame.databinding.ActivitySplashBinding
import kotlinx.coroutines.*

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private val binding:ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val scope = CoroutineScope(Job() + Dispatchers.Main)

        scope.launch {
            delay(3000)

                startActivity(
                    Intent(this@SplashActivity, MainActivity::class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                )
                finish()
        }
    }
}