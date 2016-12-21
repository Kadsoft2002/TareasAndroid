package llbean.tarea2listacompras;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingItems extends AppCompatActivity {

    private List<Product> productList = new ArrayList<>();
    private ListAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_items);

        adapter = new ListAdapter(this, productList);

        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }

    public void addProduct(View v) {
        startActivityForResult(new Intent(this, AddingItems.class), Constants.ACTIVITY_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.ACTIVITY_RESULT && resultCode == Activity.RESULT_OK) {
            Product product= new Product();
            product.setDescription(data.getStringExtra(Constants.DESCRIPTION));
            product.setPrice(data.getDoubleExtra(Constants.PRICE, 0));
            product.setAmount(data.getIntExtra(Constants.AMOUNT, 0));
            productList.add(product);

            adapter.notifyDataSetChanged();
            /*
            recyclerView --> notifyDataSetChanged(pos), para no repintar todos los items

             */
        }
    }
}
