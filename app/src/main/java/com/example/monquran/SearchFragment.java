package com.example.monquran;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class SearchFragment {
    private EditText editTextSearch;
    private ImageButton imageButtonSearch;
    private ImageView imageViewSearch;

    public EditText getEditTextSearch() {
        return editTextSearch;
    }

    public void setEditTextSearch(EditText editTextSearch) {
        this.editTextSearch = editTextSearch;
    }

    public ImageButton getImageButtonSearch() {
        return imageButtonSearch;
    }

    public void setImageButtonSearch(ImageButton imageButtonSearch) {
        this.imageButtonSearch = imageButtonSearch;
    }

    public ImageView getImageViewSearch() {
        return imageViewSearch;
    }

    public void setImageViewSearch(ImageView imageViewSearch) {
        this.imageViewSearch = imageViewSearch;
    }
    public void setListener(IOnSelectedRecitateur listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, null);
        listViewRecitateur = v.findViewById(R.id.listViewReciters);
        listViewRecitateur.setOnItemClickListener(this);

        editTextSearch = v.findViewById(R.id.editTextSearch);
        imageButtonSearch = v.findViewById(R.id.imageButtonSearch);
        imageButtonSearch.setOnClickListener(this);

        return v;
    }

}
