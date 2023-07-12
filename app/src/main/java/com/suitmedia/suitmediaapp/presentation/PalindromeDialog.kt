package com.suitmedia.suitmediaapp.presentation

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class PalindromeDialog: DialogFragment() {
    companion object {
        @JvmStatic
        fun newInstance(isPalindrome: Boolean) = PalindromeDialog().apply {
            arguments = Bundle().apply {
                putBoolean("isPalindrome", isPalindrome)
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val isPalindrome = arguments?.getBoolean("isPalindrome")
        val builder = AlertDialog.Builder(requireContext())
            .setMessage(
                if (isPalindrome == true) {
                    "Is Palindrome"
                } else {
                    "Not Palindrome"
                }
            )
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
        return builder.create()
    }
}