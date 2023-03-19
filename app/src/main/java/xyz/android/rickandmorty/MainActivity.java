package xyz.android.rickandmorty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar el RecyclerView con un LinearLayoutManager y un DividerItemDecoration
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CharacterAdapter adapter = new CharacterAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // Configurar el SwipeRefreshLayout
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        // Actualizar la lista al hacer swipe hacia abajo
        swipeRefreshLayout.setOnRefreshListener(this::loadPersonajes);

        // Configurar el botón flotante
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mostrar una animación de carga mientras se espera la actualización
                swipeRefreshLayout.setRefreshing(true);
                loadPersonajes();
            }
        });

        // Cargar los personajes por primera vez
        loadPersonajes();

    }

    private void loadPersonajes() {
        RestClient restClient = RestClient.getInstance();
        restClient.getCharacters(1, new Callback<CharacterResponse>() {
            @Override
            public void onResponse(Call<CharacterResponse> call, Response<CharacterResponse> response) {
                if (response.isSuccessful()) {
                    CharacterResponse characterResponse = response.body();

                    assert characterResponse != null;
                    ArrayList<Character> characters = characterResponse.getResults();
                    CharacterAdapter adapter = new CharacterAdapter(characters);
                    recyclerView.setAdapter(adapter);
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    Log.e("MainActivity", "errores de la respuesta de la API");
                }
            }

            @Override
            public void onFailure(Call<CharacterResponse> call, Throwable t) {
                Log.e("MainActivity", "Error getting characters", t);
            }
        });
    }
}