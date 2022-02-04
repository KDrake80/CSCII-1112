/*
 * Author Kevin Drake
 * Date 2/3/22
 * This class defines the item object. To be stored in an ArrayList in the Account
 */
import java.io.Serializable;
import java.util.Date;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Item {
	private String itemName = " ";
	private String description = " ";
	private double price = 0.0;
	private ImageView itemImage = new ImageView();
	
	public Item(String itemName) {
		this.itemName = itemName;

	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ImageView getImage() {
		return itemImage;
	}
	public void setImage(String imagePath) {
		this.itemImage = new ImageView(new Image(imagePath));
	}
	public void setImageView(ImageView image) {
		this.itemImage = image;
	}
	@Override
	public String toString() {
		return itemName + "/n$" + price + "0/n" + description;
	}
}