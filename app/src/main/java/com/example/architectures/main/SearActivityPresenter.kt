package com.example.architectures.main

import com.example.architectures.model.Movie
import com.example.architectures.model.RemoteDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class SearActivityPresenter (private val searActivityInterface: SearhActivityContract.SearchActivityView, private val remoteDataSource: RemoteDataSource): SearhActivityContract.SearchActivityPresenterInterface{

    private val listObservable: DisposableObserver<List<Movie>>
                get() = object: DisposableObserver<List<Movie>>(){
                    override fun onComplete() {
                        searActivityInterface.success("Fetched Movies")
                    }

                    override fun onNext(t: List<Movie>) {
                        if(t.size >= 1) searActivityInterface.getListMovies(t)
                    }

                    override fun onError(e: Throwable) {
                        searActivityInterface.error(e.localizedMessage)
                    }
                }


    override fun fetchMovieList(query: String) {
        remoteDataSource.searchResultsObservable(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.results
            }
            .subscribe(listObservable)
    }

}