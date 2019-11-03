package com.example.shuttletime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv_left_team,
            tv_right_team,
            tv_ser_man;
    private Button btn_pointUp,
            btn_serOut;
    private int left_count, right_count, flag;
    private ServingTeam servingTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        left_count = 0;
        right_count = 0;
        flag = 0;
        servingTeam = ServingTeam.First;
        tv_left_team = findViewById(R.id.tv_left_team);
        tv_right_team = findViewById(R.id.tv_rigth_team);
        tv_ser_man = findViewById(R.id.serve_man);
        btn_pointUp = findViewById(R.id.btn_pointUp);
        btn_serOut = findViewById(R.id.btn_serOut);

        btn_pointUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (servingTeam == ServingTeam.First) {
                    tv_left_team.setText(Integer.toString(++left_count));
                } else {
                    tv_right_team.setText(Integer.toString(++right_count));
                }
            }
        });
        btn_serOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag++;
                if (flag == 1) {
                    tv_ser_man.setText(R.string.second_man);
                } else { //means flag == 2
                    tv_ser_man.setText(R.string.first_man);
                    flag = 0;

                    if (servingTeam == ServingTeam.First)
                        servingTeam = ServingTeam.Second;
                    else
                        servingTeam = ServingTeam.First;
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.newGameMenu:
                servingTeam = ServingTeam.First;
                right_count = 0;
                left_count = 0;
                flag = 0;
                tv_ser_man.setText(R.string.first_man);
                tv_left_team.setText(Integer.toString(left_count));
                tv_right_team.setText(Integer.toString(right_count));
                return true;
            case R.id.sideChangeMenu:
            {
                int temp = left_count;
                left_count = right_count;
                right_count = temp;
                tv_left_team.setText(Integer.toString(left_count));
                tv_right_team.setText(Integer.toString(right_count));
                if(servingTeam == ServingTeam.First){
                    servingTeam = ServingTeam.Second;
                }else{
                    servingTeam = ServingTeam.First;
                }
            }
            default:
                super.onOptionsItemSelected(item);
        }
        return false;
    }
}
