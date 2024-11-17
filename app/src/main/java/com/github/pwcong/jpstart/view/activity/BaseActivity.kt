package com.github.pwcong.jpstart.ui.activity

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle

import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

import com.google.android.material.snackbar.Snackbar

import com.github.pwcong.jpstart.manager.ActivityManager

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {
    lateinit var viewBinding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityManager.getInstance().register(this)
        ActivityManager.getInstance().setCurrent(this)

        viewBinding = initViewBinding()
        setContentView(viewBinding.root)

        init(savedInstanceState)
    }

    protected abstract fun initViewBinding(): T

    protected abstract fun init(savedInstanceState: Bundle?)

    override fun onDestroy() {
        super.onDestroy()

        ActivityManager.getInstance().unregister(this)
    }

    fun showSnackBar(view: View, msg: String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()
    }

    fun showSnackBar(view: View, msg: Int) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()
    }

    fun showAlertDialog(
        context: Context, titleId: Int, message: String,
        iconId: Int,
        positiveTextId: Int, positiveButtonListener: DialogInterface.OnClickListener?
    ) {
        AlertDialog.Builder(context)
            .setTitle(titleId)
            .setMessage(message)
            .setPositiveButton(positiveTextId, positiveButtonListener)
            .setIcon(iconId)
            .create()
            .show()
    }

    fun showAlertDialog(
        context: Context, titleId: Int, messageId: Int,
        iconId: Int,
        positiveTextId: Int, positiveButtonListener: DialogInterface.OnClickListener?
    ) {
        AlertDialog.Builder(context)
            .setTitle(titleId)
            .setMessage(messageId)
            .setPositiveButton(positiveTextId, positiveButtonListener)
            .setIcon(iconId)
            .create()
            .show()
    }

    fun showAlertDialog(
        context: Context, titleId: Int, message: String,
        iconId: Int,
        positiveTextId: Int, positiveButtonListener: DialogInterface.OnClickListener?,
        negativeTextId: Int, negativeButtonListener: DialogInterface.OnClickListener?
    ) {
        AlertDialog.Builder(context)
            .setTitle(titleId)
            .setMessage(message)
            .setPositiveButton(positiveTextId, positiveButtonListener)
            .setNegativeButton(negativeTextId, negativeButtonListener)
            .setIcon(iconId)
            .create()
            .show()
    }

    fun showAlertDialog(
        context: Context, titleId: Int, messageId: Int,
        iconId: Int,
        positiveTextId: Int, positiveButtonListener: DialogInterface.OnClickListener?,
        negativeTextId: Int, negativeButtonListener: DialogInterface.OnClickListener?
    ) {
        AlertDialog.Builder(context)
            .setTitle(titleId)
            .setMessage(messageId)
            .setPositiveButton(positiveTextId, positiveButtonListener)
            .setNegativeButton(negativeTextId, negativeButtonListener)
            .setIcon(iconId)
            .create()
            .show()
    }

    override fun onResume() {
        super.onResume()
        ActivityManager.getInstance().setCurrent(this)
    }
}