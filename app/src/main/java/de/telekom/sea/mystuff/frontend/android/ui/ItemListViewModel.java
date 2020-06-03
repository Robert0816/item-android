package de.telekom.sea.mystuff.frontend.android.ui;

import androidx.lifecycle.LiveData;

import java.util.List;

import de.telekom.sea.mystuff.frontend.android.api.ApiResponse;
import de.telekom.sea.mystuff.frontend.android.model.Item;
import de.telekom.sea.mystuff.frontend.android.util.MyStuffViewModel;


//holt die Daten aus der Datanbank
public class ItemListViewModel extends MyStuffViewModel {

    public LiveData<ApiResponse<List<Item>>>loadItems(){
        return this.getMyStuffContext().getItemRepo().getAll();

    }
}
