package com.example.callphobia_overs.main.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.example.callphobia_overs.databinding.CustomAlertdialogBinding


class CustomAlertDialog(context: Context,
                        alertDialogInterface: AlertDialogInterface) : Dialog(context) {

    private lateinit var binding : CustomAlertdialogBinding
    private var alertDialogInterface :AlertDialogInterface ?= null

    init {
        this.alertDialogInterface = alertDialogInterface
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CustomAlertdialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCancelable(false) //빈 터치 화면 이런거 클릭해도 다이얼로그가 꺼지지 않게 해줌
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))



        binding.btnShutdownOk.setOnClickListener {
            this.alertDialogInterface?.PositiveBtnClicked()
            dismiss()
        }
        binding.btnShutdownRefuse.setOnClickListener {
            this.alertDialogInterface?.NegativeBtnClicked()
            dismiss()
        }

    }

}