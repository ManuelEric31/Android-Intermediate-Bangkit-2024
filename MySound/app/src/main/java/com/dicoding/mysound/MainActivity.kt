package com.dicoding.mysound

import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var sp: SoundPool
    private var soundId: Int = 0
    private var spLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSound = findViewById<Button>(R.id.btn_sound_pool)

        btnSound.setOnClickListener{
            if (spLoaded) {
                sp.play(soundId, 1f, 1f, 0, 1, 1f)
            }
        }

        sp = SoundPool.Builder()
            .setMaxStreams(10)
            .build()

        sp.setOnLoadCompleteListener{_,_, status ->
            if (status == 0) {
                spLoaded = true
            } else {
                Toast.makeText(this@MainActivity, "Gagal load", Toast.LENGTH_SHORT).show()
            }
        }

        soundId = sp.load(this, R.raw.padded_walls, 1)
    }
}