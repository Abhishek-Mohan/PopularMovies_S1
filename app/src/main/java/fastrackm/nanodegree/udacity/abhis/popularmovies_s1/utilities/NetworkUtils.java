package fastrackm.nanodegree.udacity.abhis.popularmovies_s1.utilities;

/**
 * Created by abhis on 6/12/2017.
 */

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import fastrackm.nanodegree.udacity.abhis.popularmovies_s1.R;

/**
 * These utilities will be used to communicate with the movie servers.
 */
public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    //private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";

    // Need to append the api key to the end of this in the string resource
    private static final String POPULAR_MOVIES = "https://api.themoviedb.org/3/movie/popular?api_key=";

    // Need to append the api key to the end of this in the string resource
    private static final String TOP_RATED = "https://api.themoviedb.org/3/movie/top_rated?api_key=";

    /**
     * Builds the URL used to talk to the weather server using a location. This location is based
     * on the query capabilities of the weather provider that we are using.
     *
     */
    public static URL buildUrl(Context context, String neededQuery)
    {
        if (neededQuery.equals("popular"))
        {

            String urlWithString = POPULAR_MOVIES.concat(context.getString(R.string.themoviedb_api_key));
            Uri builtUri = Uri.parse(urlWithString).buildUpon()
                    .build();

            URL url = null;
            try {
                url = new URL(builtUri.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            Log.v(TAG, "Built URI " + url);

            return url;
        }
        else
        {
            String urlWithString = TOP_RATED.concat("59dfa2d6fd373bda96b580ece2a59bb6");
            Uri builtUri = Uri.parse(urlWithString).buildUpon()
                    .build();

            URL url = null;
            try {
                url = new URL(builtUri.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            Log.v(TAG, "Built URI " + url);

            return url;
        }
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}