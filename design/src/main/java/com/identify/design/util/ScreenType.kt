package com.identify.design.util

/**
 * Created by gokmenbayram on 11.10.2025.
 */
enum class ScreenType {
    CARD_BACK_SIDE,
    CARD_FRONT_SIDE,
    NFC_INFO,
    SELFIE,
    ADDRESS_INFO;

    companion object{
        fun convertToScreenType(screenName: String?): ScreenType? {
            return values().find { screenName == it.name }
        }
    }
}