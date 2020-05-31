package de.telekom.sea.mystuff.frontend.android;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;

import de.telekom.sea.mystuff.frontend.android.ui.ItemListRecyclerViewAdapter;
import de.telekom.sea.mystuff.frontend.android.ui.ItemListViewModel;

public class MainActivity extends AppCompatActivity {
    ItemListRecyclerViewAdapter adapter;
    ItemListViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(ItemListViewModel.class);
        viewModel.initWithApplication(getApplication());
        adapter = new ItemListRecyclerViewAdapter(new ArrayList<>());
        RecyclerView rv = findViewById(R.id.rv_items);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        viewModel.loadItems().observe(this, listApiResponse -> {
            if (listApiResponse.isSuccessful()) {
                adapter.updateList(listApiResponse.body);
            } else {
                MyStuffContext ctx = ((MyStuffApplication)getApplication()).getMyStuffContext();
                ctx.sendInfoMessage("Could not load the list");
            }
        });
    }
}