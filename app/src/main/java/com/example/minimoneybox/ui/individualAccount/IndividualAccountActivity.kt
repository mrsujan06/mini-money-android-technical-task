package com.example.minimoneybox.ui.individualAccount

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.minimoneybox.App
import com.example.minimoneybox.R
import com.example.minimoneybox.ui.user_account_activity.UserAccountActivity
import javax.inject.Inject

class IndividualAccountActivity : AppCompatActivity(), IndividualAccountView {

    private lateinit var account_name: TextView
    private lateinit var plan_value: TextView
    private lateinit var moneybox: TextView
    private lateinit var name: String
    private lateinit var planValue: String
    private lateinit var addAmountButton: Button
    private lateinit var token: String
    private var Id: Int = 0
    private var moneyboxValue: Int = 0
    private val poundSign = "£"

    private lateinit var presenter: IndividualAccountPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_account)

        (applicationContext as App).getAppComponent().inject(this)

        presenter.bind(this)

        setupViews()
        getIncomingIntents()
        updateUI()

        addAmountButton.setOnClickListener {
            presenter.updateAmount()
        }

    }

    /**
     * Initializing views
     * **/
    private fun setupViews() {
        account_name = findViewById(R.id.account_name)
        plan_value = findViewById(R.id.plan_value_in)
        moneybox = findViewById(R.id.moneybox_in)
        addAmountButton = findViewById(R.id.add_button)
    }

    /**
     * Telling dagger to inject a presenter to Individual Account
     * using setPresenter
     * **/
    @Inject
    fun setPresenter(presenter: IndividualAccountPresenter) {
        this.presenter = presenter
    }

    /**
     * Getting incoming intents from UserAccountActivity
     * **/
    private fun getIncomingIntents() {

        if (intent.getStringExtra(UserAccountActivity.FRIENDLY_NAME) != null && 
            intent.getStringExtra(UserAccountActivity.PLAN_VALUE) != null &&
            intent.getStringExtra(UserAccountActivity.BEARERTOKEN) != null)
        {
            name = intent.getStringExtra(UserAccountActivity.FRIENDLY_NAME)
            planValue = intent.getStringExtra(UserAccountActivity.PLAN_VALUE)
            token = intent.getStringExtra(UserAccountActivity.BEARERTOKEN)
            moneyboxValue = intent.getIntExtra(UserAccountActivity.MONEYBOX, 0)
            Id = intent.getIntExtra(UserAccountActivity.ID, 0)
        }
    }

    private fun updateUI() {
        account_name.text = name
        plan_value.text = poundSign.plus(planValue)
        moneybox.text =  poundSign.plus(moneyboxValue.toString())
    }

    override fun updateMoneyboxAmount(value: Int) {
        moneybox.text = poundSign.plus(value)
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun getId(): Int {
        return Id
    }

    override fun getToken(): String {
        return token
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
        presenter.dispose()
    }
}
