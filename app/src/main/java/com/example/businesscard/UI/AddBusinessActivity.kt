package com.example.businesscard.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.businesscard.App
import com.example.businesscard.Data.BusinessCard
import com.example.businesscard.R
import com.example.businesscard.databinding.ActivityAddBusinessBinding


class AddBusinessActivity : AppCompatActivity() {
    private val binding by lazy{ ActivityAddBusinessBinding.inflate(layoutInflater)}

    private  val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
    }

    private fun insertListeners() {
        binding.buttonClose.setOnClickListener {
            finish()
        }
        binding.buttonConfirmar.setOnClickListener {
            val businessCard = BusinessCard(
                nome = binding.tilNome.editText?.text.toString(),
                telefone = binding.tilTelefone.editText?.text.toString(),
                empresa = binding.tilEmpresa.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                fundoPersonalizado = binding.tilCor.editText?.text.toString()

            )
            mainViewModel.insert(businessCard)
            Toast.makeText(this, R.string.cadastro_com_sucesso, Toast.LENGTH_LONG).show()
            finish()
        }
    }

}