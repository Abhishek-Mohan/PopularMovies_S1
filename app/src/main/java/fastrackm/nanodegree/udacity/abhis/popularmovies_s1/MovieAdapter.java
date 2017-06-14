package fastrackm.nanodegree.udacity.abhis.popularmovies_s1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import fastrackm.nanodegree.udacity.abhis.popularmovies_s1.models.Movie;

import static android.content.ContentValues.TAG;

/**
 * Created by abhis on 6/11/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>
{
    private ArrayList<Movie> mMovieDataTitles;

    /*
     * An on-click handler that we've defined to make it easy for an Activity to interface with
     * our RecyclerView
     */
    private final MovieAdapterOnClickHandler mClickHandler;
    private Context mContext;



    /**
     * The interface that receives onClick messages.
     */
    public interface MovieAdapterOnClickHandler
    {
        // Need to pass in information like title (string),
        // image thumbnail (url), plot (string), user rating (string), and release date (string)
        void onClick();
    }

    MovieAdapter(Context context, MovieAdapterOnClickHandler clickHandler)
    {
        mContext = context;
        this.mClickHandler = clickHandler;
    }

    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int i)
    {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_grid, parent, false);
        return new MovieAdapterViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder movieAdapterViewHolder, int i)
    {
        // Need to set the image here from an array of images.
        // Here is probably where i need to start appending the images url
        // to make sure they work with picasso.
        Movie currentMovie = mMovieDataTitles.get(i);
        movieAdapterViewHolder.mMovieTitle.setText(currentMovie.getMtitle());
        Log.d(TAG, currentMovie.getmPoster());
        Picasso.with(mContext).load(currentMovie.getmPoster()).into(movieAdapterViewHolder.mMoviePoster);


    }

    @Override
    public int getItemCount()
    {
        if (mMovieDataTitles == null)
        {
            return 0;
        }
        return mMovieDataTitles.size();
    }

    void setMovieData(ArrayList<Movie> movieData)
    {
        mMovieDataTitles = movieData;
        notifyDataSetChanged();
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        ImageView mMoviePoster;
        TextView mMovieTitle;

        MovieAdapterViewHolder(View itemView)
        {
            super(itemView);
            mMoviePoster = (ImageView) itemView.findViewById(R.id.moviePoster);
            mMovieTitle = (TextView) itemView.findViewById(R.id.country_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            int adapterPosition = getAdapterPosition();
            Movie currentMovie = mMovieDataTitles.get(adapterPosition);
            Toast.makeText(mContext, "Going to Movie Detail", Toast.LENGTH_SHORT).show();
            mClickHandler.onClick();
            Intent sendToMovieActivity = new Intent(mContext, MovieDetailActivity.class);
            sendToMovieActivity.putExtra("movieObject", currentMovie);
            mContext.startActivity(sendToMovieActivity);
        }
    }

}
