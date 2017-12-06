package com.example.andrestorresb.evaluaciondeproyectos;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Checklist.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Checklist#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Checklist extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private View view;
    private Button guardar;
    private EditText

    editText,
    editText2,
    editText3,
    editText4,
    editText5,
    editText6,
    editText32,
    editText35,
    editText38,
    editText41,
    editText47,
    editText50,
    editText53,
    editText56,
    editText59,
    editText62,
    editText65,
    editText68,
    editText71;

    private OnFragmentInteractionListener mListener;

    public Checklist() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Checklist.
     */
    // TODO: Rename and change types and number of parameters
    public static Checklist newInstance(String param1, String param2) {
        Checklist fragment = new Checklist();
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
        view = inflater.inflate(R.layout.fragment_checklist, container, false);

        this.guardar = (Button) view.findViewById(R.id.GuardarChecklist);
        this.guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                editText = (EditText) view.findViewById(R.id.editText);
                        editText2  = (EditText) view.findViewById(R.id.editText2);
                        editText3  = (EditText) view.findViewById(R.id.editText3);
                        editText4  = (EditText) view.findViewById(R.id.editText4);
                        editText5  = (EditText) view.findViewById(R.id.editText5);
                        editText6 = (EditText) view.findViewById(R.id.editText6);
                        editText32 = (EditText) view.findViewById(R.id.editTex32);
                        editText35 = (EditText) view.findViewById(R.id.editTex35);
                        editText38 = (EditText) view.findViewById(R.id.editTex38);
                        editText41 = (EditText) view.findViewById(R.id.editTex41);
                        editText47 = (EditText) view.findViewById(R.id.editTex47);
                        editText50 = (EditText) view.findViewById(R.id.editTex50);
                        editText53 = (EditText) view.findViewById(R.id.editTex53);
                        editText56 = (EditText) view.findViewById(R.id.editTex56);
                        editText59 = (EditText) view.findViewById(R.id.editTex59);
                        editText62 = (EditText) view.findViewById(R.id.editTex62);
                        editText65 = (EditText) view.findViewById(R.id.editTex65);
                        editText68 = (EditText) view.findViewById(R.id.editTex68);
                        editText71 = (EditText) view.findViewById(R.id.editTex71);


                //Bitmap  bitmap = Bitmap.createBitmap( view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
                //Canvas canvas = new Canvas(bitmap);
                //view.draw(canvas);
                //bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);

                Bitmap b = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas c = new Canvas(b);

                //c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                //view.getDrawingCache();

                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "checklist");
                FileOutputStream outputStream;

                    try {
                        outputStream = new FileOutputStream(file);
                        outputStream.write(System.getProperty("line.separator").getBytes());
                        outputStream.write(editText.getText().toString().getBytes());
                        outputStream.write(System.getProperty("line.separator").getBytes());
                        outputStream.write(editText2.getText().toString().getBytes());
                        outputStream.write(System.getProperty("line.separator").getBytes());

                        outputStream.write(editText3.getText().toString().getBytes());
                        outputStream.write(System.getProperty("line.separator").getBytes());

                        outputStream.write(editText4.getText().toString().getBytes());
                        outputStream.write(System.getProperty("line.separator").getBytes());

                        outputStream.write(editText5.getText().toString().getBytes());
                        outputStream.write(System.getProperty("line.separator").getBytes());

                        outputStream.write(editText6.getText().toString().getBytes());
                        outputStream.write(System.getProperty("line.separator").getBytes());

                        outputStream.write(editText32.getText().toString().getBytes());
                        outputStream.write(System.getProperty("line.separator").getBytes());

                        outputStream.write(editText35.getText().toString().getBytes());
                        outputStream.write(System.getProperty("line.separator").getBytes());

                        outputStream.write(editText38.getText().toString().getBytes());
                        outputStream.write(System.getProperty("line.separator").getBytes());

                        outputStream.write(editText41.getText().toString().getBytes());
                        outputStream.write(System.getProperty("line.separator").getBytes());

                        outputStream.write(editText47.getText().toString().getBytes());
                        outputStream.write(System.getProperty("line.separator").getBytes());

                        outputStream.write(editText50.getText().toString().getBytes());
                        outputStream.write(System.getProperty("line.separator").getBytes());

                        outputStream.write(editText53.getText().toString().getBytes());
                        outputStream.write(System.getProperty("line.separator").getBytes());

                        outputStream.write(editText56.getText().toString().getBytes());
                        outputStream.write(System.getProperty("line.separator").getBytes());

                        outputStream.write(editText59.getText().toString().getBytes());
                        outputStream.write(System.getProperty("line.separator").getBytes());

                        outputStream.write(editText62.getText().toString().getBytes());
                        outputStream.write(System.getProperty("line.separator").getBytes());

                        outputStream.write(editText65.getText().toString().getBytes());
                        outputStream.write(System.getProperty("line.separator").getBytes());

                        outputStream.write(editText68.getText().toString().getBytes());
                        outputStream.write(System.getProperty("line.separator").getBytes());

                        outputStream.write(editText71.getText().toString().getBytes());
                        outputStream.write(System.getProperty("line.separator").getBytes());

                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        });

        return view;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
