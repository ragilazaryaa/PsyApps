package com.example.psyapps.register


import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.psyapps.login.LoginActivity
import com.example.psyapps.data.UserAuth
import com.example.psyapps.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        setupView()
        binding.etEmailRegister.validateInput(0)
        binding.etPwRegister.validateInput(1)
        binding.etUsername.validateInput(2)
        playAnimation()
        validateregister()

    }

    private fun validateregister() {
        binding.btnRegister.setOnClickListener{
            val email = binding.etEmailRegister
            val password = binding.etPwRegister
            val username = binding.etUsername

            if (email.text.toString().isEmpty() || password.text.toString().isEmpty() || username.text.toString().isEmpty()){
                Toast.makeText(this, "pastikan username, email, dan password sudah terisi!", Toast.LENGTH_SHORT).show()
            }

            if (email.isEmailValid && password.isPasswordValid && username.isUsernameValid){
                showLoading(true)
                viewModel.postRegister( UserAuth(username.text.toString(), email.text.toString(), password.text.toString()))
                viewModel.registerresponse.observe(this){response ->
                    Log.d("TAG", "Hasil Register : $response")
                    if (response.error == false) {
                        showLoading(false)
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                        finish()
                    } else {
                        showLoading(false)
                        Toast.makeText(this, response?.message ?: "Register Failed, try again!", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Invalid email, password, or username!", Toast.LENGTH_SHORT).show()
            }
        }
    }





    private fun showLoading(state : Boolean){
        if(state){
            binding.pbRegister.visibility = View.VISIBLE
        }else{
            binding.pbRegister.visibility = View.GONE
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
        ObjectAnimator.ofFloat(binding.ivLogo, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val title = ObjectAnimator.ofFloat(binding.tvTitle, View.ALPHA, 1f).setDuration(500)
        val tvEnterEmail = ObjectAnimator.ofFloat(binding.tvEnterEmail, View.ALPHA , 1f).setDuration(500)
        val tvUser = ObjectAnimator.ofFloat(binding.tvUsername , View.ALPHA , 1f ).setDuration(500)
        val etlUser = ObjectAnimator.ofFloat(binding.etlUsername , View.ALPHA , 1f).setDuration(500)
        val etUser = ObjectAnimator.ofFloat(binding.etUsername , View.ALPHA , 1f ).setDuration(500)
        val tvEmail = ObjectAnimator.ofFloat(binding.emailTextView , View.ALPHA , 1f).setDuration(500)
        val etlEmail = ObjectAnimator.ofFloat(binding.etlEmail , View.ALPHA, 1f).setDuration(500)
        val etEmail = ObjectAnimator.ofFloat(binding.etEmailRegister , View.ALPHA , 1f).setDuration(500)
        val tvpw = ObjectAnimator.ofFloat(binding.tvPw , View.ALPHA , 1f).setDuration(500)
        val etlpw = ObjectAnimator.ofFloat(binding.etlPw , View.ALPHA , 1f).setDuration(500)
        val etpw = ObjectAnimator.ofFloat(binding.etPwRegister , View.ALPHA , 1f).setDuration(500)
        val regis = ObjectAnimator.ofFloat(binding.btnRegister , View.ALPHA , 1f).setDuration(500)


        AnimatorSet().apply {
            playSequentially(
                title,
                tvEnterEmail,
                tvUser,
                etlUser,
                etUser,
                tvEmail,
                etlEmail,
                etEmail,
                tvpw,
                etlpw,
                etpw,
                regis
            )
            startDelay = 100
        }.start()
    }
}