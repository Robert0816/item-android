package de.telekom.sea.mystuff.frontend.android.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.telekom.sea.mystuff.frontend.android.R;
import de.telekom.sea.mystuff.frontend.android.databinding.MyStuffItemBinding;
import de.telekom.sea.mystuff.frontend.android.model.Item;
import lombok.Getter;



public class ItemListRecyclerViewAdapter extends RecyclerView.Adapter<ItemListRecyclerViewAdapter.ViewHolder>{

    @Getter
    private final List<Item> list;

    public ItemListRecyclerViewAdapter(List<Item> list) {
        this.list = list;
    }

    public void updateList(List<Item> nlist) {
        list.clear();
        list.addAll(nlist);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyStuffItemBinding listitemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.my_stuff_item, parent, false);
        return new ViewHolder(listitemBinding);
    }


    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = getList().get(position);
        holder.getBinding().setItem(item);
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Getter
        private MyStuffItemBinding binding;

        public ViewHolder(@NonNull MyStuffItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
