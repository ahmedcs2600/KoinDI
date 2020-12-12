package com.app.koindi.di

import com.app.koindi.global.BASE_URL
import com.app.koindi.models.responses.MovieModel
import com.app.koindi.network.MovieApiService
import com.app.koindi.network.datasource.details.DetailRepository
import com.app.koindi.network.datasource.details.DetailRepositoryImp
import com.app.koindi.network.datasource.home.HomeRepository
import com.app.koindi.network.datasource.home.HomeRepositoryImp
import com.app.koindi.ui.details.DetailsViewModel
import com.app.koindi.ui.home.HomeViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)
    }

    single {
        OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(get<Interceptor>(qualifier = StringQualifier("logging_interceptor")))
            .build()
    }
}

val interceptorModule = module {
    factory<Interceptor>(named("logging_interceptor")) {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}

val repositoryModule = module {
    factory<DetailRepository> { DetailRepositoryImp(get()) }
    viewModel { (movieItem: MovieModel) -> DetailsViewModel(get(), movieItem) }
}

val homeModule = module {
    factory<HomeRepository> { HomeRepositoryImp(get()) }
    viewModel { HomeViewModel(get()) }
}

val appModules = listOf(networkModule,interceptorModule,repositoryModule,homeModule)