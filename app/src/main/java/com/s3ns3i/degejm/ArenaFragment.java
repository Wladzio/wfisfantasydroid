package com.s3ns3i.degejm;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ArenaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ArenaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArenaFragment extends Fragment implements SensorEventListener {


//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        DBHelper = new DatabaseHelper(activity);
//    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private SensorManager mSensorManager;
    private Sensor mShake;
    ProgressBar PBhp,PBmana;
    ImageView IVattack,IVblock,IVspecial;
    Player contender;
    Vibrator v;
    MediaPlayer SoundSwordSwoosh, SoundSwordClash, SoundSwordSwing;
    ListView EnemiesLV;
    CombatResolutionDataFromServer fight;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ArenaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArenaFragment newInstance(String param1, String param2) {
        ArenaFragment fragment = new ArenaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    public ArenaFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            v= (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            mSensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
            mShake = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            SoundSwordClash= MediaPlayer.create(getActivity().getApplicationContext(), R.raw.sword_swing);
            IVattack=(ImageView)getActivity().findViewById(R.id.IVattack);
            IVblock=(ImageView)getActivity().findViewById(R.id.IVblock);
            IVspecial=(ImageView)getActivity().findViewById(R.id.IVspecial);
            PBhp=(ProgressBar)getActivity().findViewById(R.id.PBhealth);
            PBmana=(ProgressBar)getActivity().findViewById(R.id.PBmana);
            EnemiesLV=(ListView)getActivity().findViewById(R.id.EnemiesLV);
            fight=new CombatResolutionDataFromServer();


            IVattack.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    // do stuff
                }

            });

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_arena, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }




    @Override
    public void onSensorChanged(SensorEvent event) {


            Float accX = event.values[0];
            Float accY = event.values[1];
            Float accZ = event.values[2];

            if(accX>15||accX<-15 && accY<8 &&accY>-8 && accZ<8&&accZ>-8){
                //Tutaj wsadzimy wysyłanie na server
                v.vibrate(250);

                SoundSwordClash.start();
            }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

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
        public void onFragmentInteraction(Uri uri);
    }

}
