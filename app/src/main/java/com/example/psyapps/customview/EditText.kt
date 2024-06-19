package com.example.storyapps.customview

import android.content.Context
import android.graphics.Canvas
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import android.view.View
import androidx.appcompat.widget.AppCompatEditText

class EditText : AppCompatEditText {

    var isEmailValid: Boolean = false
    var isPasswordValid: Boolean = false
    var isUsernameValid : Boolean = false

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
        val layoutParams = layoutParams
        layoutParams.height = 100
        setLayoutParams(layoutParams)
        setPadding(10, 0, 10, 0)

    }

    // 0 : email, 1 : password, 2 : username
    fun validateInput(inputType: Int) {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                val inputText = s.toString().trim()
                when (inputType) {
                    0 -> validateEmail(inputText)
                    1 -> validatePassword(inputText)
                    2 -> validateUsername(inputText)
                }
            }
        })
    }

    fun validateEmail(email: String) {

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()) {
            error = "Invalid Email"
            isEmailValid = false

        } else if (email.isEmpty()) {
            isEmailValid = false
            error = "Email can't be empty"
        } else {
            isEmailValid = true
        }

    }

    fun validatePassword(password : String) {

        if (password.isEmpty()) {
            error = "Password Can't be empty"
            isPasswordValid = false
        } else if (password.length in 1..7) {
            error = "Password must at least 8 chars"
            isPasswordValid = false
        } else {
            isPasswordValid = true
        }
    }
    fun validateUsername(username : String){
        if (username.isEmpty()) {
            error = "Username Can't be empty"
            isUsernameValid = false
        }else  {
            isUsernameValid = true
        }
    }
}
