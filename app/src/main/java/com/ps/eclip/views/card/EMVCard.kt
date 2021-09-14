package com.ps.eclip.views.card

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.ps.eclip.R
import com.ps.eclip.databinding.EmvCardBinding

class EMVCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAtr: Int = 0
) : FrameLayout(context, attrs, defStyleAtr), View.OnClickListener {

    private val binding = EmvCardBinding.inflate(LayoutInflater.from(context), this, true)

    private var flipCount = 0

    init {
        showFlipSwitch(true)
    }

    private fun flipCard(strRes: Int, vararg rotations: Float) {
        val flipper = binding.cardFlipper
        val anim1 = ObjectAnimator.ofFloat(flipper, "rotationY", rotations[0], rotations[1])
        val anim2 = ObjectAnimator.ofFloat(flipper, "rotationY", rotations[2], rotations[3])

        anim1.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                flipper.showNext()
                anim2.start()
            }
        })

        anim2.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                binding.showCvv.isEnabled = true
                binding.showCvv.setText(strRes)
            }
        })

        anim1.start()
    }

    fun showFlipSwitch(show: Boolean) {
        if (show) {
            binding.showCvv.visibility = VISIBLE
            binding.showCvv.setOnClickListener(this)
        } else {
            binding.showCvv.visibility = INVISIBLE
            binding.showCvv.setOnClickListener(null)
        }
    }

    companion object {
        private const val TAG = "EMVCard"
    }

    override fun onClick(v: View?) {
        binding.showCvv.isEnabled = false
        val flipper = binding.cardFlipper
        if (flipCount == 0) {
            flipper.cameraDistance = flipper.cameraDistance * 4
        }

        if (flipCount % 2 == 0) {
            flipCard(R.string.hide_cvv, 0f, 90f, -90f, 0f)
        } else {
            flipCard(R.string.show_cvv, 0f, -90f, 90f, 0f)
        }
        flipCount++
    }
}