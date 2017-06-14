package fastrackm.nanodegree.udacity.abhis.popularmovies_s1;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import fastrackm.nanodegree.udacity.abhis.popularmovies_s1.models.Movie;
import fastrackm.nanodegree.udacity.abhis.popularmovies_s1.utilities.NetworkUtils;
import fastrackm.nanodegree.udacity.abhis.popularmovies_s1.utilities.themoviedbJsonUtils;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler
{
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private GridLayoutManager mLayoutManager;
    private MovieAdapter mAdapter;
    private Context mContext;
    private String defaultList = "popular";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Popular Movies");

        mContext = getApplicationContext();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mLayoutManager = new GridLayoutManager(mContext, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MovieAdapter(mContext, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);

        loadMovieData(defaultList);

    }

    private void loadMovieData(String typeOfMovies)
    {
        new FetchMovieTask().execute(typeOfMovies);
    }

    public class FetchMovieTask extends AsyncTask<String, Void, ArrayList<Movie>>
    {

        @Override
        protected ArrayList<Movie> doInBackground(String... params)
        {
            if (params.length == 0)
            {
                return null;
            }

            String typeOfMovies = params[0];

            URL movieRequestURL = NetworkUtils.buildUrl(mContext, typeOfMovies);

            try
            {
                String jsonMovieResponse = NetworkUtils.getResponseFromHttpUrl(movieRequestURL);

                return themoviedbJsonUtils.getMovieTitles(jsonMovieResponse);
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return null;
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movieData)
        {
            if (movieData != null)
            {
                mAdapter.setMovieData(movieData);
            }
            else
            {
                Log.d(TAG, "this failed terribly");
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            Toast.makeText(mContext, "Work in Progress", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.popular_movies)
        {
            loadMovieData("popular");
            return true;
        }
        if (id == R.id.top_rated)
        {
            loadMovieData("top");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick() {

    }
}
