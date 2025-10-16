package com.identify.design.prepare

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.identify.design.R
import com.identify.design.databinding.FragmentPrepareBinding
import com.identify.sdk.base.viewBinding.viewBinding
import com.identify.sdk.prepare.BasePrepareFragment

class PrepareFragment : BasePrepareFragment() {

    val binding by viewBinding(FragmentPrepareBinding::bind)

    override fun getUnsuitableInternetErrorMessage(): String =getString(R.string.unsuitable_internet)

    override fun initViews() {
        cbConnectionStatus = binding.cbConnectionFine
        ivTestAgain = binding.ivDownloadTestAgain
        btnContinue = binding.btnConfirm
        /*cbIdNear = binding.cbIdNear
        cbIamAlone = binding.cbIamAlone
        cbPlaceState = binding.cbPlaceState*/

        setupSwitchListeners()
    }

    override fun getLayoutRes() = R.layout.fragment_prepare

    companion object {

        @JvmStatic
        fun newInstance() =
            PrepareFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private fun setupSwitchListeners() {
        with(binding) {
            swCameraPermission.setOnCheckedChangeListener { _, _ -> checkAllSwitches() }
            swMicPermission.setOnCheckedChangeListener { _, _ -> checkAllSwitches() }
            swVoiceRecognitionPermission.setOnCheckedChangeListener { _, _ -> checkAllSwitches() }
            swIdentityNearPermission.setOnCheckedChangeListener { _, _ -> checkAllSwitches() }
            swAlonePermission.setOnCheckedChangeListener { _, _ -> checkAllSwitches() }
            swPlaceStatePermission.setOnCheckedChangeListener { _, _ -> checkAllSwitches() }
        }
    }

    private fun checkAllSwitches() {
        with(binding) {
            val allChecked =
                swCameraPermission.isChecked &&
                        swMicPermission.isChecked &&
                        swVoiceRecognitionPermission.isChecked &&
                        swIdentityNearPermission.isChecked &&
                        swAlonePermission.isChecked &&
                        swPlaceStatePermission.isChecked

            btnConfirm.isEnabled = allChecked
        }
    }
}