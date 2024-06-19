package com.example.psyapps.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.psyapps.data.UserModel
import com.example.psyapps.databinding.ActivityLoginBinding
import com.example.psyapps.home.HomeActivity
import com.example.psyapps.preferences.SharedPreferences
import com.example.psyapps.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val signUp = binding.tvRegis
        sharedPreferences = SharedPreferences(this)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        setupView()
        binding.etEmailLogin.validateInput(0)
        binding.etPwLogin.validateInput(1)

        signUp.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        validatelogin()
        playAnimation()
    }

    private fun validatelogin() {
        binding.loginButton.setOnClickListener{
            val email = binding.etEmailLogin
            val password = binding.etPwLogin

            if(email.text.toString().isEmpty() || email.text.toString().isEmpty()){
                Toast.makeText(this, "Pastikan anda telah mengisi input email dan password!", Toast.LENGTH_SHORT).show()
            }
            if (email.isEmailValid && password.isPasswordValid){
                showLoading(true)
                viewModel.postLogin(UserModel(email.text.toString(), password.text.toString()))
                viewModel.loginResponse.observe(this){ response ->
                    if (response.error == false){
                        showLoading(false)
                        response.loginResult?.let {
                            result ->
                            sharedPreferences.saveToken(
                                result.token.toString(),
                                result.username.toString(), email.text.toString())
                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }

                    } else {
                        showLoading(false)
                        Toast.makeText(this, "Login Failed. Try Again!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
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

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.ivLogin, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val title = ObjectAnimator.ofFloat(binding.tvTitle, View.ALPHA, 1f).setDuration(500)
        val desc = ObjectAnimator.ofFloat(binding.tvDesc, View.ALPHA, 1f).setDuration(500)
        val email = ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(500)
        val etlEmail = ObjectAnimator.ofFloat(binding.etlEmail, View.ALPHA, 1f).setDuration(500)
        val etEmail = ObjectAnimator.ofFloat(binding.etEmailLogin, View.ALPHA, 1f).setDuration(500)
        val textPw = ObjectAnimator.ofFloat(binding.tvPw, View.ALPHA, 1f).setDuration(500)
        val etlPw = ObjectAnimator.ofFloat(binding.etlPw, View.ALPHA, 1f).setDuration(500)
        val etPw = ObjectAnimator.ofFloat(binding.etPwLogin, View.ALPHA, 1f).setDuration(500)
        val btn = ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(500)
        val tvDoc = ObjectAnimator.ofFloat(binding.tvLoginDoctor, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(
                title,
                desc,
                email,
                etlEmail,
                etEmail,
                textPw,
                etlPw,
                etPw,
                btn,
                tvDoc
            )
            startDelay = 100
        }.start()
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.pbProgressBar.visibility = View.VISIBLE
        } else {
            binding.pbProgressBar.visibility = View.GONE
        }
    }
}