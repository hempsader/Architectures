package com.example.architectures.main

import com.example.architectures.model.LocalDataSource
import com.example.architectures.model.Movie
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val viewInterface: MainContract.ViewInterface, private val dataSource: LocalDataSource): MainContract.PresenterInterface {
    private val compositeDisposable = CompositeDisposable()
    private val myMoviesObservable: Observable<List<Movie>>
        get() = dataSource.allMovies

    private val observer: DisposableObserver<List<Movie>>
        get() = object : DisposableObserver<List<Movie>>() {
            override fun onComplete() {

            }

            override fun onNext(listMovies: List<Movie>) {
                if(listMovies == null || listMovies.size == 0){
                    viewInterface.displayNoMovies()
                }else{
                    viewInterface.displayMovies(listMovies)
                }
            }

            override fun onError(e: Throwable) {
                viewInterface.displayError(e.localizedMessage)
            }
        }

    override fun getMoviesList() {
        val myMoviesDisposable = myMoviesObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(observer)
        compositeDisposable.add(myMoviesDisposable)
    }
}