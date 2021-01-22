package com.mqweather.android.weather.activities

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.*
import androidx.appcompat.app.AppCompatActivity
import com.mqweather.android.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private var increment = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        init()
        timer(1000, 100).start()
    }

    private fun init() {
        val fadeIn: Animation = AlphaAnimation(0f, 1f)
        fadeIn.interpolator = DecelerateInterpolator() //add this

        fadeIn.duration = 1000

        val fadeOut: Animation = AlphaAnimation(1f, 0f)
        fadeOut.interpolator = AccelerateInterpolator() //and this

        fadeOut.startOffset = 1000
        fadeOut.duration = 1000

        val animation = AnimationSet(false) //change to false

        animation.addAnimation(fadeIn)
        animation.addAnimation(fadeOut)
        //starting the animation
        splash_help_text.animation = (animation)
    }

    // Method to configure and return an instance of CountDownTimer object
    private fun timer(millisInFuture: Long, countDownInterval: Long): CountDownTimer {
        return object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                increment += 10
            }

            override fun onFinish() {
                goToNextScreen()
            }
        }
    }

    private fun goToNextScreen() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}