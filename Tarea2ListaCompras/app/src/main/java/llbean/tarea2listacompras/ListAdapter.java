package llbean.tarea2listacompras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mariano on 11/13/2016.
 */

public final class ListAdapter extends BaseAdapter {

    private List<Product> productList;
    private final LayoutInflater mInflater;
    private Context mContext;

    public ListAdapter(Context context, List<Product> list) {
        productList = list;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ProductHolder holder;
        if (view == null) {
            holder = new ProductHolder();
            view = mInflater.inflate(R.layout.product_layout, viewGroup, false);
            holder.mDescription = (TextView) view.findViewById(R.id.description);
            holder.mAmount = (TextView) view.findViewById(R.id.amount);
            holder.mPrice = (TextView) view.findViewById(R.id.price);
            holder.mTotal = (TextView) view.findViewById(R.id.total);

            view.setTag(holder);
        } else {
            holder = (ProductHolder) view.getTag();
        }

        Product product = productList.get(i);

        holder.mDescription.setText(mContext.getString(R.string.description) + product.getDescription());
        holder.mAmount.setText(mContext.getString(R.string.amount) + product.getAmount());
        holder.mPrice.setText(mContext.getString(R.string.price) + product.getPrice());
        holder.mTotal.setText(mContext.getString(R.string.total) + product.getAmount() * product.getPrice()); //TODO: calculate this in the object

        return view;
    }

    private class ProductHolder {
        TextView mDescription;
        TextView mAmount;
        TextView mPrice;
        TextView mTotal;
    }

}
