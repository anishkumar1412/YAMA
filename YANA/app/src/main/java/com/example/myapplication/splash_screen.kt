package com.example.myapplication



import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView

class splashpage : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val imageView = findViewById<ImageView>(R.id.imgAnim)
        val scaleAnimation = ScaleAnimation(
            1.0f, 1.5f, 1.0f, 1.5f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
        )
        scaleAnimation.setDuration((ANIMATION_DURATION / 2).toLong())
        scaleAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                val reverseScaleAnimation = ScaleAnimation(
                    1.5f, 1.0f, 1.5f, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
                )
                reverseScaleAnimation.setDuration((ANIMATION_DURATION / 2).toLong())
                imageView.startAnimation(reverseScaleAnimation)
                Handler().postDelayed({
                    val iHome = Intent(applicationContext, MainActivity::class.java)
                    startActivity(iHome)
                    finish()
                }, (ANIMATION_DURATION / 2).toLong())
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        imageView.startAnimation(scaleAnimation)
    }

    companion object {
        private const val ANIMATION_DURATION = 5000
    }
}