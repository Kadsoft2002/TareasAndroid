package llbean.tarea21listacompras;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariano
 * @since 11/25/2016
 */
public final class ShoppingItems extends AppCompatActivity {

    private ArrayList<Product> productList = new ArrayList<>();
    private ListAdapter adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_items);

        if(savedInstanceState != null){
            productList = savedInstanceState.getParcelableArrayList(Constants.PRODUCT_LIST);
        }

        adapter = new ListAdapter(this, productList);

        final RecyclerView view = (RecyclerView) findViewById(R.id.itemsList);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setHasFixedSize(true);
        view.setAdapter(adapter);
    }

    public void addProduct(final View v) {
        startActivityForResult(new Intent(this, AddingItems.class), Constants.ACTIVITY_RESULT);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Constants.PRODUCT_LIST, productList);

    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.ACTIVITY_RESULT && resultCode == Activity.RESULT_OK) {
            /*final Product product= new Product();
            product.setDescription(data.getStringExtra(Constants.DESCRIPTION));
            product.setPrice(data.getDoubleExtra(Constants.PRICE, 0));
            product.setAmount(data.getIntExtra(Constants.AMOUNT, 0));
            product.setTotal(data.getDoubleExtra(Constants.TOTAL, 0));*/

            final Product product = data.getParcelableExtra(Constants.PRODUCT);

            productList.add(product);

            adapter.notifyDataSetChanged();

        }
    }
}
