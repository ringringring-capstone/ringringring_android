package com.example.callphobia_overs.main.view.practicecall

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.callphobia_overs.R
import com.example.callphobia_overs.databinding.FragmentCallingBinding
import com.example.callphobia_overs.main.base.AlertDialogInterface
import com.example.callphobia_overs.main.base.BaseFragment
import com.example.callphobia_overs.main.base.CustomAlertDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CallingFragment : BaseFragment<FragmentCallingBinding>(R.layout.fragment_calling), AlertDialogInterface {
    private lateinit var speechRecognizer: SpeechRecognizer
    private val LOG="calling"
    private var pauseTime = 0L //통화시간 일시정지시
    private var callHistory : String = "" //통화 내용을 누적
    private lateinit var navController: NavController

    override fun initView() {
        navController = requireActivity().findNavController(R.id.fragContainer)
        callTimer()
    }

    override fun initClick() {
        binding.btnStopCall.setOnClickListener { //통화 끊기 버튼
            saveRecords()
        }

        binding.btnToSpeak.setOnClickListener { //사용자 음성 인식 시작
            Log.d(LOG, "통화 버튼 누름")
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext())
            speechRecognizer.setRecognitionListener(recognitionListener)
            speechRecognizer.startListening(recordUserVoice())
        }
    }

    /** 아래부터 관련 함수들 */

    private fun callTimer(){
        binding.timerCallTime.base = SystemClock.elapsedRealtime()
        binding.timerCallTime.start()
    }


    private fun saveRecords() {
        pauseTime = SystemClock.elapsedRealtime() - binding.timerCallTime.base
        Log.d("tttttt", (pauseTime/1000).toString())
        binding.timerCallTime.stop() //시간 잠깐 멈춰주기
        CustomAlertDialog(requireContext(), this).show()

        /*
        val builder = AlertDialog.Builder(context).apply {
            setTitle("통화를 종료하시겠어요?")
            setMessage("나가면 현재 통화 내용을 이어갈 수 없어요.")

            setPositiveButton("확인",DialogInterface.OnClickListener { dialog, which ->
                val action = CallingFragmentDirections.actionCallingFragmentToCallingEndFragment(
                    callTitle = "테스트용", callContent = callHistory)

                navController.navigate(action)
            })
            setNegativeButton("취소",DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(requireContext(),"통화를 계속 이어갈게",Toast.LENGTH_SHORT).show()
            })
            show()
        }
        builder.create()
        */

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

    override fun PositiveBtnClicked() {
        val action = CallingFragmentDirections.actionCallingFragmentToCallingEndFragment(
            callTitle = "테스트용",
            callContent = resources.getString(R.string.callRecord_content_test),
            callTime = pauseTime/1000)

        navController.navigate(action)
    }

    override fun NegativeBtnClicked() {
        binding.timerCallTime.base = SystemClock.elapsedRealtime() - pauseTime
        binding.timerCallTime.start() //타이머 재시작
        Toast.makeText(requireContext(),"통화를 계속 이어갈게요",Toast.LENGTH_SHORT).show()
    }

}