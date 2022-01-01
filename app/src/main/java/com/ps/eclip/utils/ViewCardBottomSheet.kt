package com.ps.eclip.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ps.eclip.databinding.SheetViewCardInfoBinding
import com.ps.eclip.models.EMVCard

class ViewCardBottomSheet(
    private val card: EMVCard? = null
) : BottomSheetDialogFragment() {

    private val binding by lazy { SheetViewCardInfoBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        inflateCardDetails()
    }

    private fun inflateCardDetails() {
        card ?: return
        binding.cardLabel.text = card.cardLabel
        binding.emvCard.setCardNumber(card.cardNumber)
        if (card.expiryMonth != null && card.expiryYear != null) {
            binding.emvCard.setCardExpiry(card.expiryMonth!!, card.expiryYear!!)
        }
        binding.emvCard.setCardType(card.cardType)
        binding.emvCard.setCvv(card.cvv)
    }
}