package itlwy.com.o2omall.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mac on 16/10/4.
 */

public class Product implements Parcelable {
    public static final String Tag = "Product";
    /**
     * 商品id
     */
    private int id;

    private int categoryOneId;

    private int categoryTwoId;
    /**
     * 商品图片url
     */
    private String imgUrl;
    /**
     * 信息
     */
    private String info;
    /**
     * 价格
     */
    private float price;

    private int num = 1;

    private boolean isCheck;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public int getCategoryOneId() {
        return categoryOneId;
    }

    public void setCategoryOneId(int categoryOneId) {
        this.categoryOneId = categoryOneId;
    }

    public int getCategoryTwoId() {
        return categoryTwoId;
    }

    public void setCategoryTwoId(int categoryTwoId) {
        this.categoryTwoId = categoryTwoId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.categoryOneId);
        dest.writeInt(this.categoryTwoId);
        dest.writeString(this.imgUrl);
        dest.writeString(this.info);
        dest.writeFloat(this.price);
        dest.writeInt(this.num);
        dest.writeByte(this.isCheck ? (byte) 1 : (byte) 0);
    }

    public Product() {
    }

    protected Product(Parcel in) {
        this.id = in.readInt();
        this.categoryOneId = in.readInt();
        this.categoryTwoId = in.readInt();
        this.imgUrl = in.readString();
        this.info = in.readString();
        this.price = in.readFloat();
        this.num = in.readInt();
        this.isCheck = in.readByte() != 0;
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
