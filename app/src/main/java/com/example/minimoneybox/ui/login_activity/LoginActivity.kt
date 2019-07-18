package com.example.minimoneybox.ui.login_activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.example.minimoneybox.App
import com.example.minimoneybox.R
import com.example.minimoneybox.ui.user_account_activity.UserAccountActivity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity(), LoginView {


    private lateinit var btn_sign_in: Button
    private lateinit var til_email: TextInputLayout
    private lateinit var et_email: EditText
    private lateinit var til_password: TextInputLayout
    private lateinit var et_password: EditText
    private lateinit var til_name: TextInputLayout
    private lateinit var et_name: EditText
    private lateinit var pigAnimation: LottieAnimationView

    private lateinit var loginPresenter: LoginPresenter
    private var token: String = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        (applicationContext as App).getAppComponent().inject(this)

        setupViews()

        loginPresenter.bind(this)

    }

    override fun onStart() {
        super.onStart()
        setupAnimation()
    }

    @Inject
    fun getPresenter(loginPresenter: LoginPresenter) {
        this.loginPresenter = loginPresenter
    }

    private fun setupViews() {
        btn_sign_in = findViewById(R.id.btn_sign_in)
        til_email = findViewById(R.id.til_email)
        et_email = findViewById(R.id.et_email)
        til_password = findViewById(R.id.til_password)
        et_password = findViewById(R.id.et_password)
        til_name = findViewById(R.id.til_name)
        et_name = findViewById(R.id.et_name)
        pigAnimation = findViewById(R.id.animation)

        btn_sign_in.setOnClickListener {
            if (loginPresenter.allFieldsValid()) {
                loginPresenter.getToken()
            }
        }
    }

    /**
     * Setting up Animation
     * First animation start with 0 to 109
     * onAnimationEnd listener was used to find the animation end
     * When first stage of the animation finished it then loop from 131 to 158 continuously
     *  **/
    private fun setupAnimation() {
        pigAnimation.setMinAndMaxFrame(0, 109)
        pigAnimation.playAnimation()
        pigAnimation.addAnimatorListener(object : AnimatorListenerAdapter() {

            override fun onAnimationEnd(animation: Animator) {
                pigAnimation.setMinAndMaxFrame(131, 158)
                pigAnimation.repeatMode
                pigAnimation.playAnimation()
            }
        })
    }

    companion object {
        val EMAIL_REGEX = "[^@]+@[^.]+\\..+"
        val NAME_REGEX = "[a-zA-Z]{5,30}"
        val PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[A-Z]).{10,50}$"
        val NAME = "name"
        val TOKEN_ID = "token_id"
    }

    override fun getEmail(): String {
        return et_email.text.toString()
    }

    override fun getPassword(): String {
        return et_password.text.toString()
    }

    override fun getFullName(): String {
        return et_name.text.toString()
    }

    override fun setTillEmailError() {
        til_email.error = getString(R.string.email_address_error)
    }

    override fun setTillPasswordError() {
        til_password.error = getString(R.string.password_error)
    }

    override fun setTillNameError() {
        til_name.error = getString(R.string.full_name_error)
    }

    override fun setEmailEmptyError() {
        til_email.error = getString(R.string.email_empty_error)
    }

    override fun setPasswordEmptyError() {
        til_password.error = getString(R.string.password_empty_error)
    }

    override fun setEmailErrorNull() {
        til_email.error = null
    }

    override fun setPasswordErrorNull() {
        til_password.error = null
    }

    override fun setNameErrorNull() {
        til_name.error = null
    }

    /**
     * Method to start UserAccountActivity
     * Pass name of the user and token to UserAccountActivity
     * **/
    override fun startUserAccountActivity() {
        val intent = Intent(this, UserAccountActivity::class.java)
        val name = et_name.text.toString()

        intent.putExtra(NAME, name)
        intent.putExtra(TOKEN_ID, token)
        startActivity(intent)
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun setToken(token: String) {
        this.token = token
    }

    override fun onDestroy() {
        super.onDestroy()
        loginPresenter.unbind()
        loginPresenter.dispose()
    }


}
