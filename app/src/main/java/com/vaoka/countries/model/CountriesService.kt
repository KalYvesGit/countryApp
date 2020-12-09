package com.vaoka.countries.model


import com.vaoka.countries.dependencyInjection.DaggerComponentApi
import io.reactivex.Single
import javax.inject.Inject

class CountriesService {

    @Inject
    lateinit var api: CountriesApi

    init {
        DaggerComponentApi.create().inject(this)

    }

    fun getCountries(): Single<List<Country>> {
        return api.getCountries()
    }

}