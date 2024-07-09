package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityOtpBinding
import com.google.firebase.auth.*

class otp : AppCompatActivity() {
    private lateinit var binding: ActivityOtpBinding
    private var storeVerificationId: String? = ""
    private lateinit var auth: FirebaseAuth
    private lateinit var loadingDialog: AlertDialog
    private var resendToken: PhoneAuthProvider.ForceResendingToken? = null
    private lateinit var countDownTimer: CountDownTimer
    private val countdownPeriod: Long = 60000 // 60 seconds
    private val countdownInterval: Long = 1000 // 1 second

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        storeVerificationId = intent.getStringExtra("storedVerificationId")
        resendToken = intent.getParcelableExtra("resendToken")

        binding.btnverify.setOnClickListener {
            val otpInput = binding.otpinput.text.toString().trim()
            if (otpInput.isEmpty()) {
                showDialog("Error", "Please input your OTP")
                return@setOnClickListener
            }
            verifyPhoneNumberWithCode(storeVerificationId, otpInput)
        }

        setupResendOtp()
        startCountdownTimer()
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        showLoadingDialog("Verifying...")
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                loadingDialog.dismiss()
                if (task.isSuccessful) {
                    Log.d("success", "signInWithCredential:success")
                    val intent = Intent(this@otp, home_page::class.java)
                    startActivity(intent)
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        showDialog("Error", "OTP not match. Please try again.")
                    } else {
                        Log.w("failed", "signInWithCredential:failure", task.exception)
                    }
                }
            }
    }

    @SuppressLint("MissingInflatedId")
    private fun showLoadingDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.loading_dialog, null)
        view.findViewById<TextView>(R.id.loadingTextView).text = message
        builder.setView(view)
        builder.setCancelable(false)
        loadingDialog = builder.create()
        loadingDialog.show()
    }

    private fun showDialog(title: String, message: String) {
        AlertDialog.Builder(this).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton("OK", null)
        }.show()
    }

    private fun setupResendOtp() {
        binding.btnResendOtp.setOnClickListener {
            // Implement resend OTP functionality here, using resendToken if necessary
            startCountdownTimer() // Restart countdown when OTP is resent
        }
        binding.btnResendOtp.isEnabled = false // Initially, disable the resend button
    }

    private fun startCountdownTimer() {
        countDownTimer = object : CountDownTimer(countdownPeriod, countdownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                binding.timerText.visibility = View.VISIBLE
                binding.timerText.text = "Resend available in: ${millisUntilFinished / 1000} sec"
                binding.btnResendOtp.isEnabled = false
            }

            override fun onFinish() {
                binding.timerText.visibility = View.INVISIBLE
                binding.btnResendOtp.isEnabled = true
            }
        }.start()
    }
}
