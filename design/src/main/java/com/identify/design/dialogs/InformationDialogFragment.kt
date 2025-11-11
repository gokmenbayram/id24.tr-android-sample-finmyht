package com.identify.design.dialogs

import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieDrawable
import com.identify.design.R
import com.identify.design.databinding.DialogInformationBinding
import com.identify.design.util.ScreenType
import com.identify.sdk.base.viewBinding.viewBinding
import com.identify.sdk.information.BaseInformationDialogFragment
import com.identify.sdk.repository.model.enums.IdentifyInformationTypes
import com.identify.sdk.repository.model.enums.IdentifyModuleTypes

class InformationDialogFragment : BaseInformationDialogFragment() {


    val binding by viewBinding(DialogInformationBinding::bind)




    override fun setCancelable(): Boolean  = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
       // activity?.finish()
    }

    override fun getLayoutRes(): Int = R.layout.dialog_information


    override fun initViews() {
        animDetectionStatus = binding.animDetectionStatusView
        imgFrame = binding.imgFrameView
        imgInfo = binding.imgInfoView
        tvInfoContent = binding.tvInfoContentView
        tvInfoTitle = binding.tvInfoTitleView
        btnContinue = binding.cardContinueBtnView
        btnClose = binding.imgCloseBtnView
        cbSignLang = binding.cbSignLangView

        checkScreenType(ScreenType.convertToScreenType(arguments?.getString("screenType")))

        binding.ivCloseApp.setOnClickListener {
            CustomDialog(requireContext()).show(
                title = "Uyarı",
                message = "Çıkış yapmak istediğinize emin misiniz?",
                onConfirm = {
                    requireActivity().finish()
                }
            )
        }

        binding.btnBack.setOnClickListener {
            handleBackNavigation()
        }
    }

    /**
     * Geri navigasyonu handle eder
     */
    private fun handleBackNavigation() {
        backPressFromInformation()
    }

    override fun changeStatusColor(): Int? = android.R.color.transparent

    private fun checkScreenType(screenType: ScreenType?) {
        when(screenType) {
            ScreenType.CARD_FRONT_SIDE -> {
                binding.rlContainer.visibility = View.GONE
                binding.llContainer.visibility = View.VISIBLE

                binding.tvCartTitle.text = getString(R.string.id_front_title)
                binding.tvCartDesc.text = getString(R.string.take_id_front_photo_instruction)
                binding.tvCardContinue.text = "Kamerayı Aç"

                binding.ivTitleIcon.background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_identity_front)
            }
            ScreenType.CARD_BACK_SIDE -> {
                binding.rlContainer.visibility = View.GONE
                binding.llContainer.visibility = View.VISIBLE

                binding.tvCartTitle.text = getString(R.string.id_back_title)
                binding.tvCartDesc.text = getString(R.string.take_id_back_photo_instruction)
                binding.tvCardContinue.text = "Kamerayı Aç"

                binding.ivTitleIcon.background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_identity_back)
            }
            ScreenType.NFC_INFO -> {
                binding.rlContainer.visibility = View.GONE
                binding.llContainer.visibility = View.VISIBLE

                binding.tvCartTitle.text = "Çip Okuma (NFC)"
                binding.tvCartDesc.text = "Kimliğinizi telefonunuza temas ettirin ve işlem bitene kadar hareket ettirmeyin."
                binding.tvCartDesc2.text = "Telefon kılıfınızı çıkarmanızı öneririz."
                binding.ivTitleIcon.background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_nfc)

            }
            ScreenType.SELFIE -> {
                binding.rlContainer.visibility = View.GONE
                binding.llContainer.visibility = View.VISIBLE

                binding.tvCartTitle.text = "Selfie Çekim"
                binding.tvCartDesc.text = "Lütfen yüzünüzü kameraya odaklayarak bir selfie çekin. Hazırsanız devam ederek başlayabilirsiniz."
                binding.ivTitleIcon.background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_selfie)
                binding.tvCardContinue.text = "Kamerayı Aç"

            }

            ScreenType.ADDRESS_INFO -> {
                binding.rlContainer.visibility = View.GONE
                binding.llContainer.visibility = View.VISIBLE

                binding.tvCartTitle.text = "Adres Doğrulama"
                binding.tvCartDesc.text = "Adresinizi teyit için güncel ikametgah belgesi veya son üç aya ait bir fatura görseli yükleyiniz."
                binding.ivTitleIcon.background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_document)
                //binding.tvCardContinue.text = "Kamerayı Aç"

            }
            else -> {
                binding.rlContainer.visibility = View.VISIBLE
                binding.llContainer.visibility = View.GONE
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(identifyInformationTypes: IdentifyInformationTypes?=null,identifyModuleTypes: IdentifyModuleTypes?=null,@DrawableRes animResourceId : Int?=null,@DrawableRes imgResourceId : Int?=null,infoTitleText : String,infoContentText : String,animRepeatCount : Int = LottieDrawable.INFINITE,isImgFrameVisible: Int = View.GONE,
                        screenType: ScreenType? = null) =
            InformationDialogFragment().apply {
                arguments = Bundle().apply {
                    putString("infoContentText",infoContentText)
                    putString("infoTitleText",infoTitleText)
                    putInt("animRepeatCount",animRepeatCount)
                    putString("identifyInformationTypes",identifyInformationTypes?.name)
                    putString("identifyModuleTypes",identifyModuleTypes?.name)
                    putInt("isImgFrameVisible",isImgFrameVisible)
                    animResourceId?.let { putInt("animResourceId", it) }
                    imgResourceId?.let { putInt("imgResourceId",it) }
                    screenType?.let { putString("screenType", screenType.name) }
                }
            }
    }

}