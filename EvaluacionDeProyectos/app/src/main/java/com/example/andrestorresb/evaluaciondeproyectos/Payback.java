package com.example.andrestorresb.evaluaciondeproyectos;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
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
 * {@link Payback.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Payback#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Payback extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;


    private OnFragmentInteractionListener mListener;

    private TableLayout tabla;
    private EditText[][] arregloDatos;

    private Button limpiar,calcular;
    private EditText tasaInt,principal,periodos;
    View v;
    double cumulativeCash;
    public Payback() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment Payback.
     */
    // TODO: Rename and change types and number of parameters
    public static Payback newInstance(String param1) {
        Payback fragment = new Payback();
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


    //Aqui se crea la interface, esto es lo que importa
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_payback, container, false);
        //se le asigna el layout correspondiente a su id
        this.tabla=(TableLayout)v.findViewById(R.id.tabla);

        //asignamos el edittext con su correspondiente id
        this.periodos=(EditText)v.findViewById(R.id.periodosText);
        //le agregamos actionlistener
        this.periodos.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //si se escribe algo y se le da done o listo en el teclado hace lo siguiente
                if (actionId == EditorInfo.IME_ACTION_DONE ) {
                    //creamos la matris con los edittexts, si se le pone en periodos 5, sera matriz[5][3]
                    arregloDatos=new EditText[Integer.parseInt(v.getText().toString())][3];
                    //se hace for anidado para entrar a la matriz, primer for hasta el tamaño de los periodos
                    //segundo for hasta tamaño 3
                    for (int i = 0; i < Integer.parseInt(v.getText().toString()); i++) {
                        //se crea una fila
                        TableRow fila = new TableRow(v.getContext());
                        //se crea un textview
                        TextView num = new TextView(v.getContext());
                        //se le asigna el texto al textview
                        num.setText(i + 1 + "");
                        //metemos el textview a la fila creada en la columna 0
                        fila.addView(num, 0);
                        for (int j = 0; j < 3; j++) {
                            //creamos el edittext
                            EditText cuadros = new EditText(v.getContext());
                            //se los agregamos a las filas en la columna j+1
                            fila.addView(cuadros, j + 1);
                            //agregamos los edittext a la matriz para despues poder manipularla
                            arregloDatos[i][j]=cuadros;
                        }
                        //agregamos la fila a la tabla
                        tabla.addView(fila);
                    }
                    //esto es para ocultar el teclado cuando se da done o listo en periodos
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(periodos.getApplicationWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        //se asigna la tasa de interes a su id correspondiente, tambien el principal y el boton
        this.tasaInt=(EditText)v.findViewById(R.id.interesText);
        this.principal=(EditText)v.findViewById(R.id.principalText);
        this.calcular=(Button)v.findViewById(R.id.calcular);

        //se le pone un click listener al boton
        this.calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //se le asigna el principal en negativo al cumulative
                cumulativeCash+=(Double.parseDouble(principal.getText().toString())*-1);

                /*ignoren esto
                boolean igualesCashflow=false;
                int cuandoSerecupera;*/

                //si la tasa de interes es vacia, nomas sumamons el principal, outflow y inflow
                if (tasaInt.getText().toString().isEmpty()){

                    /*ignoren esto
                    for (int a=0;a>Integer.parseInt(periodos.getText().toString());a++){
                        if ((arregloDatos[a][0].getText().toString()==arregloDatos[a+1][0].getText().toString())
                                && (arregloDatos[a][1].getText().toString()==arregloDatos[a+1][1].getText().toString())){
                            igualesCashflow=true;
                        }
                    }*/

                    //for anidado para entrar a la matriz, for primero es para recorrer las filas
                    //for dos es para escribir el cumulative
                    for(int i=0;i<Integer.parseInt(periodos.getText().toString());i++){
                        for (int j=0;j<1;j++){
                            //si esta vacio los outflows se le pone 0 al dar click en el boton
                            if(arregloDatos[i][j].getText().toString().isEmpty()){
                                arregloDatos[i][j].setText("0");
                            }
                            //si esta vacio los inflows se le pone 0 al dar click en el boton
                            else if(arregloDatos[i][j+1].getText().toString().isEmpty()){
                                arregloDatos[i][j+1].setText("0");
                            }
                            //se hace la suma si los outflow son cero
                            cumulativeCash += (Double.parseDouble(arregloDatos[i][j].getText().toString()) * -1) +
                                        (Double.parseDouble(arregloDatos[i][j + 1].getText().toString()) * 1);
                            //se escribe el cumulativeCashflow
                            arregloDatos[i][j+2].setText(cumulativeCash+" ");

                            /*ignoresn esto
                            if(igualesCashflow){
                                cuandoSerecupera=Integer.parseInt(principal.getText().toString())
                                        /cumulativeCash;
                                if(Integer.parseInt(periodos.getText().toString())<=cuandoSerecupera){
                                    arregloDatos[cuandoSerecupera][2].setTextColor(Color.GREEN);
                                }

                            }*/

                            //se pone verde cuando se recupera la inversion
                            if (cumulativeCash>=0 /*&& igualesCashflow==false*/){
                                arregloDatos[i][j + 2].setTextColor(Color.GREEN);
                            }

                        }
                    }
                }
                //si hay tasa de interes se hace esto
                else{
                    //se saca la tasa de interes en decimal
                    double tasa=(Double.parseDouble(tasaInt.getText().toString()))/100;
                    //for anidado para manipular la matriz
                    for(int i=0;i<Integer.parseInt(periodos.getText().toString());i++){
                        for (int j=0;j<1;j++){
                            //si esta vacio los outflows se le pone 0 al dar click en el boton
                            if(arregloDatos[i][j].getText().toString().isEmpty()){
                                arregloDatos[i][j].setText("0");
                            }
                            //si esta vacio los inflows se le pone 0 al dar click en el boton
                            else if(arregloDatos[i][j+1].getText().toString().isEmpty()){
                                arregloDatos[i][j+1].setText("0");
                            }
                            //se multiplica el principal por la tasa, se suma con el outflow(si es que hay),
                            //se suma con el inflow y luego eso se le suma otra vez al principal para ver
                            //como queda el cumulativecashflow
                            cumulativeCash =(cumulativeCash*tasa) +
                                    (Double.parseDouble(arregloDatos[i][j].getText().toString()) * -1) +
                                        (Double.parseDouble(arregloDatos[i][j + 1].getText().toString()) * 1) + cumulativeCash;
                            //se escribe el cumulativeCashflow
                            arregloDatos[i][j+2].setText(cumulativeCash+" ");
                            //se pone verde cuando se recupera la inversion
                            if (cumulativeCash>=0){
                                arregloDatos[i][j + 2].setTextColor(Color.GREEN);
                            }

                        }
                    }
                }
                cumulativeCash=0;
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
