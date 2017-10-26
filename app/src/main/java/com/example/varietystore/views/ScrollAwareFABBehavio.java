package com.example.varietystore.views;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by 鱼握拳 on 2017/9/19.
 */

//滑动隐藏floatactionbutton
    public class ScrollAwareFABBehavio extends FloatingActionButton.Behavior{
        public ScrollAwareFABBehavio(Context context, AttributeSet attrs) {
            super();
        }

        @Override
        public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout, final FloatingActionButton child,
                                           final View directTargetChild, final View target, final int nestedScrollAxes) {
            return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
        }

        @Override
        public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                                   View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
            super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed,
                    dyUnconsumed);

            if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE) {
                child.hide();
            } else if (dyConsumed <=0 && child.getVisibility() != View.VISIBLE) {
                child.show();
            }
        }



        // FAB移出屏幕动画（隐藏动画）
        private void animateOut(FloatingActionButton fab) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
            int bottomMargin = layoutParams.bottomMargin;
            fab.animate().translationY(fab.getHeight() + bottomMargin).setInterpolator(new LinearInterpolator()).start();
        }

        // FAB移入屏幕动画（显示动画）
        private void animateIn(FloatingActionButton fab) {
            fab.animate().translationY(0).setInterpolator(new LinearInterpolator()).start();
        }
    }
