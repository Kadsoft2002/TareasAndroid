package llbean.tarea2listacompras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddingItems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_items);
    }

    public void saveProduct(View v) {
        String descr = ((EditText) findViewById(R.id.editDescription)).getText().toString();
        int amount = Integer.valueOf(((EditText) findViewById(R.id.editAmount)).getText().toString());
        double price = Double.valueOf(((EditText) findViewById(R.id.editPrice)).getText().toString());

        Intent intent = new Intent();
        intent.putExtra(Constants.DESCRIPTION, descr);
        intent.putExtra(Constants.PRICE, price);
        intent.putExtra(Constants.AMOUNT, amount);
        setResult(RESULT_OK, intent);

        finish();
    }

}
