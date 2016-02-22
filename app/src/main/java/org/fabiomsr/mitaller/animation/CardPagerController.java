package org.fabiomsr.mitaller.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.fabiomsr.mitaller.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CardPagerController {

  @Bind(R.id.before_card) TextView mBeforeCardView;
  @Bind(R.id.next_card)   TextView mNextCardView;

  @Bind(R.id.card_container) ViewGroup mCardContainer;

  View[] mCardViews;

  private int mCurrentCardIndex;

  public CardPagerController(View view){
    ButterKnife.bind(this, view);

    mCardViews = new View[mCardContainer.getChildCount()];

    for (int i = 0; i < mCardViews.length; i++) {
      mCardViews[i] = mCardContainer.getChildAt(i);
    }

  }

  @OnClick(R.id.before_card)
  public void onBeforeCard() {
    View currentCard = mCardViews[mCurrentCardIndex];
    View beforeCard  = mCardViews[mCurrentCardIndex - 1];

    currentCard.animate()
        .translationX(mCardContainer.getWidth())
        .setListener(new AnimatorListenerAdapter() {
          @Override
          public void onAnimationEnd(Animator animation) {
            currentCard.setVisibility(View.GONE);
          }
        })
        .start();

    beforeCard.animate()
        .translationX(0)
        .setListener(null)
        .start();

    beforeCard.setVisibility(View.VISIBLE);
    mNextCardView.setVisibility(View.VISIBLE);

    mCurrentCardIndex--;

    if (mCurrentCardIndex == 0) {
      mBeforeCardView.setVisibility(View.GONE);
    }
  }

  @OnClick(R.id.next_card)
  public void onNextCard() {
    View currentCard = mCardViews[mCurrentCardIndex];

    mCurrentCardIndex++;

    if (mCurrentCardIndex == mCardViews.length - 1) {
      mNextCardView.setVisibility(View.GONE);
    }

    View nextCard = mCardViews[mCurrentCardIndex];

    currentCard.animate()
        .translationX(-mCardContainer.getWidth())
        .setListener(new AnimatorListenerAdapter() {
          @Override
          public void onAnimationEnd(Animator animation) {
            currentCard.setVisibility(View.GONE);
          }
        })
        .start();

    nextCard.setTranslationX(mCardContainer.getWidth());
    nextCard.setVisibility(View.VISIBLE);
    nextCard.animate()
        .translationX(0)
        .setListener(null)
        .start();

    mBeforeCardView.setVisibility(View.VISIBLE);
  }


}
