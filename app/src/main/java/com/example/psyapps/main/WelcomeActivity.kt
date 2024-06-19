package com.example.psyapps.main

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.psyapps.home.HomeActivity
import com.example.psyapps.databinding.ActivityWelcomeBinding
import com.example.psyapps.login.LoginActivity
import com.example.psyapps.preferences.SharedPreferences

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var token : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = SharedPreferences(this)
        token = sharedPreferences.getToken()
        checkToken()
    }
    private fun checkToken() {
        if (!token.isNullOrEmpty()){
            startActivity(Intent(this@WelcomeActivity, HomeActivity::class.java))
            finish()
        }
        else{
            setupView()
            setupAction()
            playAnimation()

        }
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.btnContinue.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.ivLogo, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val ivTitle = ObjectAnimator.ofFloat(binding.tvTitle, View.ALPHA, 1f).setDuration(100)
        val ivLogo = ObjectAnimator.ofFloat(binding.ivLogo, View.ALPHA, 1f).setDuration(100)
        val tvdesc = ObjectAnimator.ofFloat(binding.tvMsg, View.ALPHA, 1f).setDuration(100)
        val tvStart = ObjectAnimator.ofFloat(binding.tvStarted, View.ALPHA, 1f).setDuration(100)
        val btn= ObjectAnimator.ofFloat(binding.btnContinue, View.ALPHA, 1f).setDuration(100)

        val together = AnimatorSet().apply {
            playTogether(ivTitle, tvdesc , tvStart , btn)
        }

        AnimatorSet().apply {
            playSequentially( ivLogo, together)
            start()
        }
    }
}