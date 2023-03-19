package xyz.android.rickandmorty;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/";

    private static RestClient instance;
    private final RickAndMortyApi rickAndMortyApi;

    private RestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        rickAndMortyApi = retrofit.create(RickAndMortyApi.class);
    }

    public static RestClient getInstance() {
        if (instance == null) {
            instance = new RestClient();
        }

        return instance;
    }

    public void getCharacters(int page, Callback<CharacterResponse> callback) {
        Call<CharacterResponse> call = rickAndMortyApi.getCharacters(page);
        call.enqueue(callback);
    }
}
