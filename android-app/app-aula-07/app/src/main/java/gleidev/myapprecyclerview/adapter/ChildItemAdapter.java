package gleidev.myapprecyclerview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gleidev.myapprecyclerview.R;
import gleidev.myapprecyclerview.model.ChildItem;

public class ChildItemAdapter extends RecyclerView.Adapter<ChildItemAdapter.ChildViewHolder> {
    private List<ChildItem> childItemList;

    public ChildItemAdapter(List<ChildItem> childItemList) {
        this.childItemList = childItemList;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item,parent, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {
        ChildItem childItem = childItemList.get(position);
        holder.childItemTitle.setText(childItem.getChildItemTitle());
    }

    @Override
    public int getItemCount() {
        return childItemList.size();
    }

    class ChildViewHolder extends RecyclerView.ViewHolder {
        TextView childItemTitle;
        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            childItemTitle = itemView.findViewById(R.id.child_item_title);
        }
    }
}
