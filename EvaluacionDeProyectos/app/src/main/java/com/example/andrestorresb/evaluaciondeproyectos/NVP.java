package com.example.andrestorresb.evaluaciondeproyectos;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NVP.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NVP#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NVP extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";


    // TODO: Rename and change types of parameters
    private String mParam1;

    private View v;
    private Button calcular;
    private EditText periodos,principal,tasaInt,taxRate;
    EditText[][] arregloDatos;
    private TableLayout tabla;
    double cumulativeCash;
    double netCashflow;
    double npv;
    private OnFragmentInteractionListener mListener;

    public NVP() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.

     * @return A new instance of fragment NVP.
     */
    // TODO: Rename and change types and number of parameters
    public static NVP newInstance(String param1, String param2) {
        NVP fragment = new NVP();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_nvp, container, false);
        //la tabla
        this.tabla=(TableLayout)v.findViewById(R.id.tablaNVP);

        //los periodos y se le agrega un listener
        this.periodos=(EditText)v.findViewById(R.id.peridodosNVPText);
        this.periodos.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE ) {
                /* If the action is a key-up event on the return key, do something */
                    //se crea el arreglo de filas periodo + 1 con 4 columnas
                    //for anidado para manipulaar la matriz
                    arregloDatos=new EditText[Integer.parseInt(v.getText().toString())+1][4];
                    for (int i = 0; i < Integer.parseInt(v.getText().toString()); i++) {
                        TableRow fila = new TableRow(v.getContext());
                        TextView num = new TextView(v.getContext());
                        num.setText(i + 1 + "");
                        fila.addView(num, 0);
                        for (int j = 0; j < 4; j++) {
                            EditText cuadros = new EditText(v.getContext());
                            fila.addView(cuadros, j + 1);
                            arregloDatos[i][j]=cuadros;
                        }
                        tabla.addView(fila);
                    }
                    //se crea hasta al final una fila nueva para meter el npv
                    TableRow fila = new TableRow(v.getContext());
                    TextView num = new TextView(v.getContext());
                    num.setText("NPV:");
                    fila.addView(num,0);
                    EditText cuadros = new EditText(v.getContext());
                    fila.addView(cuadros, 1);
                    arregloDatos[Integer.parseInt(v.getText().toString())][0]=cuadros;
                    tabla.addView(fila);
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(periodos.getApplicationWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        this.principal=(EditText)v.findViewById(R.id.principalNVPText);
        this.tasaInt=(EditText)v.findViewById(R.id.tasaInteresNVOPText);
        this.taxRate=(EditText)v.findViewById(R.id.taxRateNVPText);

        this.calcular=(Button)v.findViewById(R.id.calcularNVP);
        this.calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //se le asigna el principal en negativo al cumulative
                cumulativeCash+=(Double.parseDouble(principal.getText().toString())*-1);

                //si el tax de interes es vacia, nomas sumamons los cashflows por periodo/el interFes^periodo
                if (taxRate.getText().toString().isEmpty()){
                    double interest=((Double.parseDouble(tasaInt.getText().toString()))/100)+1;

                    //for anidado para entrar a la matriz,
                    //for primero es para recorrer las filas
                    //for dos es para escribir el cumulative y el netcashflow
                    for(int i=0;i<Integer.parseInt(periodos.getText().toString());i++){
                        for (int j=0;j<1;j++){
                            //si esta vacio los outflows se le pone 0 al dar click en el boton
                            if(arregloDatos[i][j].getText().toString().isEmpty() && arregloDatos[i][j+1].getText().toString().isEmpty()){
                                netCashflow=Double.parseDouble(arregloDatos[i][j+2].getText().toString());
                            }
                            else {
                                if(arregloDatos[i][j].getText().toString().isEmpty()){
                                    arregloDatos[i][j].setText("0");
                                }
                                //si esta vacio los inflows se le pone 0 al dar click en el boton
                                else if(arregloDatos[i][j+1].getText().toString().isEmpty()){
                                    arregloDatos[i][j+1].setText("0");
                                }
                                //suma por periodo para sacar el netcashfflow
                                netCashflow = (Double.parseDouble(arregloDatos[i][j].getText().toString()) * -1) +
                                        (Double.parseDouble(arregloDatos[i][j + 1].getText().toString()) * 1);
                            }
                            arregloDatos[i][j+2].setText(netCashflow+" ");
                            //se hace la suma para sacar el cumulative
                            cumulativeCash += netCashflow;
                            //se escribe el cumulativeCashflow
                            arregloDatos[i][j+3].setText(cumulativeCash+" ");

                            npv+=(netCashflow/(Math.pow(interest,i+1)));
                            //se pone rojo cuando es negativa la inversion
                            if (cumulativeCash<=0 /*&& igualesCashflow==false*/){
                                arregloDatos[i][j + 3].setTextColor(Color.RED);
                            }

                        }
                    }
                    //se pinta verde si es positivo el npv
                    if(npv+(Double.parseDouble(principal.getText().toString())*-1)>0) {
                        arregloDatos[Integer.parseInt(periodos.getText().toString())][0].setTextColor(Color.GREEN);
                    }
                    //se escribe el npv
                    arregloDatos[Integer.parseInt(periodos.getText().toString())][0]
                                .setText(npv+(Double.parseDouble(principal.getText().toString())*-1)+" ");


                }
                //si hay tax de interes se hace esto
                else{
                    //se saca la tasa de interes en decimal + 1 y el 1-tax en decimal
                    double interest=((Double.parseDouble(tasaInt.getText().toString()))/100)+1;
                    double tax=1-((Double.parseDouble(taxRate.getText().toString()))/100);

                    //for anidado para manipular la matriz
                    for(int i=0;i<Integer.parseInt(periodos.getText().toString());i++){
                        for (int j=0;j<1;j++){
                            if(arregloDatos[i][j].getText().toString().isEmpty() && arregloDatos[i][j+1].getText().toString().isEmpty()){
                                netCashflow=Double.parseDouble(arregloDatos[i][j+2].getText().toString());
                            }
                            else {
                                if(arregloDatos[i][j].getText().toString().isEmpty()){
                                    arregloDatos[i][j].setText("0");
                                }
                                //si esta vacio los inflows se le pone 0 al dar click en el boton
                                else if(arregloDatos[i][j+1].getText().toString().isEmpty()){
                                    arregloDatos[i][j+1].setText("0");
                                }
                                //suma por periodo para sacar el netcashfflow
                                netCashflow = (Double.parseDouble(arregloDatos[i][j].getText().toString()) * -1) +
                                        (Double.parseDouble(arregloDatos[i][j + 1].getText().toString()) * 1);
                            }
                            arregloDatos[i][j+2].setText(netCashflow+" ");
                            //se hace la suma para sacar el cumulative
                            cumulativeCash += netCashflow;
                            //se escribe el cumulativeCashflow
                            arregloDatos[i][j+3].setText(cumulativeCash+" ");

                            npv+=(netCashflow/(Math.pow(interest,i+1)));
                            //se pone rojo cuando es negativo
                            if (cumulativeCash<=0 /*&& igualesCashflow==false*/){
                                arregloDatos[i][j + 3].setTextColor(Color.RED);
                            }

                        }
                    }
                    //se multiplica el npv por el tax0
                    npv*=tax;
                    //si es positivo es verde
                    if (npv + (tax * (Double.parseDouble(principal.getText().toString())*-1))>0) {
                        arregloDatos[Integer.parseInt(periodos.getText().toString())][0].setTextColor(Color.GREEN);
                    }
                    //se escribe el npv
                    arregloDatos[Integer.parseInt(periodos.getText().toString())][0]
                                .setText(npv+(tax*(Integer.parseInt(principal.getText().toString())*-1))+" ");

                }
                npv=0;
                cumulativeCash=0;
                netCashflow=0;
            }
        });
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void addFragmentListener(OnFragmentInteractionListener ofil) {
        mListener=ofil;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
