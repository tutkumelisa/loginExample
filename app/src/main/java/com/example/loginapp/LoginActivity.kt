package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var loginButton: Button
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //Geri tuşunu etkinleştirir (action bar'da görünmesini sağlar).
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        // View'leri burada bağlayın
        loginButton = findViewById(R.id.loginButton)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

        // Firebase Authentication başlatma, Firebase üzerinden giriş yapmak için bu nesneye ihtiyacımız var.
        mAuth = FirebaseAuth.getInstance()

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(
                    this@LoginActivity,
                    "Lütfen tüm alanları doldurun.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            //Firebase ile giriş işlemi
            loginUser(email, password)
        }
    }

    private fun loginUser(email: String, password: String) {
        // aşağıda, mAuth kullanılarak Firebase Authentication üzerinden e-posta ve şifreyle giriş yapılır.
        mAuth!!.signInWithEmailAndPassword(email, password)
                //işlem tamamlandıktan sonra ne yapılcağı belirlenir
            .addOnCompleteListener(this) { task: Task<AuthResult?> ->
                if (task.isSuccessful) {
                    // Giriş başarılı
                    val user = mAuth!!.currentUser
                    Toast.makeText(
                        this@LoginActivity,
                        "Hoşgeldiniz ${user!!.email}",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Ana ekrana yönlendirme veya bir sonraki aktiviteye geçiş yapabilirsiniz
                } else {
                    // Giriş başarısız
                    Toast.makeText(
                        this@LoginActivity,
                        "Giriş başarısız. Lütfen tekrar deneyin.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    // Geri tuşu işlemi
    override fun onBackPressed() {
        FirebaseAuth.getInstance().signOut()
        super.onBackPressed()
        // Çıkış yaptıktan sonra MainActivity'ye yönlendirebilirsiniz
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed() // Geri tuşuna basıldığında önceki ekrana dön
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
