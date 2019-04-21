package com.example.greeknews.weigth;

public interface TouchCallBacak {
    //数据交换
    void onItemMove(int formPosition,int toPostition);

    //删除条目
    void onItemDelete(int position);
}
