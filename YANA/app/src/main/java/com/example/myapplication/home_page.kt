package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import com.example.myapplication.databinding.ActivityHomePageBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class home_page : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    private var selectedFileUri: Uri? = null
    private val db = FirebaseFirestore.getInstance()
    private val realtimeDb = FirebaseDatabase.getInstance()
    private val storage = FirebaseStorage.getInstance()
    private lateinit var loadingDialog: AlertDialog

    private val pickFileResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            selectedFileUri = result.data?.data
            selectedFileUri?.let { uri ->
                val fileName = getFileName(uri)
                binding.tvFileName.text = fileName ?: "Unknown file"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pickDocument.setOnClickListener {
            pickFile()
        }

        binding.buttonSubmit.setOnClickListener {
            submitData()
        }
    }

    private fun pickFile() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*"
            val mimeTypes = arrayOf("application/pdf", "image/*")
            putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        }
        pickFileResultLauncher.launch(intent)
    }

    private fun submitData() {
        navigateToRideBook();
        val name = binding.name.text.toString().trim()
        val phone = binding.phone.text.toString().trim()
        val email = binding.email.text.toString().trim()
        val address = binding.address.text.toString().trim()
        val emergencyNumber = binding.enumber.text.toString().trim()

        if (selectedFileUri == null) {
            Toast.makeText(this, "Please select a file to upload", Toast.LENGTH_SHORT).show()
            return
        }

        showLoadingDialog()

        val fileRef = storage.reference.child("docs/${System.currentTimeMillis()}-${selectedFileUri?.lastPathSegment}")
        fileRef.putFile(selectedFileUri!!).addOnSuccessListener { taskSnapshot ->
            taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->
                val documentUri = uri.toString()
                addUserToRealtimeDb(name, phone, email, address, emergencyNumber, documentUri)
            }
        }.addOnFailureListener {
            loadingDialog.dismiss()
            Toast.makeText(this, "Failed to upload file", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addUserToRealtimeDb(name: String, phone: String, email: String, address: String, emergencyNumber: String, documentUri: String) {
        val user = User(name, phone, email, address, emergencyNumber, documentUri)
        val uniqueId = realtimeDb.reference.child("users").push().key

        uniqueId?.let {
            realtimeDb.reference.child("users").child(it).setValue(user).addOnCompleteListener { task ->
                loadingDialog.dismiss()
                if (task.isSuccessful) {
                    Toast.makeText(this, "Data stored successfully", Toast.LENGTH_SHORT).show()
                    navigateToRideBook()
                } else {
                    Toast.makeText(this, "Failed to store data", Toast.LENGTH_SHORT).show()
                }
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

    private fun getFileName(uri: Uri): String? {
        var name: String? = null
        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            it.moveToFirst()
            name = it.getString(it.getColumnIndexOrThrow(android.provider.OpenableColumns.DISPLAY_NAME))
        }
        return name
    }

    private fun navigateToRideBook() {
        // Navigate to another activity as intended
        val intent = Intent(this, Ridebook::class.java)
        startActivity(intent)
        finish()
    }
}



data class User(
    val name: String,
    val phone: String,
    val email: String,
    val address: String,
    val emergencyNumber: String,
    val documentUri: String
)
