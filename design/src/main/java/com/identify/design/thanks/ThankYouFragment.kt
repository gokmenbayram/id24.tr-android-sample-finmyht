package com.identify.design.thanks

import androidx.viewbinding.ViewBinding
import com.identify.design.R
import com.identify.design.databinding.FragmentThankYouBinding
import com.identify.design.dialogs.CustomDialog
import com.identify.sdk.base.viewBinding.viewBinding
import com.identify.sdk.thanks.BaseThankYouFragment

class ThankYouFragment : BaseThankYouFragment() {


    val binding by viewBinding(FragmentThankYouBinding::bind)


    override fun initViews() {
        btnFinish = binding.cardFinishView

        binding.ivCloseApp.setOnClickListener {
            CustomDialog(requireContext()).show(
                title = "Uyarı",
                message = "Çıkış yapmak istediğinize emin misiniz?",
                onConfirm = {
                    requireActivity().finish()
                }
            )
        }
    }

    override fun changeStatusColor(): Int? = android.R.color.transparent

    override fun getLayoutRes(): Int = R.layout.fragment_thank_you

    companion object {

        @JvmStatic
        fun newInstance() =
            ThankYouFragment().apply {

            }
    }
}