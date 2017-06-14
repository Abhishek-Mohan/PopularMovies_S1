package fastrackm.nanodegree.udacity.abhis.popularmovies_s1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fastrackm.nanodegree.udacity.abhis.popularmovies_s1.models.Movie;

/**
 * Created by abhis on 6/13/2017.
 */

public class MovieDetailActivity extends AppCompatActivity
{
    private ImageView mMovieBackDrop;
    private TextView mMoviePlot;
    private TextView mMovieRating;
    private TextView mMoviedate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        Intent currentMovie = getIntent();
        Movie currentMovieObj = (Movie) currentMovie.getSerializableExtra("movieObject");

        String movieTitle = currentMovieObj.getMtitle();
        String movieBackDrop = currentMovieObj.getmPoster();
        String moviePlot = currentMovieObj.getmPlot();
        double movieRating = currentMovieObj.getmUserRating();
        String movieDate = currentMovieObj.getmReleaseDate();

        getSupportActionBar().setTitle(movieTitle);

        mMovieBackDrop = (ImageView) findViewById(R.id.main_backdrop);
        mMoviePlot = (TextView) findViewById(R.id.plot_summary);
        mMovieRating = (TextView) findViewById(R.id.user_rating);
        mMoviedate = (TextView) findViewById(R.id.release_date);

        Picasso.with(this).load(movieBackDrop).fit().into(mMovieBackDrop);
        mMoviePlot.setText(String.format(getApplicationContext().
                getString(R.string.Plot_Synopsis), moviePlot));
        mMovieRating.setText(String.format(getApplicationContext().
                getString(R.string.Rating), Double.toString(movieRating)));
        mMoviedate.setText(String.format(getApplicationContext().
                getString(R.string.Release_Date), movieDate));





    }
}
