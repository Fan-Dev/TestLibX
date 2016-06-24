package com.example.interfaces;

/**
 * 异步任务(网络请求)的主线程回调
 * Created by Administrator on 2016/6/24.
 */

public interface UICallBack {
    /**
     * 结果处理
     *
     * @param result
     */
    public abstract void onPostExecute(String result);

    /**
     * 执行前的处理
     */
    public void onPreExecute();

    /**
     * 请求被取消
     */
    public void onCancled();

    /**
     * 执行进度更新
     *
     * @param progress 进行进度
     */
    public void onUpdateProgress(int progress);
}
