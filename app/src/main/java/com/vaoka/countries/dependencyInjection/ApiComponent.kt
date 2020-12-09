package com.vaoka.countries.dependencyInjection

import com.vaoka.countries.model.CountriesService
import com.vaoka.countries.viewModel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ComponentApi {

    fun inject(service: CountriesService)

    fun inject(viewModel: ListViewModel)
}