package gleidev.myapprecyclerview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gleidev.myapprecyclerview.R;
import gleidev.myapprecyclerview.model.ParentItem;

public class ParentItemAdapter extends RecyclerView.Adapter<ParentItemAdapter.ParentViewHolder> {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<ParentItem> itemList;

    public ParentItemAdapter(List<ParentItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_item, parent, false);
        return new ParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder holder, int position) {
        ParentItem parentItem = itemList.get(position);
        holder.parentItemTitle.setText(parentItem.getParentItemTitle());


        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.childRecycleView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setInitialPrefetchItemCount(parentItem.getChildItemList().size());

        ChildItemAdapter childItemAdapter = new ChildItemAdapter(parentItem.getChildItemList());
        holder.childRecycleView.setLayoutManager(layoutManager);
        holder.childRecycleView.setAdapter(childItemAdapter);
        holder.childRecycleView.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ParentViewHolder extends RecyclerView.ViewHolder {
        private final TextView parentItemTitle;
        private final RecyclerView childRecycleView;

        public ParentViewHolder(@NonNull View itemView) {
            super(itemView);
            parentItemTitle = itemView.findViewById(R.id.parent_item_title);
            childRecycleView = itemView.findViewById(R.id.child_recyclerview);
        }
    }
}
