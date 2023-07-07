package com.example.capstone111;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.Objects;

public class CustomDialog extends Dialog {

    private EditText et_text;
    private Context mContext;

    private TestDialogListener testDialogListener;
    String name;
    Objects Dial;

    public interface TestDialogListener{
        void clickBtn(String data);
    }

    public CustomDialog(Context mContext,  TestDialogListener testDialogListener) {
        super(mContext);
        this.mContext = mContext;
        this.testDialogListener = testDialogListener;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);

        // 다이얼로그의 배경을 투명으로 만든다.

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        et_text = findViewById(R.id.put_text);
        Button saveButton = findViewById(R.id.btnSave);
        Button cancelButton = findViewById(R.id.btnCancle);

        // 버튼 리스너 설정
        saveButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // '확인' 버튼 클릭시
                // ...코드..
                name = null;
                name = et_text.getText().toString();
                if(name.length() == 0){
                    Toast.makeText(mContext,"제목을 입력하세요", Toast.LENGTH_SHORT).show();
                }else{
                    testDialogListener.clickBtn(name);
                }
                // Custom Dialog 종료
                dismiss();
            }
        });
        cancelButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // '취소' 버튼 클릭시
                // ...코드..
                // Custom Dialog 종료
                dismiss();
            }
        });

    }

}