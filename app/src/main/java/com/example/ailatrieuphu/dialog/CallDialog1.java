package com.example.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.ailatrieuphu.OnActionCallBack;
import com.example.ailatrieuphu.OnItemSubmitListener;
import com.example.ailatrieuphu.R;


public class CallDialog1 extends Dialog {
    public static final String KEY_CALL_DOCTOR = "KEY_CALL_DOCTOR";
    public static final String KEY_CALL_PROFESSOR = "KEY_CALL_PROFESSOR";
    public static final String KEY_CALL_ARCHITECT = "KEY_CALL_ARCHITECT";
    public static final String KEY_CALL_INTERVIEW = "KEY_CALL_INTERVIEW";
    private final OnActionCallBack callBack;
    private final OnItemSubmitListener mOnItemSubmit;
    private ImageView ivDoctor, ivProfessor, ivArchitect, ivInterview;

    public CallDialog1(@NonNull Context context, OnActionCallBack callBack, OnItemSubmitListener mCallbackSubmit) {
        super(context);
        setContentView(R.layout.view_call_help_1);
        this.callBack = callBack;
        this.mOnItemSubmit = mCallbackSubmit;
        initViews();
    }

    private void initViews() {
        ivDoctor = findViewById(R.id.iv_doctor);
        ivProfessor = findViewById(R.id.iv_professor);
        ivArchitect = findViewById(R.id.iv_architect);
        ivInterview = findViewById(R.id.iv_interview);

        ivDoctor.setOnClickListener(view -> callDoctor());
        ivProfessor.setOnClickListener(view -> callProfessor());
        ivArchitect.setOnClickListener(view -> callArchitect());
        ivInterview.setOnClickListener(view -> callInterview());
    }

    private void callArchitect() {
        callBack.callBack(null, KEY_CALL_ARCHITECT);
        ivArchitect.setImageLevel(1);
        mOnItemSubmit.onItemSubmit(3, R.drawable.ic_call_architect, "Kĩ sư");
        dismiss();
    }

    private void callInterview() {
        callBack.callBack(null, KEY_CALL_INTERVIEW);
        ivInterview.setImageLevel(1);
        mOnItemSubmit.onItemSubmit(4, R.drawable.ic_call_interviewer, "Phóng viên");
        dismiss();
    }

    private void callProfessor() {
        callBack.callBack(null, KEY_CALL_PROFESSOR);
        ivProfessor.setImageLevel(1);
        mOnItemSubmit.onItemSubmit(2, R.drawable.ic_call_professor, "Giáo sư");
        dismiss();
    }

    private void callDoctor() {
        callBack.callBack(null, KEY_CALL_DOCTOR);
        ivDoctor.setImageLevel(1);
        mOnItemSubmit.onItemSubmit(1, R.drawable.ic_call_doctor, "Bác sĩ");
        dismiss();
    }
}
