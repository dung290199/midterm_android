package com.cndd.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private TextView tvResult, tvKQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvResult = (TextView) findViewById(R.id.tv_result);
        tvKQ = (TextView) findViewById(R.id.tv_kq);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("package");
            String result = (String) bundle.getSerializable("result");
            tvResult.setText(result);

            String inA = (String) bundle.getSerializable("a");
            String inB = (String) bundle.getSerializable("b");

            int a = Integer.parseInt(inA);
            int b = Integer.parseInt(inB);

            String ketq = "Phương trình f'(x) = 0 ";

            if (a == 0){
                ketq += "không có nghiệm";
            } else {
                float x = -b / 2 * a;
                ketq += "có nghiệm là " + x;
            }
            tvKQ.setText(ketq);
        }

    }
}
