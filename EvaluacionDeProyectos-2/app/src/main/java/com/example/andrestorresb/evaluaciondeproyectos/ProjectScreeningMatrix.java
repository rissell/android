package com.example.andrestorresb.evaluaciondeproyectos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ProjectScreeningMatrix extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            this.order();
        }

        public void order(){

            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/Download/Project-Screening-Matrix.xls");
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file),"application/vnd.ms-excel");
            startActivity(intent);

            try{
                InputStream is = new FileInputStream(file);
                Workbook wb = Workbook.getWorkbook(is);
                Sheet s = wb.getSheet(0);
                String xx = "";

                Cell z = s.getCell(0,0);
                xx = z.getContents();
                display(xx);

            }catch (Exception e){
                e.printStackTrace();
            }



        }

        public void display(String value){
            TextView x = (TextView) findViewById(R.id.textoExcel);
            x.setText(value);

        }



}
