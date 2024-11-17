package com.github.pwcong.jpstart.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.github.pwcong.jpstart.constants.Constants
import com.github.pwcong.jpstart.databinding.FragmentBannerBinding
import com.github.pwcong.jpstart.mvp.bean.BannerItem

class BannerFragment : BaseFragment<FragmentBannerBinding>() {

    private lateinit var mImageView: ImageView

    private var banner = 0

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBannerBinding {
        return FragmentBannerBinding.inflate(inflater, container, false)
    }

    override fun init(savedInstanceState: Bundle?) {
        mImageView = viewBinding.ivBanner
        banner = requireArguments().getInt(Constants.IMG_BANNER)

        context?.let { Glide.with(it).asBitmap().load(banner).into(mImageView) }
    }

    companion object {
        fun newInstance(item: BannerItem): BannerFragment {
            val arguments = Bundle()
            arguments.putInt(Constants.IMG_BANNER, item.banner)
            val fragment = BannerFragment()
            fragment.arguments = arguments

            return fragment
        }
    }
}
