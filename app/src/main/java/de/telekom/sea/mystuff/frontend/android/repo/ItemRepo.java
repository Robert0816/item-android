package de.telekom.sea.mystuff.frontend.android.repo;

import androidx.lifecycle.LiveData;

import java.util.List;

import de.telekom.sea.mystuff.frontend.android.api.ApiResponse;
import de.telekom.sea.mystuff.frontend.android.api.ItemApi;
import de.telekom.sea.mystuff.frontend.android.model.Item;

public class ItemRepo {

    private ItemApi api;

    public ItemRepo(ItemApi api) {
        this.api = api;
    }

    public LiveData<ApiResponse<List<Item>>> getAll() {
        return this.api.getAll();
    }
}
