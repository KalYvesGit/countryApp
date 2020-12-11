package com.vaoka.countries.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vaoka.countries.dependencyInjection.DaggerComponentApi
import com.vaoka.countries.model.CountriesService
import com.vaoka.countries.model.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ListViewModel: ViewModel() {

    @Inject
    lateinit var countriesService: CountriesService
    init {
        DaggerComponentApi.create().inject(this)
    }
    private val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    //done this way so that outside callers do not know how the actual operation is performed.
     fun refresh(){
        fetchCountries()
    }

    // does the fetching from the backend
    private fun fetchCountries(){
        loading.value = true
        disposable.add(countriesService.getCountries()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<List<Country>>(){
                override fun onSuccess(value: List<Country>?) {
                    countries.value = value
                    countryLoadError.value = false
                    loading.value = false
                }

                override fun onError(e: Throwable?) {
                    countryLoadError.value = true
                    loading.value = false
                }

            })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}