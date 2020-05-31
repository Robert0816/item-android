package de.telekom.sea.mystuff.frontend.android;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import de.telekom.sea.mystuff.frontend.android.ui.ItemListRecyclerViewAdapter;
import de.telekom.sea.mystuff.frontend.android.ui.ItemListViewModel;


public class MainActivity extends AppCompatActivity {

//    private DirectChatListViewModel viewModel;
//    private DirectChatListAdapter directChatListAdapter;

    private ItemListViewModel itemListViewModel;
    private ItemListRecyclerViewAdapter itemListRecyclerViewAdapter;
    private RecyclerView itemList;


//   MyListRideFragment (Mayo)
/*    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(MyListViewModel.class);
        viewModel.initWithApplication(getActivity().getApplication());
        ridesListAdapter = new RidesListAdapter(new ArrayList<>(), this);

        ridesSearchList.setLayoutManager(new LinearLayoutManager(getContext()));
        ridesSearchList.setAdapter(ridesListAdapter);

        loadAndObserveSearchRideViewModel();


    }
    RecyclerView recyclerView = findViewById(R.id.recycler_view);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
    */



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemList = findViewById(R.id.rv_items);


        itemListViewModel = new ViewModelProvider(this).get(ItemListViewModel.class);
        itemListViewModel.initWithApplication(getApplication());
        itemListRecyclerViewAdapter = new ItemListRecyclerViewAdapter(new ArrayList<>());
        itemList.setLayoutManager(new LinearLayoutManager(this));
        itemList.setAdapter(itemListRecyclerViewAdapter);
        loadAndObserveViewModel();
    }


  /*  private void loadAndObserveSearchRideViewModel() {
        viewModel.getMyRides().observe(getViewLifecycleOwner(), apiResponse -> {
            if (apiResponse.isSuccessful()) {
                ridesSearchList.setVisibility(View.VISIBLE);
                ridesListAdapter.updateList(apiResponse.body);
                searchListError.setVisibility(View.GONE);
            } else {
                ((MayoApplication) getActivity().getApplication()).getMayoContext().sendInfoMessage(apiResponse.errorMessage);
                ridesSearchList.setVisibility(View.GONE);
                searchListError.setVisibility(View.VISIBLE);
            }
            loadingView.setVisibility(View.GONE);

        });*/


    private void loadAndObserveViewModel() {
        itemListViewModel.loadItems().observe(this, listApiResponse -> {
            if (listApiResponse.isSuccessful()) {
                itemListRecyclerViewAdapter.updateList(listApiResponse.body);
            } else {
                ((MyStuffApplication) getApplication()).getMyStuffContext().sendInfoMessage(listApiResponse.errorMessage);
            }
        });
    }
}
