package com.ardovic.weatherappprototype.di;

import com.ardovic.weatherappprototype.network.WeatherApi;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;

import static com.ardovic.weatherappprototype.network.WeatherApi.BASE_URL;

/**
 * @author Polurival on 12.05.2018.
 */
@Module
public abstract class NetworkModule {  //If module has no state it can be converted to abstract for performance benefits

    @Provides
    @Singleton
    public static Retrofit provideRetrofit() { //static methods have better performance benefits

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton //static methods have better performance benefits
    public static WeatherApi provideWeatherApi(Retrofit retrofit) {
        return retrofit.create(WeatherApi.class);
    }
}
