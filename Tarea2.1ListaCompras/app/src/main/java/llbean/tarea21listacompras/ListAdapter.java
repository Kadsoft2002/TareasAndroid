package llbean.tarea21listacompras;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @author Mariano
 * @since 11/25/2016
 */
final class ListAdapter extends RecyclerView.Adapter<ListAdapter.ProductHolder> {

    private List<Product> productList;
    private Context mContext;

    ListAdapter(final Context context, final List<Product> list) {
        productList = list;
        mContext = context;
    }

    @Override
    public ProductHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout, parent, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductHolder holder, final int position) {
        Product product = productList.get(position);

        holder.mDescription.setText(mContext.getString(R.string.description) + product.getDescription());
        holder.mAmount.setText(mContext.getString(R.string.amount) + product.getAmount());
        holder.mPrice.setText(mContext.getString(R.string.price) + product.getPrice());
        holder.mTotal.setText(mContext.getString(R.string.total) + product.getTotal());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {
        TextView mDescription;
        TextView mAmount;
        TextView mPrice;
        TextView mTotal;

        ProductHolder(View itemView) {
            super(itemView);

            mDescription = (TextView) itemView.findViewById(R.id.description);
            mAmount = (TextView) itemView.findViewById(R.id.amount);
            mPrice = (TextView) itemView.findViewById(R.id.price);
            mTotal = (TextView) itemView.findViewById(R.id.total);
        }

    } // class ProductHolder

} // class ListAdapter
