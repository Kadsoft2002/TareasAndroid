package llbean.tarea21listacompras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * @author Mariano
 * @since 11/25/2016
 */
public final class AddingItems extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_items);
    }

    public void saveProduct(final View v) {
        final String descr = ((EditText) findViewById(R.id.editDescription)).getText().toString();
        int amount = 0;
        try {
            amount = Integer.valueOf(((EditText) findViewById(R.id.editAmount)).getText().toString());
        } catch (Exception e) {}

        double price = 0;
        try {
            price = Double.valueOf(((EditText) findViewById(R.id.editPrice)).getText().toString());
        } catch (Exception e) {}

        final Intent intent = new Intent();
        /*intent.putExtra(Constants.DESCRIPTION, descr);
        intent.putExtra(Constants.PRICE, price);
        intent.putExtra(Constants.AMOUNT, amount);
        intent.putExtra(Constants.TOTAL, amount * price);*/

        final Product product= new Product();
        product.setDescription(descr);
        product.setPrice(price);
        product.setAmount(amount);
        product.setTotal(amount * price);

        intent.putExtra(Constants.PRODUCT, product);


        setResult(RESULT_OK, intent);

        finish();
    }

}
