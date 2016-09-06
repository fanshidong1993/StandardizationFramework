package com.sdf.www.standardizationframework.views;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sdf.www.standardizationframework.R;
import com.sdf.www.standardizationframework.bean.DemoBean;
import com.sdf.www.standardizationframework.interfaces.DemoViewInterface;
import com.sdf.www.standardizationframework.presenters.DemoPresenter;



/**
 * Created by shadow on 16/8/22.
 */

public class DemoViewActivity extends Activity implements DemoViewInterface, View.OnClickListener {

    private Button btn_showToast,btn_showDialog,btn_countDownStart,btn_countDownReset,btn_request;
    private TextView tv_countDown_number,tv_showResult;
    private EditText et_input;
    private DemoPresenter demoPresenter;
    private ImageView iv_imagerequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demoview);
        demoPresenter = new DemoPresenter(this);
        bindView();
        bindEvent();
    }

    private void bindEvent() {
        btn_request.setOnClickListener(this);
        btn_countDownReset.setOnClickListener(this);
        btn_countDownStart.setOnClickListener(this);
        btn_showDialog.setOnClickListener(this);
        btn_showToast.setOnClickListener(this);
        iv_imagerequest.setOnClickListener(this);

    }

    private void bindView() {
        et_input = (EditText) findViewById(R.id.et_input);
        btn_showToast = (Button) findViewById(R.id.btn_showtoast);
        btn_showDialog = (Button) findViewById(R.id.btn_showdialog);
        tv_countDown_number = (TextView) findViewById(R.id.tv_countdown_number);
        btn_countDownStart = (Button) findViewById(R.id.btn_countdown_start);
        btn_countDownReset = (Button) findViewById(R.id.btn_countdown_reset);
        btn_request = (Button) findViewById(R.id.btn_request);
        tv_showResult = (TextView) findViewById(R.id.tv_result);
        iv_imagerequest = (ImageView) findViewById(R.id.iv_demoview_imagerequest);
    }


    @Override
    public void showToast(String str, int length) {
        String content = str + "&" + length;
        Toast.makeText(this,content,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showDialog(String str, int length) {
        Dialog dialog = new Dialog(this);
        String content = str + "&" + length;
        TextView tv_content = new TextView(this);
        tv_content.setText(content);
        dialog.setContentView(tv_content);
        dialog.show();
    }

    @Override
    public void showCountDown(int count) {
        tv_countDown_number.setText(String.valueOf(count));
    }

    @Override
    public void showMessage(DemoBean msg) {
        tv_showResult.setText(msg.getNickname());
    }

    @Override
    public void loagImage(Bitmap bitmap) {
        iv_imagerequest.setImageBitmap(bitmap);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_showtoast:
                demoPresenter.clickShowToast(et_input.getText().toString());
                break;
            case R.id.btn_showdialog:
                demoPresenter.clickShowDialog(et_input.getText().toString());
                break;
            case R.id.btn_countdown_reset:
                demoPresenter.clickCountDownRestart();
                break;
            case R.id.btn_countdown_start:
                demoPresenter.clickCountDownStart();
                break;
            case R.id.btn_request:
                demoPresenter.clickRequest();
                break;
            case R.id.iv_demoview_imagerequest:
                demoPresenter.clickImageRequest();
                break;
        }
    }
}
