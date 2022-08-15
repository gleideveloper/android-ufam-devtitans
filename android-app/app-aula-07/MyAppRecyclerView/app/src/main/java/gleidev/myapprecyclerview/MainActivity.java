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
        //Instacia o gerenciador e o adapter principal da list pai.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ParentItemAdapter parentItemAdapter = new ParentItemAdapter(parentItemList());
        //Adiciona o adpater na RecyclerView
        parentRecyclerView.setAdapter(parentItemAdapter);
        //Adiciona a RecyclerView no layout gerenciador
        parentRecyclerView.setLayoutManager(layoutManager);
    }
    //Adicionar os itens pais na lista
    private List<ParentItem> parentItemList() {
        List<ParentItem> parentItemList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            parentItemList.add(new ParentItem("Titulo Faixa " + i, childItemList()));
        }
        return parentItemList;
    }
    //Adicionar os itens filhos na lista
    private List<ChildItem> childItemList() {
        List<ChildItem> childItemList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            childItemList.add(new ChildItem("Item da faixa" + i, 10));
        }
        return childItemList;
    }
}