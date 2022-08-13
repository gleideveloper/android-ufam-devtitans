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
        for (int i = 1; i <= 5; i++) {
            parentItemList.add(new ParentItem("Titulo Faixa " + i, childItemList()));

        }
        return parentItemList;
    }

    private List<ChildItem> childItemList() {
        List<ChildItem> childItemList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            childItemList.add(new ChildItem("Item da faixa" + i, 10));
        }

        return childItemList;
    }
}