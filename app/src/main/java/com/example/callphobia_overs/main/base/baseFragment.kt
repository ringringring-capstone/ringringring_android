package com.example.callphobia_overs.main.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.callphobia_overs.R
import com.example.callphobia_overs.main.view.MainActivity

/** fragment base, 중복되는 부분들 없앰 */

abstract class BaseFragment<T : ViewDataBinding>(@LayoutRes val layoutRes : Int) : Fragment() {
    lateinit var binding : T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        initView()
        initClick()
        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun initView() //보여주는 부분 작성
    abstract fun initClick() //클릭 이벤트 부분 작성

    /*
    protected fun findNavController():NavController?{
        val navHostFragment = (requireActivity() as? MainActivity)?.
        supportFragmentManager?.
        findFragmentById(R.id.showFrame) as? NavHostFragment

        return navHostFragment?.navController
    }
*/
}