package com.github.pwcong.jpstart.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<T : ViewBinding> : Fragment() {
    private lateinit var viewBinding: T

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = initViewBinding(inflater, container)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initVariable(savedInstanceState)
        doAction()
    }

    protected fun getViewBinding(): T {
        return viewBinding
    }

    protected abstract fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): T

    protected abstract fun initVariable(savedInstanceState: Bundle?)

    protected abstract fun doAction()

    fun showSnackBar(msg: String?) {
        Snackbar.make(requireView(), msg!!, Snackbar.LENGTH_SHORT).show()
    }

    fun showSnackBar(msg: Int) {
        Snackbar.make(requireView(), msg, Snackbar.LENGTH_SHORT).show()
    }
}
