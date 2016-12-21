package myapplication.retrofit.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.squareup.picasso.Picasso;

import java.util.List;

import myapplication.retrofit.MyApplication;
import myapplication.retrofit.R;
import myapplication.retrofit.models.MovieFeed;
import myapplication.retrofit.models.MovieObject;
import myapplication.retrofit.ui.adapter.MarginDecoration;
import myapplication.retrofit.ui.adapter.MovieAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    public static final String TAG = MainActivity.class.getName();

    private EditText mSearch;
    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;
    private List<MovieObject> movieList;

    private String mSearchMovie;
    private int mCurrentPage;
    private int mTotalPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearch = (EditText) findViewById(R.id.search);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRecyclerView.addItemDecoration(new MarginDecoration(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        Picasso.with(this).setIndicatorsEnabled(true);

        mCurrentPage = 1;
        mTotalPages = 0;
    }

    /**
     * Load all movies and then iterates in the list
     */
    private void searchMovies() {
        Call<MovieFeed> call = MyApplication.getInstance().getAPI().getImages(mSearchMovie, mCurrentPage);

        call.enqueue(new Callback<MovieFeed>() {
            @Override
            public void onResponse(Call<MovieFeed> call, Response<MovieFeed> response) {
                mTotalPages = response.body().getTotalResults();
                movieList = response.body().getMovies();

                Log.d(TAG, "Current page: " + mCurrentPage + " of " + mTotalPages);

                mAdapter = new MovieAdapter(movieList, MainActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<MovieFeed> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void search(final View view) {
        mSearchMovie = mSearch.getText().toString();
        searchMovies();
    }

    public void goToLeft(final View v) {
        if (mCurrentPage > 1 && mCurrentPage < mTotalPages) {
            mCurrentPage--;
            searchMovies();
        }
    }

    public void goToRight(final View v) {
        if (mCurrentPage < mTotalPages) {
            mCurrentPage++;
            searchMovies();
        }
    }

}
