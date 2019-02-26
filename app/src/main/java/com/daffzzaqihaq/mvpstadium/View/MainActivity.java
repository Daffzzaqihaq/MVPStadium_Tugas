package com.daffzzaqihaq.mvpstadium.View;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.daffzzaqihaq.mvpstadium.Adapter.MainAdapter;
import com.daffzzaqihaq.mvpstadium.Main.MainContract;
import com.daffzzaqihaq.mvpstadium.Main.MainPresenter;
import com.daffzzaqihaq.mvpstadium.Model.StadiumData;
import com.daffzzaqihaq.mvpstadium.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.rv_teams)
    RecyclerView rvTeams;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;

    private ProgressDialog progressDialog;
    private MainPresenter mainPresenter = new MainPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenter.getDataListUser();

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainPresenter.getDataListUser();
            }
        });
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
        swiperefresh.setRefreshing(false);
    }

    @Override
    public void showDataListUser(List<StadiumData> stadiumDataList) {
        rvTeams.setLayoutManager(new LinearLayoutManager(this));
        rvTeams.setAdapter(new MainAdapter(this, stadiumDataList));
    }

    @Override
    public void showFailureMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
