package com.hxb.commondialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private int[] textSizeArray = {10, 20, 25, 30, 40};
    private CharSequence[] item = new CharSequence[]{"旅游","美食","看电影","运动"};
    private boolean[] checkedItems = new boolean[]{false,false,false,false};
    int textSize = 1;
    Button bt1,bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt1).setOnClickListener(this);
        findViewById(R.id.bt2).setOnClickListener(this);
        textView = (TextView) findViewById(R.id.tv);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);

    }

    @Override
    public void onClick(View v) {

        if (v==bt1){
            AlertDialog dialog;
            String[] args = new String[]{"小号", "默认", "中号", "大号",
                    "超大"};
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("设置字体大小")           //设置标题
                    .setIcon(R.mipmap.ic_launcher)
                    .setSingleChoiceItems(args, textSize, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            textSize = which;
                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //为TextView设置在单选对话框中选择的字体大小
                            textView.setTextSize(textSizeArray[textSize]);
                            dialog.dismiss(); //关闭对话框
                        }
                    })//添加“确定”按钮
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            dialog = builder.create();
            dialog.show();
        }
        if(v==bt2){
            AlertDialog dialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("添加爱好:");
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setMultiChoiceItems(item, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    checkedItems[which]=isChecked;
                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @SuppressLint("ShowToast")
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i=0;i<=checkedItems.length-1;i++){
                        if (checkedItems[i]){
                            stringBuffer.append(item[i]).append(" ");
                        }
                    }
                    Toast.makeText(MainActivity.this,stringBuffer,Toast.LENGTH_LONG).show();

                    dialog.dismiss();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog=builder.create();
            dialog.show();
        }
    }
}