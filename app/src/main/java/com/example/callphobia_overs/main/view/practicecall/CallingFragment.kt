package com.example.callphobia_overs.main.view.practicecall

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.text.Layout.Directions
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.FragmentCallingBinding
import com.example.callphobia_overs.main.base.BaseFragment
import com.example.callphobia_overs.main.view.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CallingFragment : BaseFragment<FragmentCallingBinding>(R.layout.fragment_calling) {
    private lateinit var speechRecognizer: SpeechRecognizer
    private val LOG="calling"
    private var callHistory : String = "" //통화 내용을 누적

    override fun initClick() {
        val navController = requireActivity().findNavController(R.id.fragContainer)

        binding.btnStopCall.setOnClickListener {
            val action = CallingFragmentDirections.actionCallingFragmentToCallingEndFragment(
                callTitle = "테스트용", callContent = callHistory)

            navController.navigate(action)
        }

        binding.btnToSpeak.setOnClickListener { //사용자 음성 인식 시작
            Log.d(LOG, "통화 버튼 누름")
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext())
            speechRecognizer.setRecognitionListener(recognitionListener)
            speechRecognizer.startListening(recordUserVoice())
        }
    }

    override fun initView() {
        callTimer()
    }

    private fun callTimer(){
        binding.timerCallTime.base = SystemClock.elapsedRealtime()
        binding.timerCallTime.start()
    }

    private fun recordUserVoice(): Intent {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, requireActivity().packageName)//인식률 상승을 위해 추가
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR")// 언어 설정

        return intent
    }


    private val recognitionListener : RecognitionListener = object : RecognitionListener{
        override fun onReadyForSpeech(params: Bundle?) {
            binding.textUserSay.text = "음성을 인식할 준비가 되었어요."
        }

        override fun onBeginningOfSpeech() {

        }

        override fun onRmsChanged(p0: Float) {

        }

        override fun onBufferReceived(p0: ByteArray?) {

        }

        override fun onEndOfSpeech() {

        }

        override fun onError(error: Int) {
            val message = when(error) {
                SpeechRecognizer.ERROR_AUDIO -> "오디오 관련 에러가 발생했습니다."
                SpeechRecognizer.ERROR_CLIENT -> "클라이언트에 에러가 발생했습니다."
                SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "권한이 설정되어 있지 않습니다."
                SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "음성인식기가 현재 작업중입니다."
                SpeechRecognizer.ERROR_SPEECH_TIMEOUT ->"음성인식 시간이 초과되었습니다."
                else -> "알 수 없는 오류가 발생했습니다."
            }
            binding.textUserSay.text = message
        }

        override fun onResults(result: Bundle?) {
            val data = result?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            for(i in data!!.indices)
                binding.textUserSay.text = data[i]

            callHistory = binding.textUserSay.text.toString()
        }

        override fun onPartialResults(partialResults: Bundle?) {

        }

        override fun onEvent(eventType: Int, params: Bundle?) {

        }

    }

}