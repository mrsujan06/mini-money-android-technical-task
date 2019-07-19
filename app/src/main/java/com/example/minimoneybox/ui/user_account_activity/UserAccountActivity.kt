package com.example.minimoneybox.ui.user_account_activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.example.minimoneybox.App
import com.example.minimoneybox.R
import com.example.minimoneybox.model.user_account_detail.ProductResponse
import com.example.minimoneybox.ui.individualAccount.IndividualAccountActivity
import com.example.minimoneybox.ui.login_activity.LoginActivity
import javax.inject.Inject

class UserAccountActivity : AppCompatActivity(), UserAccountView, ProductAdapter.OnProductClickListener {

    private lateinit var greeting: TextView
    private lateinit var totalPlanValue: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var presenter: UserAccountPresenter
    private lateinit var name: String
    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_account)

        (applicationContext as App).getAppComponent().inject(this)
        initViews()

        name = intent.getStringExtra(LoginActivity.NAME)
        token = intent.getStringExtra(LoginActivity.TOKEN_ID)

        updateGreeting()
        initRecyclerView()

        presenter.bind(this)
        presenter.getUserData(token)

    }

    @Inject
    fun getPresenter(presenter: UserAccountPresenter) {
        this.presenter = presenter
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        greeting = findViewById(R.id.greeting_tv)
        totalPlanValue = findViewById(R.id.total_plan_value)
    }

    private fun initRecyclerView() {
        productAdapter = ProductAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = productAdapter
    }

    override fun updateGreeting() {
        val greet = "Hello "
        greeting.text = greet.plus(name)
    }

    override fun updateTotalPlanValue(totalPlanValue: String) {
        this.totalPlanValue.text = totalPlanValue
    }

    override fun updateUserData(userData: List<ProductResponse>) {
        productAdapter.setProduct(userData)
    }

    override fun onProductClick(position: Int, product: MutableList<ProductResponse>) {

        val name = product[position].Product.FriendlyName
        val planValue = product[position].PlanValue.toString()
        val moneyboxValue = product[position].Moneybox
        val id = product[position].Id

        val intent = Intent(this, IndividualAccountActivity::class.java)
        intent.putExtra(FRIENDLY_NAME, name)
        intent.putExtra(PLAN_VALUE, planValue)
        intent.putExtra(MONEYBOX, moneyboxValue)
        intent.putExtra(ID, id)
        intent.putExtra(BEARERTOKEN, token)

        startActivity(intent)
    }

    companion object {
        const val FRIENDLY_NAME = "name"
        const val PLAN_VALUE = "plan_value"
        const val MONEYBOX = "moneybox_value"
        const val ID = "id"
        const val BEARERTOKEN = "bearertoken"
    }
    
    override fun onRestart() {
        super.onRestart()
        initRecyclerView()
        presenter.getUserData(token)}

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
        presenter.dispose()
    }

}
