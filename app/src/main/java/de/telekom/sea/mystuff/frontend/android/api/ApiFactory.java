package de.telekom.sea.mystuff.frontend.android.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.telekom.sea.mystuff.frontend.android.BuildConfig;
import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class ApiFactory {

    @Getter
    private final Retrofit retrofit;
    private final String baseRestUrl;
    private final String hostName;
    private final OkHttpClient okHttpClient;

    public ApiFactory() {

        this.hostName = BuildConfig.apifactory_hostname;
        this.baseRestUrl = BuildConfig.apifactory_protocoll + "://" + this.hostName + ":" + BuildConfig.apifactory_port;


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(this.baseRestUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .client(okHttpClient)
                .build();
    }

    /**
     * @param retrofitApiInterface defines the REST interface, must not be null
     * @param <S>
     * @return API instance for performing REST calls, never null
     */
    public <S> S createApi(Class<S> retrofitApiInterface) {
        return retrofit.create(retrofitApiInterface);
    }

}
