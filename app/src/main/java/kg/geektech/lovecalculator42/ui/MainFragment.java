package kg.geektech.lovecalculator42.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kg.geektech.lovecalculator42.network.App;
import kg.geektech.lovecalculator42.R;
import kg.geektech.lovecalculator42.databinding.FragmentMainBinding;
import kg.geektech.lovecalculator42.network.LoveModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainFragment extends Fragment {

 FragmentMainBinding binding;
    NavController navController;

 private final String HOST = "love-calculator.p.rapidapi.com";
 public static final String KEY = "5699688dc9msh809376b312571d4p1e9b63jsnf8f87e86c059";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDataFromLaveApi();
        initClickers();
        navController = NavHostFragment.findNavController(this);

    }

    private void initClickers() {
        binding.tryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataFromLaveApi();
            }
        });
    }

    private void getDataFromLaveApi() {
        String firstName = binding.firstNameEd.getText().toString();
        String secondName = binding.secondNameEd.getText().toString();
        App.api.loveCalculator(firstName,secondName,HOST,KEY).enqueue(new Callback<LoveModel>() {
            @Override
            public void onResponse(Call<LoveModel> call, Response<LoveModel> response) {
                if (response.isSuccessful()){
                    Bundle bundle = new Bundle();
                    bundle.putString("nas" , response.body().percentage);

                    Log.e("ololo", "onResponse: "+response.body() );
                    binding.tryBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            navController.navigate(R.id.resultFragment,bundle);
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<LoveModel> call, Throwable t) {
                Log.e("ololo", "onFailure: "+t.getLocalizedMessage());

            }
        });
    }
}