package com.example.estacionamientotp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.estacionamientotp.databinding.ActivityRegistrarseBinding

class Registrarse_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityRegistrarseBinding=DataBindingUtil.setContentView(this,R.layout.activity_registrarse)

    }
}