package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
     //lateinit anahtar kelimesi, bu değişkenlerin daha sonra başlatılacağını belirtir.
    // Yani bu değişkenler onCreate metodu içerisinde bağlanacaktır.
    private lateinit var signUpButton: Button
    private lateinit var signInButton: Button

    //onCreate metodu, Android uygulamasında bir aktivite başlatıldığında ilk olarak çağrılan metottur.
    // Uygulama ilk açıldığında yapılacak tüm işlemler burada tanımlanır.
    override fun onCreate(savedInstanceState: Bundle?) {

        //savedInstanceState parametresi, aktivite yeniden başlatıldığında (örneğin, ekran döndüğünde)
        //önceki durumu geri yüklemek için kullanılır. Ancak burada kullanılmıyor, yalnızca bir işaretçi olarak var.
        super.onCreate(savedInstanceState)
       //Bu satır, aktivite için gösterilecek olan arayüzün (UI) belirlenmesini sağlar.
        // Burada, activity_main.xml dosyasındaki arayüz bileşenlerini kullanarak bu aktiviteyi yapılandırıyoruz.
        setContentView(R.layout.activity_main)

        //findViewById metodu, XML dosyasındaki bir görünümü (View) Java/Kotlin kodunda kullanabilmek için bulmamızı sağlar.
        // Bu satır, activity_main.xml dosyasındaki signUpButton id'sine sahip butonu signUpButton değişkenine bağlar.
        // View'leri bağlayın
        signUpButton = findViewById(R.id.signUpButton)
        signInButton = findViewById(R.id.signInButton)

        // Sign Up butonuna tıklama -> SignUpActivity'e yönlendir
        signUpButton.setOnClickListener {
            //Bu satır, SignUpActivity aktivitesine yönlendirecek bir Intent nesnesi oluşturur.
            // Intent, aktiviteler arasında geçiş yapmak için kullanılır.
            // this, şu anki MainActivity'yi belirtir. SignUpActivity::class.java, yönlendirilecek aktiviteyi belirtir.
            val intent = Intent(this, SignUpActivity::class.java)
            //Bu satır, önceki satırda oluşturulan Intent nesnesini kullanarak SignUpActivity'yi başlatır.
            startActivity(intent)
        }

        // Sign In butonuna tıklama
        signInButton.setOnClickListener {
            // LoginActivity'e yönlendir
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
