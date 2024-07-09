package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var loadingDialog: AlertDialog

    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                loadingDialog.dismiss()
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                loadingDialog.dismiss()
                Toast.makeText(applicationContext, "Verification failed: ${e.message}", Toast.LENGTH_LONG).show()
            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                loadingDialog.dismiss()
                storedVerificationId = verificationId
                resendToken = token
                val intent = Intent(this@MainActivity, otp::class.java).apply {
                    putExtra("storedVerificationId", storedVerificationId)
                }
                startActivity(intent)
            }
        }

        binding.btnsend.setOnClickListener {
            val phoneNumber = binding.phoneNumberEditText.text.toString()
            if (phoneNumber.isNotEmpty()) {
                startPhoneNumberVerification(phoneNumber)
            } else {
                Toast.makeText(this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        showLoadingDialog()

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+91$phoneNumber")
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    val intent = Intent(this@MainActivity, home_page::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun showLoadingDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        builder.setView(inflater.inflate(R.layout.loading_dialog, null))
        builder.setCancelable(false)
        loadingDialog = builder.create()
        loadingDialog.show()
    }

    companion object {
        private const val TAG = "PhoneAuthActivity"
    }
}
