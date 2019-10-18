package com.cndd.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.cndd.midterm.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edta, edtb, edtc;
    private ArrayList<String> result;
    private ListView lvResult;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edta = (EditText) findViewById(R.id.edt_a);
        edtb = (EditText) findViewById(R.id.edt_b);
        edtc = (EditText) findViewById(R.id.edt_c);
        lvResult = (ListView) findViewById(R.id.lv_result);

        result = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, result);
        lvResult.setAdapter(adapter);

        lvResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String tmpResult = (String) adapterView.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("result", tmpResult);
                bundle.putSerializable("a", edta.getText().toString());
                bundle.putSerializable("b", edta.getText().toString());
                intent.putExtra("package", bundle);

                startActivity(intent);
            }
        });

        lvResult.setLongClickable(true);
        lvResult.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View arg1, final int pos, long id) {
                result.remove(pos);
                adapter.notifyDataSetChanged();
                return true;
            }
        });


    }

    public void solve(View view){
        String inA = edta.getText().toString();
        String inB = edtb.getText().toString();
        String inC = edtc.getText().toString();
        if (inA == null || inB == null || inC == null){
            Toast.makeText(MainActivity.this, " Các trường dữ liệu không được bỏ trống! ", Toast.LENGTH_LONG);
        } else {
            try{
                int a = Integer.parseInt(inA);
                int b = Integer.parseInt(inB);
                int c = Integer.parseInt(inC);

                String resultTmp = a + "x^2 + " + b + "x + " + c + " = 0 ";

                float delta = (float) (Math.pow(b, 2) - 4 * a * c);
                if (delta < 0){
                    resultTmp += "vô nghiệm";
                } else {
                    if (delta > 0){
                        float x1 = (float) (( -b + Math.sqrt(delta)) / 2 * a);
                        float x2 = (float) (( -b - Math.sqrt(delta)) / 2 * a);
                        resultTmp += "có 2 nghiệm x1 = " + x1 + "; x2 = " + x2;
                    }else{
                        float x = -b / 2 * a;
                        resultTmp += "có nghiệm kép x1 = x2 = " + x;
                    }
                    result.add(resultTmp);
                    adapter.notifyDataSetChanged();
                }
            }catch (NumberFormatException e){
                Toast.makeText(MainActivity.this, "Bạn phải nhập vào một số! ", Toast.LENGTH_LONG);
            }
        }

    }

    public void clear(View view){
        edta.setText("");
        edtb.setText("");
        edtc.setText("");
    }
}
