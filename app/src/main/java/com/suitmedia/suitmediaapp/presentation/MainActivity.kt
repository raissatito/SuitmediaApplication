package com.suitmedia.suitmediaapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.suitmedia.suitmediaapp.R
import com.suitmedia.suitmediaapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        binding.btnCheck.setOnClickListener {
            val text = binding.edPalindrome.text.toString()
            val isPalindrome = checkPalindrome(text)
            val dialog = PalindromeDialog.newInstance(isPalindrome)
            dialog.show(supportFragmentManager, "PalindromeDialog")
        }

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            val name = binding.edName.text.toString()
            intent.putExtra("name", name)
            startActivity(intent)
        }
    }

    private fun checkPalindrome(text: String): Boolean {
        val formattedText = text.split(" ").joinToString("").lowercase()
        val reversedText = formattedText.reversed()
        return formattedText == reversedText
    }
}