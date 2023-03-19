package xyz.android.rickandmorty;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private List<Character> characters;

    public CharacterAdapter(List<Character> characters) {
        this.characters = characters;
    }

    @NonNull
    @Override
    public CharacterAdapter.CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_item, parent, false);
        CardView cardView = view.findViewById(R.id.card_view);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterAdapter.CharacterViewHolder holder, int position) {
        Character character = characters.get(position);
        holder.nameTextView.setText(character.getName());
        holder.statusTextView.setText(character.getStatus());
        holder.speciesTextView.setText(character.getSpecies());
        holder.genderTextView.setText(character.getGender());
        Glide.with(holder.itemView.getContext()).load(character.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView nameTextView;
        public TextView statusTextView;
        public TextView speciesTextView;
        public TextView genderTextView;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            statusTextView = itemView.findViewById(R.id.status_text_view);
            speciesTextView = itemView.findViewById(R.id.species_text_view);
            genderTextView = itemView.findViewById(R.id.gender_text_view);

            // Set Material Design styles
            CardView cardView = itemView.findViewById(R.id.card_view);
            cardView.setCardElevation(8);
            cardView.setRadius(16);
        }
    }
}
