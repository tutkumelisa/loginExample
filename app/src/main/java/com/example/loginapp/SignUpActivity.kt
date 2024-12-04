package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signUpButton: Button
    //mAuth değişkeni, Firebase Authentication'ı kullanarak kullanıcı doğrulaması yapmak için kullanılır.
    // FirebaseAuth.getInstance() metodu, Firebase Authentication'ın bir örneğini alır.
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // View'leri bağlama
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        signUpButton = findViewById(R.id.signUpButton)

        // Buton için Click Listener
        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Alanların boş olup olmadığını kontrol et
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Lütfen tüm alanları doldurun.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Kullanıcı kaydını başlat
            registerUser(email, password)
        }
    }

    //Firebase Authentication kullanarak kullanıcı kaydını yapar.
    private fun registerUser(email: String, password: String) {
        //Bu satır, Firebase Authentication'ı kullanarak verilen e-posta ve şifre ile yeni bir kullanıcı oluşturur
        mAuth.createUserWithEmailAndPassword(email, password)
            //Bu kod bloğu, kullanıcı kaydı tamamlandığında yapılacak işlemleri tanımlar.
            //Eğer işlem başarılıysa kullanıcıya bir başarı mesajı gösterilir, başarısızsa hata mesajı gösterilir.
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Kayıt başarılı ise mesaj göster
                    //Toast, Android uygulamalarında kullanıcıya kısa süreli mesajlar göstermek için kullanılan bir bileşendir.
                    //Toast mesajları, genellikle kullanıcı etkileşimlerinin hemen ardından, uygulamanın ekranında kısa süreli olarak görünür ve otomatik olarak kaybolur.
                    Toast.makeText(this, "Kullanıcı başarıyla kaydedildi.", Toast.LENGTH_SHORT).show()
                    // İsteğe bağlı olarak, LoginActivity'ye yönlendirme yapılabilir
                    // startActivity(Intent(this, LoginActivity::class.java))
                } else {
                    // Kayıt başarısız ise hata mesajı göster
                    Toast.makeText(this, "Kayıt başarısız. Lütfen tekrar deneyin.", Toast.LENGTH_SHORT).show()
                }
            }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        // MainActivity'ye geri dönmek için
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
