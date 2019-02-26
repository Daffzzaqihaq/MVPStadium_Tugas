package com.daffzzaqihaq.mvpstadium.View;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.daffzzaqihaq.mvpstadium.Detail.DetailContract;
import com.daffzzaqihaq.mvpstadium.Detail.DetailPresenter;
import com.daffzzaqihaq.mvpstadium.Model.StadiumData;
import com.daffzzaqihaq.mvpstadium.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements DetailContract.View {

    @BindView(R.id.imgLogoClub)
    ImageView imgLogoClub;
    @BindView(R.id.txtNamaClub)
    TextView txtNamaClub;
    @BindView(R.id.txtDetail)
    TextView txtDetail;

    private ProgressDialog progressDialog;
    private DetailPresenter detailPresenter = new DetailPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        detailPresenter.getDataStadium(bundle);
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading cuk...");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();

    }

    @Override
    public void showDataStadium(List<StadiumData> stadiumData) {
        txtNamaClub.setText(stadiumData.get(0).getStrStadium());
        txtDetail.setText(stadiumData.get(0).getStrStadiumDescription());

        RequestOptions options = new RequestOptions().error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_broken_image);
        Glide.with(this).load(stadiumData.get(0).getStrStadiumThumb()).apply(options).into(imgLogoClub);

    }

    @Override
    public void showFailureMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
