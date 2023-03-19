package xyz.android.rickandmorty;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RickAndMortyApi {
    @GET("character")
    Call<CharacterResponse> getCharacters(@Query("page") int page);
}
