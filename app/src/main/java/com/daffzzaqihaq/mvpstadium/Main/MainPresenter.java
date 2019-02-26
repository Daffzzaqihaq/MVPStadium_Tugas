package com.daffzzaqihaq.mvpstadium.Main;

import com.daffzzaqihaq.mvpstadium.Model.StadiumResponse;
import com.daffzzaqihaq.mvpstadium.Network.ApiClient;
import com.daffzzaqihaq.mvpstadium.Network.ApiInterface;
import com.daffzzaqihaq.mvpstadium.Utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter{

    private  MainContract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataListUser() {
        view.showProgress();
        Call<StadiumResponse> call = apiInterface.getStadium(Constant.s, Constant.c );
        call.enqueue(new Callback<StadiumResponse>() {
            @Override
            public void onResponse(Call<StadiumResponse> call, Response<StadiumResponse> response) {
                view.hideProgress();
                if(response.body() != null){
                    StadiumResponse stadiumResponse = response.body();

                    if (stadiumResponse.getTeams() != null){
                        view.showDataListUser(stadiumResponse.getTeams());
                    }
                }
            }

            @Override
            public void onFailure(Call<StadiumResponse> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());
            }
        });

    }
}
