package llbean.tarea21listacompras;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Mariano
 * @since 11/25/2016
 */
final class Product implements Parcelable {

    private String description;
    private Integer amount;
    private Double price;
    private Double total;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(final Integer amount) {
        this.amount = amount;
    }

    String getDescription() {
        return description;
    }

    void setDescription(final String description) {
        this.description = description;
    }

    Double getPrice() {
        return price;
    }

    void setPrice(final Double price) {
        this.price = price;
    }

    Double getTotal() {
        return total;
    }

    void setTotal(final Double total) {
        this.total = total;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeValue(this.amount);
        dest.writeValue(this.price);
        dest.writeValue(this.total);
    }

    public Product() {
    }

    protected Product(Parcel in) {
        this.description = in.readString();
        this.amount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.price = (Double) in.readValue(Double.class.getClassLoader());
        this.total = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
