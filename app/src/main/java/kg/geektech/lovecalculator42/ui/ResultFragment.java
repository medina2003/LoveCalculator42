package kg.geektech.lovecalculator42.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import kg.geektech.lovecalculator42.R;
import kg.geektech.lovecalculator42.databinding.FragmentMainBinding;
import kg.geektech.lovecalculator42.databinding.FragmentResultBinding;


public class ResultFragment extends Fragment {
    FragmentResultBinding binding;
    NavController navController;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentResultBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String percentage = getArguments().getString("100%","99%");
        binding.result.setText(percentage);

        navController = NavHostFragment.findNavController(this);
        binding.buttonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigateUp();

            }
        });
    }


        }