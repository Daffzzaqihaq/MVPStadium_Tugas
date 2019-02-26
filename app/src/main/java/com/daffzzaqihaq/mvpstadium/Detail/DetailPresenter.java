package com.daffzzaqihaq.mvpstadium.Detail;

import android.os.Bundle;

import com.daffzzaqihaq.mvpstadium.Model.StadiumResponse;
import com.daffzzaqihaq.mvpstadium.Network.ApiClient;
import com.daffzzaqihaq.mvpstadium.Network.ApiInterface;
import com.daffzzaqihaq.mvpstadium.Utils.Constant;
import com.daffzzaqihaq.mvpstadium.View.DetailActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter implements DetailContract.Presenter {

    private DetailContract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private int id;

    public DetailPresenter(DetailContract.View view){
        this.view = view;
    }

    @Override
    public void getDataStadium(Bundle bundle) {
        if (bundle != null){
            id = Integer.valueOf(bundle.getString(Constant.id));
            view.showProgress();

            Call<StadiumResponse> call = apiInterface.getDetail(id);
            call.enqueue(new Callback<StadiumResponse>() {
                @Override
                public void onResponse(Call<StadiumResponse> call, Response<StadiumResponse> response) {
                    view.hideProgress();
                    if (response.body() != null) {
                        StadiumResponse stadiumResponse = response.body();

                        if (stadiumResponse.getTeams() != null) {
                            view.showDataStadium(stadiumResponse.getTeams());
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
}
