package com.example.topmovies;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.topmovies.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private Fragment fragment = null;
    public static final String FRAG_TAG_POPULAR ="frag-popular";
    public static final String FRAG_TAG_TOP_RATED ="frag-top-rated";
    public static final String FRAG_TAG_SETTINGS ="frag-settings";
    //ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.bottonNavigation.setOnNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        loadPopularMoviesFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_popular:
                loadPopularMoviesFragment();
                return true;
            case R.id.action_top_rated:
                loadTopRatedMoviesFragment();
                return true;
            case R.id.action_settings:
                Toast.makeText(MainActivity.this,"Settings Fragment Clicked",Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

    private void loadTopRatedMoviesFragment() {
        fragment = fragmentManager.findFragmentByTag(FRAG_TAG_TOP_RATED);
        if(fragment != null){
            //If fragment already exists,show the fragment
            fragmentManager.beginTransaction().show(fragment).commit();
        }else {
            //If fragment does not exist,create and add fragment
            fragmentManager.beginTransaction().add(R.id.fragment_container, new MovieFragment(),FRAG_TAG_TOP_RATED).commit();
        }
        //Hide rest of the fragmentsif they exist
        hideFragments(FRAG_TAG_POPULAR,FRAG_TAG_SETTINGS);
    }

    private void loadPopularMoviesFragment() {
        fragment = fragmentManager.findFragmentByTag(FRAG_TAG_POPULAR);
        if(fragment != null){
            //If fragment already exists,show the fragment
            fragmentManager.beginTransaction().show(fragment).commit();
        }else {
            //If fragment does not exist,create and add fragment
            fragmentManager.beginTransaction().add(R.id.fragment_container,new MovieFragment(),FRAG_TAG_POPULAR).commit();
        }
        //Hide rest of the fragment, if they exist
        hideFragments(FRAG_TAG_TOP_RATED,FRAG_TAG_SETTINGS);
    }

    private void loadSettingsFragment()
    {
        fragment = fragmentManager.findFragmentByTag(FRAG_TAG_SETTINGS);
        if(fragment!= null)
        {   //if freagment already exists,show the fragment
            fragmentManager.beginTransaction().show(fragment).commit();
        }
        else
        {
            fragmentManager.beginTransaction().add(R.id.fragment_container, new MovieFragment(), FRAG_TAG_SETTINGS).commit();
        }
        //Hide rest of the fragmentsif they exist
        hideFragments(FRAG_TAG_POPULAR,FRAG_TAG_TOP_RATED);
    }

    private void hideFragments(String... tags) {
        for(String tag : tags)
        {
            fragment= fragmentManager.findFragmentByTag(tag);
            if(fragment!=null)
                fragmentManager.beginTransaction().hide(fragment).commit();
        }
    }
}