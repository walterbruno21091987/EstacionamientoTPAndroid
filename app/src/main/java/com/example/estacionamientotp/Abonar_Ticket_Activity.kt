package com.example.estacionamientotp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.estacionamientotp.databinding.ActivityAbonarTicketBinding

class Abonar_Ticket_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityAbonarTicketBinding=DataBindingUtil.setContentView(this,R.layout.activity_abonar_ticket)
 binding.btPagar.setOnClickListener {


 }

    }
}