package com.ps.eclip.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.ps.eclip.R
import com.ps.eclip.dao.AppDatabase
import com.ps.eclip.dao.AppExecutors
import com.ps.eclip.databinding.ActivityEnterDetailsBinding
import com.ps.eclip.enums.EMVCardType
import com.ps.eclip.enums.EmvCardScheme
import com.ps.eclip.models.EMVCard
import com.ps.eclip.models.EMVCardPreviewModel
import com.ps.eclip.utils.CardSchemeIdentifier

class EnterCardDetailsActivity : AppCompatActivity() {

    private val binding by lazy { ActivityEnterDetailsBinding.inflate(layoutInflater) }
    private val database by lazy { AppDatabase.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        window.isNavigationBarContrastEnforced = false

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        binding.forms.cardNumberLayout.editText?.addTextChangedListener {
            val scheme = CardSchemeIdentifier.match(it?.toString())
            updateCardIcon(scheme)
        }

        binding.fabAddCard.setOnClickListener {
            val card = getCardDetails()
            card?.let {
                saveCardDetails(card)
            }
        }
    }

    private fun updateCardIcon(scheme: EmvCardScheme) {
        binding.forms.cardNumberLayout.editText?.setCompoundDrawablesRelativeWithIntrinsicBounds(
            0, 0, scheme.iconRes, 0
        )
    }

    private fun getCardDetails(): EMVCard? {
        val cardNum: Long? = if (binding.forms.cardNumberLayout.editText!!.text.isNotBlank()) {
            binding.forms.cardNumberLayout.editText!!.text.toString().toLong()
        } else {
            null
        }

        val cardHolder: String? = if (binding.forms.cardHolderLayout.editText!!.text.isNotBlank()) {
            binding.forms.cardHolderLayout.editText!!.text.toString()
        } else {
            null
        }

        val cvv: Int? = if (binding.forms.cvvLayout.editText!!.text.isNotBlank()) {
            binding.forms.cvvLayout.editText!!.text.toString().toInt()
        } else {
            null
        }

        val expiryMonth = binding.forms.expiryPicker.month
        val expiryYear = binding.forms.expiryPicker.year
        val cardType = if (binding.forms.cardTypeGroup.checkedButtonId == R.id.card_type_debit) EMVCardType.DEBIT
        else EMVCardType.CREDIT

        val cardLabel = binding.forms.cardLabelLayout.editText!!.text.toString()
        if (cardLabel.isBlank()) {
            binding.forms.cardLabelLayout.error = "Can't be empty"
            return null
        } else {
            binding.forms.cardLabelLayout.isErrorEnabled = false
        }

        return EMVCard(
            cardNumber = cardNum,
            expiryMonth = expiryMonth,
            expiryYear = expiryYear,
            cvv = cvv,
            cardType = cardType,
            cardHolder = cardHolder,
            cardLabel = cardLabel
        )
    }

    private fun generatePreview(card: EMVCard): EMVCardPreviewModel {
        return EMVCardPreviewModel(
            cardNumber = card.cardNumber,
            cardType = card.cardType,
            cardLabel = card.cardLabel
        )
    }

    private fun saveCardDetails(card: EMVCard) {
        val preview = generatePreview(card)

        AppExecutors.getInstance().diskIO().execute {
            database?.emvCardDao()?.insertCard(card)
            database?.emvCardPreviewDao()?.insertPreview(preview)
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val TAG = "EnterCardDetailsActivity"
    }
}