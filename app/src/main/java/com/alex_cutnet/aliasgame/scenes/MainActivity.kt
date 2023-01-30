package com.alex_cutnet.aliasgame.scenes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alex_cutnet.aliasgame.R
import com.alex_cutnet.aliasgame.scenes.fragments.AddTeamFragment
import com.alex_cutnet.aliasgame.scenes.fragments.GameFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}