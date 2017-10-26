package com.example.varietystore.views;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

/**
 * Created by 鱼握拳 on 2017/8/29.
 */

public class ScrollAwareFABBehavior extends CoordinatorLayout.Behavior<FloatingActionsMenu> {
    private boolean mIsAnimatingOut=false;
    public ScrollAwareFABBehavior(Context context, AttributeSet attrs){
        super();
    }
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionsMenu child, View directTargetChild, View target, int nestedScrollAxes) {

        return nestedScrollAxes== ViewCompat.SCROLL_AXIS_VERTICAL|| super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionsMenu child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (dyConsumed>0&&!this.mIsAnimatingOut && child.getVisibility()==View.VISIBLE){
            if (child.isExpanded()){
                child.collapse();
            }
        } else if (dyConsumed<0 && child.getVisibility()!=View.VISIBLE){
        }
    }

}