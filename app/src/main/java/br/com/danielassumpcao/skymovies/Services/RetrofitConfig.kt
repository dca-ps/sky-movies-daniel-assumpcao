package br.com.danielassumpcao.skymovies.Services

import br.com.danielassumpcao.skymovies.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


class RetrofitConfig {

    var retrofit: Retrofit;

    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.connectTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES);
        httpClient.addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response? {
                val request: Request = chain
                    .request()
                    .newBuilder()
                    .addHeader("x-rapidapi-key", BuildConfig.RAPID_API_KEY)
                    .addHeader("x-rapidapi-host", BuildConfig.RAPID_API_HOST)
                    .build()
                return chain.proceed(request)
            }
        })

        this.retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.HOST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()

    }

    fun getMoviesService(): MoviesService {

        return this.retrofit.create(MoviesService::class.java)

    }

}