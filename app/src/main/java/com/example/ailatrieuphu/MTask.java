package com.example.ailatrieuphu;

import android.os.AsyncTask;

public final class MTask extends AsyncTask<Object, Object, Object> {
    private final String key;
    private final MTaskListener callBack;

    public MTask(String key, MTaskListener callBack) {
        this.key = key;
        this.callBack = callBack;
    }

    @Override
    protected void onPreExecute() {
        callBack.startExecute();
    }

    public void requestUpdateUI(Object dataUpdate) {
        publishProgress(dataUpdate);
    }

    @Override
    protected Object doInBackground(Object... data) {
        return callBack.executeStart(data == null ? null : data[0], key, this);
    }

    @Override
    protected void onProgressUpdate(Object... dataUpdate) {
        callBack.updateUI(dataUpdate == null ? null : dataUpdate[0], key);
    }

    @Override
    protected void onPostExecute(Object result) {
        callBack.completeTask(result, key);
    }


    public interface MTaskListener {
        default void startExecute() {
        }

        Object executeStart(Object dataInput, String key, MTask task);

        default void updateUI(Object dataUpdate, String key) {
            //do nothing
        }

        default void completeTask(Object result, String key) {
            //do nothing
        }
    }
}
