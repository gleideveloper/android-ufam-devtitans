package gleidev.myapprecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import gleidev.myapprecyclerview.adapter.ParentItemAdapter;
import gleidev.myapprecyclerview.model.ChildItem;
import gleidev.myapprecyclerview.model.ParentItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView parentRecyclerView = findViewById(R.id.parent_recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ParentItemAdapter parentItemAdapter = new ParentItemAdapter(parentItemList());

        parentRecyclerView.setAdapter(parentItemAdapter);
        parentRecyclerView.setLayoutManager(layoutManager);
    }

    private List<ParentItem> parentItemList() {
        List<ParentItem> parentItemList = new ArrayList<>();
        parentItemList.add(new ParentItem("Titulo Faixa 1", childItemList()));
        parentItemList.add(new ParentItem("Titulo Faixa 1", childItemList()));
        parentItemList.add(new ParentItem("Titulo Faixa 1", childItemList()));
        return parentItemList;
    }

    private List<ChildItem> childItemList() {
        List<ChildItem> childItemList = new ArrayList<>();
        childItemList.add(new ChildItem("Item da faixa 1"));
        childItemList.add(new ChildItem("Item da faixa 1"));
        childItemList.add(new ChildItem("Item da faixa 1"));
        return childItemList;
    }
}