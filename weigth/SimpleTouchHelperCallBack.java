package com.example.greeknews.weigth;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class SimpleTouchHelperCallBack extends ItemTouchHelper.Callback {

    private TouchCallBacak mCallback;
    private boolean mswipeEnable = true;

    public SimpleTouchHelperCallBack(TouchCallBacak mCallback) {
        this.mCallback = mCallback;
    }

    /**
     * 返回可以滑动的方向
     * makeMovementFlags 或 makeflag
     * 来构造我们的返回值
     */


    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        //允许上下拖拽
        int drag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        //允许左右滑动
        int swiple = ItemTouchHelper.LEFT;
        //drag 拖拽的方向
        //swipe 滑动的方向
        return makeMovementFlags(drag,swiple);
    }

    /**
     * 拖动Item 时回调 可以调用Adapter 的notifyItemMoved 方法来交换两个viewholder 的位置
     * 最后返回true 表示被拖动 的viewholder 已经 移到了目标位置
     */

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        mCallback.onItemMove(viewHolder.getAdapterPosition(),viewHolder1.getAdapterPosition());
        return true;
    }

    /**
     * 当用户左右滑动 item时 达到删除条件就会调用 一般为一半 条目继续滑动删除 否则弹回
     */

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        mCallback.onItemDelete(viewHolder.getAdapterPosition());
    }

    /*
    * 支持滑动 即可以调用到onSwiped方法 默认是true
    */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return mswipeEnable;
    }

    /**
     * 设置是否支持滑动
    */

    public void setMswipeEnable(boolean mswipeEnable) {
        mswipeEnable = mswipeEnable;
    }
}
