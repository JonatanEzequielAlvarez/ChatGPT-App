package com.creardev.chatgptapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.creardev.chatgptapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private val chatViewModel: ChatViewModel by viewModels()// scope is it's activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        binding.tvResponse.isClickable = false
        binding.button.setOnClickListener(this)
        binding.progressCircular.isVisible = false
        setContentView(view)

    }

    private fun setData() {
        binding.progressCircular.isVisible = true

        val apiKey =
            "Bearer key" // Reemplaza con tu clave de API
        val question = binding.tVQuestion.text
        val messages = listOf(
            ChatMessage(
                "system",
                "puedes generarme una lista de las tareas realizadas de manera sencilla, en español, " +
                        "sin los encabezados, sin los numeros de las tareas, con salto de linea por tarea, " +
                        "en un lenguaje simple, por favor"
            ),
            ChatMessage("user", question.toString())
        )

        chatViewModel.getChatCompletion(apiKey, messages)

        chatViewModel.chatResponse.observe(this) {
            println(it)
            binding.tvResponse.text = it
            binding.progressCircular.isVisible = false
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.button -> setData()
        }
    }
}
