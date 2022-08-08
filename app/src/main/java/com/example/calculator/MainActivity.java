package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView t_result;
    //运算符前面的数
    private String first="";
    //运算符
    private String operator="";
    //运算符后面的数
    private String secound="";
    //结果
    private String result="";
    //显示框
    private String show="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t_result =  findViewById(R.id.t_result);
        //监听按钮
        findViewById(R.id.b_cancel).setOnClickListener(this);
        findViewById(R.id.b_divide).setOnClickListener(this);
        findViewById(R.id.b_multiply).setOnClickListener(this);
        findViewById(R.id.b_clear).setOnClickListener(this);
        findViewById(R.id.b_seven).setOnClickListener(this);
        findViewById(R.id.b_eight).setOnClickListener(this);
        findViewById(R.id.b_nine).setOnClickListener(this);
        findViewById(R.id.b_plus).setOnClickListener(this);
        findViewById(R.id.b_four).setOnClickListener(this);
        findViewById(R.id.b_five).setOnClickListener(this);
        findViewById(R.id.b_six).setOnClickListener(this);
        findViewById(R.id.b_minus).setOnClickListener(this);
        findViewById(R.id.b_one).setOnClickListener(this);
        findViewById(R.id.b_two).setOnClickListener(this);
        findViewById(R.id.b_three).setOnClickListener(this);
        findViewById(R.id.b_reciprocal).setOnClickListener(this);
        findViewById(R.id.b_zero).setOnClickListener(this);
        findViewById(R.id.b_dot).setOnClickListener(this);
        findViewById(R.id.b_equal).setOnClickListener(this);
        findViewById(R.id.i_sqrt).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //获取到按钮的文本内容
        String inputText;
        //如果是图片按钮
        if (view.getId()==R.id.i_sqrt){
            inputText = "√";
        }else{
            inputText = ((TextView)view).getText().toString();
        }
        switch(view.getId()){
            case R.id.b_clear:
                clear();
                break;
            case R.id.b_cancel:
                clear();
                break;
            case R.id.b_plus:
            case R.id.b_minus:
            case R.id.b_multiply:
            case R.id.b_divide:
                //长运算式
                if (!operator.equals("")){
                    //如果前面有运算式则先计算前面的
                    if(!first.equals("")&&!secound.equals("")){
                        double r = calcuator();
                        result = String.valueOf(r);
                        operator = "";
                        first = result;
                        secound = "";
                        show = result;
                    }else {
                        break;
                    }
                }
                operator = inputText;
                refreshText(show+operator);
                break;
            case R.id.b_equal:
                //四则运算
                double r = calcuator();
                refreshOperator(String.valueOf(r));
                refreshText(result);
                break;
            case R.id.i_sqrt:
                double r2 = Math.sqrt(Double.parseDouble(first));
                refreshOperator(String.valueOf(r2));
                refreshText(result);
                break;
            case R.id.b_reciprocal:
                double r3 = 1.0 / Double.parseDouble(first);
                refreshOperator(String.valueOf(r3));
                refreshText(result);
                break;
            default:
                //清除上次的运行结果
                if (result.length()>0&&operator.equals("")){
                    clear();
                }
                //无运算符,继续拼接第一个数
                if (operator.equals("")){
                    first=first+inputText;
                }else{
                    //有运算符，拼接第二个数
                    secound = secound+inputText;
                }
                if (show.equals("0")&&!inputText.equals(".")){
                    refreshText(inputText);
                }else {
                    refreshText(show + inputText);
                }
                break;
        }
    }

    private double calcuator() {
        switch (operator){
            case "+":
                return Double.parseDouble(first)+Double.parseDouble(secound);
            case "—":
                return Double.parseDouble(first)-Double.parseDouble(secound);
            case "x":
                return Double.parseDouble(first)*Double.parseDouble(secound);
            default :
                return Double.parseDouble(first)/Double.parseDouble(secound);
        }
    }

    private void clear() {
        refreshText("");
        refreshOperator("");
    }

    private void refreshOperator(String r){
        result = r;
        operator = "";
        first = result;
        secound = "";
    }

    private void refreshText(String s){
        show=s;
        t_result.setText(show);
   }
}