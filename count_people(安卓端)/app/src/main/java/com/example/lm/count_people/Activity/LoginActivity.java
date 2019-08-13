package com.example.lm.count_people.Activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lm.count_people.Adapter.JellyInterpolator;
import com.example.lm.count_people.R;
import com.example.lm.count_people.dto.User;
import com.example.lm.count_people.util.OKhttpUtil;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;

public class LoginActivity extends AppCompatActivity {
    private TextView mBtnLogin,mBtnRegist,mBtnForget;
    private View progress;
    private View mInputLayout;
    private float mWidth, mHeight;
    private LinearLayout mName, mPsw;

    private User user;
    private Handler handler;

    private static final String TAG = "LoginActivity";
    public static  final String URL="http://10.160.203.246:8080/count_people_war_exploded/";
    private EditText login_password,login_usernmae;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);

        mBtnLogin =  findViewById(R.id.main_btn_login);
        mBtnRegist  = findViewById(R.id.main_btn_regist);
        mBtnForget = findViewById(R.id.main_btn_forget);


        progress = findViewById(R.id.layout_progress);
        mInputLayout = findViewById(R.id.input_layout);
        mName = (LinearLayout) findViewById(R.id.input_layout_name);
        mPsw = (LinearLayout) findViewById(R.id.input_layout_psw);

        login_usernmae = findViewById(R.id.login_username);
        login_password = findViewById(R.id.login_password);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 0:
                        Toast.makeText(LoginActivity.this,"用户不存在",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,FgFunctionActivity.class);
                        String usernanme = user.getUsername();
                        Log.i(TAG,"用户名是"+usernanme);
                        intent.putExtra("username", usernanme);
                        startActivity(intent);
                        break;
                    case 2:
                        Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(LoginActivity.this,"异常",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String name = login_usernmae.getText().toString().trim();
               Log.i(TAG,"name======="+name);
                user = new User( login_usernmae.getText().toString().trim(), login_password.getText().toString().trim());

                // Log.i(TAG, "onClick: "+user.getName());
                if (user.getUsername() == null || user.getUsername().equals("")) {
                    Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (user.getPassword() == null || user.getPassword().equals("")) {
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    mWidth = mBtnLogin.getMeasuredWidth();
                    mHeight = mBtnLogin.getMeasuredHeight();
                    // 隐藏输入框
                    mName.setVisibility(View.INVISIBLE);
                    mPsw.setVisibility(View.INVISIBLE);

                    inputAnimator(mInputLayout, mWidth, mHeight);//动画处理

                    new Thread(runnable).start();
                }
            }
        });

        mBtnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegistActivity.class);
                startActivity(intent);
            }
        });


        mBtnForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ForgetPasseordActivity.class);
                startActivity(intent);
            }
        });


    }

    private void inputAnimator(final View view, float w, float h) {

        AnimatorSet set = new AnimatorSet();

        ValueAnimator animator = ValueAnimator.ofFloat(0, w);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view
                        .getLayoutParams();
                params.leftMargin = (int) value;
                params.rightMargin = (int) value;
                view.setLayoutParams(params);
            }
        });
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mInputLayout,
                "scaleX", 1f, 0.5f);
        set.setDuration(2000);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.playTogether(animator, animator2);
        set.start();
        set.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                /**
                 * 动画结束后，先显示加载的动画，然后再隐藏输入框
                 */
                progress.setVisibility(View.VISIBLE);
                progressAnimator(progress);
                mInputLayout.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });
    }
    /**
     * 出现进度动画
     *
     * @param view
     */
    private void progressAnimator(final View view) {
        PropertyValuesHolder animator = PropertyValuesHolder.ofFloat("scaleX",
                0.5f, 1f);
        PropertyValuesHolder animator2 = PropertyValuesHolder.ofFloat("scaleY",
                0.5f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofPropertyValuesHolder(view,
                animator, animator2);
        animator3.setDuration(2000);
        animator3.setInterpolator(new JellyInterpolator());
        animator3.start();

    }

    public  void recovery() {
        progress.setVisibility(View.GONE);
        mInputLayout.setVisibility(View.VISIBLE);
        mName.setVisibility(View.VISIBLE);
        mPsw.setVisibility(View.VISIBLE);

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mInputLayout.getLayoutParams();
        params.leftMargin = 0;
        params.rightMargin = 0;
        mInputLayout.setLayoutParams(params);


        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mInputLayout, "scaleX", 0.5f,1f );
        animator2.setDuration(500);
        animator2.setInterpolator(new AccelerateDecelerateInterpolator());
        animator2.start();
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                Log.i(TAG, "run: ");
                OkHttpClient client = new OkHttpClient();
                Gson gson = new Gson();
                String params = gson.toJson(user);
                String result = OKhttpUtil.postURL(URL + "user/loginUser",params,client);
                Log.i(TAG, "run: "+result);
                Message msg = new Message();
                Log.i(TAG, "run: "+result);
                msg.what = Integer.parseInt(result);

                handler.sendMessage(msg);
                Log.i(TAG, "onClick: "+result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

}


