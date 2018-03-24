package com.goodlife.bullandcow.view.home

import android.content.Intent
import android.os.Bundle
import com.goodlife.bullandcow.R
import com.goodlife.bullandcow.view.base.BaseActivity
import com.goodlife.bullandcow.view.game.GameActivity
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPlayNow.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }
    }
}
