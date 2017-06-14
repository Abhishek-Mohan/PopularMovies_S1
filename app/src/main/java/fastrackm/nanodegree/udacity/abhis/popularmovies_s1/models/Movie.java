package fastrackm.nanodegree.udacity.abhis.popularmovies_s1.models;

import java.io.Serializable;

/**
 * Created by abhis on 6/13/2017.
 */

public class Movie implements Serializable
{
    private String mtitle;
    private String mPoster;
    private String mBackDrop;
    private String mPlot;
    private double mUserRating;
    private String mReleaseDate;

    public Movie(String mtitle, String mPoster, String mBackDrop, String mPlot, double mUserRating, String mReleaseDate) {
        this.mtitle = mtitle;
        this.mPoster = mPoster;
        this.mBackDrop = mBackDrop;
        this.mPlot = mPlot;
        this.mUserRating = mUserRating;
        this.mReleaseDate = mReleaseDate;
    }

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getmPoster() {
        return mPoster;
    }

    public void setmPoster(String mPoster) {
        this.mPoster = mPoster;
    }

    public String getmBackDrop()
    {
        return mBackDrop;
    }

    public void setmBackDrop(String mBackDrop)
    {
        this.mBackDrop = mBackDrop;
    }

    public String getmPlot() {
        return mPlot;
    }

    public void setmPlot(String mPlot) {
        this.mPlot = mPlot;
    }

    public double getmUserRating() {
        return mUserRating;
    }

    public void setmUserRating(double mUserRating) {
        this.mUserRating = mUserRating;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }
}
