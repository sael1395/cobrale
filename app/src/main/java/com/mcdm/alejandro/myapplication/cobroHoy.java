package com.mcdm.alejandro.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link cobroHoy.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class cobroHoy extends Fragment {

    private OnFragmentInteractionListener mListener;
    static private final String TAG ="cobroHoy";
    ViewPager viewPager;
    TabLayout tabLayout;

    public cobroHoy() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_cobro_hoy, container, false);
        tabLayout = (TabLayout)view.findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager = (ViewPager)view.findViewById(R.id.viewpager);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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

    @Override
    public void onResume () {
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iniciarTabs();
    }

    public void iniciarTabs(){
        //Add tabs icon with setIcon() or simple text with .setText()
        tabLayout.addTab(tabLayout.newTab().setText("HOY").setIcon(R.drawable.ic_hoy));
        tabLayout.addTab(tabLayout.newTab().setText("DEBEN").setIcon(R.drawable.ic_deben));
        tabLayout.addTab(tabLayout.newTab().setText("CLIENTES").setIcon(R.drawable.ic_cliente));
        tabLayout.addTab(tabLayout.newTab().setText("HISTORIAL").setIcon(R.drawable.ic_historial));
        tabLayout.addTab(tabLayout.newTab().setText("ESTADISTICAS").setIcon(R.drawable.ic_estadisticas));
        tabLayout.addTab(tabLayout.newTab().setText("CONFIGURACION").setIcon(R.drawable.ic_configuracion));

        //Add fragments
        PagerAdapter adapter = new PagerAdapter(getFragmentManager());
        adapter.addFragment(new hoy());
        adapter.addFragment(new deben());
        adapter.addFragment(new clientes());
        adapter.addFragment(new historial());
        adapter.addFragment(new estadisticas());
        adapter.addFragment(new configuracion());

        //Setting adapter
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    class PagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragments = new ArrayList<>();

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment) {
            mFragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

}
