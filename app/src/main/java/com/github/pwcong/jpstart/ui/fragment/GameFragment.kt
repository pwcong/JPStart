package com.github.pwcong.jpstart.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.github.pwcong.jpstart.R
import com.github.pwcong.jpstart.databinding.FragmentGameBinding
import com.github.pwcong.jpstart.rxbus.RxBus
import com.github.pwcong.jpstart.rxbus.event.EventContainer
import com.github.pwcong.jpstart.rxbus.event.GameEvent

class GameFragment : BaseFragment<FragmentGameBinding>() {

    private lateinit var mPuzzleCardView: CardView

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentGameBinding {
        return FragmentGameBinding.inflate(inflater, container, false)
    }

    override fun initVariable(savedInstanceState: Bundle?) {
        mPuzzleCardView = getViewBinding().root.findViewById(R.id.cv_puzzle)
        mPuzzleCardView.setOnClickListener {
            RxBus.getDefault()
                .post(
                    EventContainer(
                        EventContainer.TYPE_GAME,
                        GameEvent(GameEvent.TYPE_PUZZLE)
                    )
                )
        }
    }

    override fun doAction() {
    }
}
