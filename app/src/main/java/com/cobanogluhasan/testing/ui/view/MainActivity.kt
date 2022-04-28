package com.cobanogluhasan.testing.ui.view

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cobanogluhasan.testing.R
import com.cobanogluhasan.testing.util.Res
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: CustomFragmentFactory
    private var res: Res? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
        setContentView(R.layout.activity_main)
    }

    override fun getResources(): Resources? {
        if (res == null) {
            res = Res(super.getResources())
        }
        return res
    }
}