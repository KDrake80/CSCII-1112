/*
 * Author Kevin Drake
 * 2/3/22
 * This Program allows you to View your account which has Items stored. You can view What others are selling
 *  and You can buy their Items
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Barter extends Application {
	@Override
	public void start(Stage primaryStage) {
		ArrayList<Account> accounts = new ArrayList<>();
		ArrayList<Account> temp = new ArrayList<>();
		// Use the Kevin Accounts and password when you run it
		// Buy sarahs items
		Account k = new Account("Kevin", "kevin");
		Account s = new Account("Sarah", "sarah");
		
		k.addNewItem("Green Motorcycle", "Rebuilt Engine, 5k miles", 2500);
		k.getItem(0).setImage("G.Bike.png");
		s.addNewItem("Brown Leather Chair", "No Foot Rest, Very Sentimental", 250);
		s.getItem(0).setImage("A.B.chair.jpg");
		k.addNewItem("Baby Crib", "Brand new, barely used, Has drawers, and baby changing station", 300);
		k.getItem(1).setImage("W.Crib.jpg");
		
		accounts.add(k);
		accounts.add(s);

		FirstPane fp = new FirstPane();
		AccountPane ap = new AccountPane();
		AddItemPane aip = new AddItemPane();

		Scene fScene = new Scene(fp, 300, 200);
		Scene aScene = new Scene(ap, 300, 200);
		//		Scene aiScene = new Scene(aip, 400, 300);
		primaryStage.setTitle("Barter");
		primaryStage.setScene(fScene);
		primaryStage.show();

		fp.btOk.setOnAction(e ->{
			for (Account a: accounts) {
				if (fp.tfName.getText().equalsIgnoreCase(a.getAccountName()) &&
						fp.tfPass.getText().equals(a.getPassword())) {
					temp.add(a);
					accounts.remove(a);
					ap.setAccount(temp.get(0));
					primaryStage.setScene(aScene);
				}
			}
		});

		fp.btCancel.setOnAction(e -> System.exit(0));
		
		ap.btAddItem.setOnAction(e -> primaryStage.setScene(new Scene(aip, 400, 300)));
		
		ap.btShop.setOnAction(e -> {
			ArrayList<String> accountNames = new ArrayList<>();
			for (Account a: accounts) {
				accountNames.add(a.getAccountName() + " Items: " + a.getItemCount());
			}
			ListView<String> lvAccountList = new ListView<>(FXCollections.observableArrayList(accountNames));
			lvAccountList.setPrefSize(200, 200);
			BorderPane bpForLv = new BorderPane();
			bpForLv.setCenter(lvAccountList);
			primaryStage.setScene(new Scene(bpForLv, 300, 300));
			lvAccountList.getSelectionModel().selectedItemProperty().addListener(
					ov -> {
						for (Integer i: lvAccountList.getSelectionModel().getSelectedIndices()) {
							int index = i;
							Account a = accounts.get(i);
							ItemPane ip = new ItemPane(a);
							primaryStage.setScene(new Scene(ip, 400, 300));

							ip.cboItems.setOnAction(ee -> {
								ip.setItem(ip.itemNames.indexOf(ip.cboItems.getValue()));

								ip.btBuy.setOnAction(u -> {
									a.removeItem(temp.get(0), index);
								});
								ip.btBack.setOnAction(q -> {
									ap.setAccount(temp.get(0));
									primaryStage.setScene(aScene);
								});
							});
						}

					});
		});
		ap.btMyItems.setOnAction(e -> {
			ItemPane mine = new ItemPane(temp.get(0));
			mine.cboItems.setOnAction(r -> mine.setItem(mine.itemNames.indexOf(mine.cboItems.getValue())));
			primaryStage.setScene(new Scene(mine, 400, 300));
			mine.btBuy.setText("Delete");
			mine.btBuy.setOnAction(t -> {
				if (mine.accountItems.isEmpty())
					mine.taDescription.setText("Your list is empty!");
				else {
					temp.get(0).loseItem(mine.itemNames.indexOf(mine.cboItems.getValue()));
				}
			});
			mine.btBack.setOnAction(i -> System.exit(2));
		});
		aip.btAdd.setOnAction(e -> {
			temp.get(0).addNewItem(aip.tfItemName.getText(), aip.taDescription.getText(), Double.parseDouble(aip.tfPrice.getText()));
			for (Item i: temp.get(0).getItems()) {
				if (aip.tfItemName.getText().equalsIgnoreCase(i.getItemName())) {
					i.setImage(aip.tfImage.getText());
					primaryStage.setScene(aScene);
				}
			}
		});
		aip.btCancel.setOnAction(e -> primaryStage.setScene(aScene));
	}
	public static void main(String[] args) {
		launch(args);
	}

}
class ItemPane extends BorderPane {

	protected ArrayList<Item> accountItems = new ArrayList<>();
	protected ArrayList<String> itemNames = new ArrayList<>();
	protected ComboBox<String> cboItems = new ComboBox<>();
	protected Label lblItem = new Label();
	protected TextArea taDescription = new TextArea();
	protected Button btBuy = new Button("Buy");
	protected Button btBack = new Button("Quit");

	public ItemPane(Account a) {
		accountItems.addAll(a.getItems());
		for (Item i : a.getItems()) {
			itemNames.add(i.getItemName());
		}
		FlowPane fpButtons = new FlowPane();
		fpButtons.getChildren().addAll(btBuy, btBack);
		fpButtons.setAlignment(Pos.CENTER);
		fpButtons.setHgap(15);
		ObservableList<String> items = FXCollections.observableArrayList(itemNames);
		cboItems.getItems().addAll(items);
		BorderPane bpForCbo = new BorderPane();
		bpForCbo.setLeft(new Label("Select an Item: "));
		bpForCbo.setPadding(new Insets(15));
		lblItem.setContentDisplay(ContentDisplay.BOTTOM);
		taDescription.setWrapText(true);
		taDescription.setEditable(false);
		taDescription.setPrefSize(100, 125);
		bpForCbo.setCenter(cboItems);
		setTop(bpForCbo);
		cboItems.setPrefWidth(300);
		setLeft(lblItem);
		setCenter(taDescription);
		setBottom(fpButtons);
		layoutChildren();
		setPadding(new Insets(10));
	}

	public void setItem(int index) {
		Item display = accountItems.get(index);
		lblItem.setText("$" + display.getPrice() + "0");
		lblItem.setGraphic(display.getImage());
		taDescription.setText(display.getDescription());
	}
	public void removeItem(int index) {
		accountItems.remove(index);
	}
	public int getIndex(String name) {
		int index = 0;
		for (Item i: accountItems) {
			if (name.equalsIgnoreCase(i.getItemName())) {
				index = accountItems.indexOf(i);
				return index;
			}
		}
		return index;
	}
}
class AccountPane extends BorderPane {
	protected Account main = null;
	protected Button btShop = new Button("Shop");
	protected Button btAddItem = new Button("New Item");
	protected Label lblAccName = new Label();
	protected Label lblItemCount = new Label();
	protected Button btMyItems = new Button("View My Items");


	public AccountPane() {
		FlowPane fpInfo = new FlowPane(Orientation.VERTICAL);
		FlowPane fpButtons = new FlowPane(Orientation.VERTICAL);
		fpInfo.setVgap(15);
		fpButtons.setVgap(15);

		fpInfo.getChildren().addAll(lblAccName, lblItemCount);
		fpButtons.getChildren().addAll(btShop, btAddItem, btMyItems);

		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.add(fpInfo, 0, 0);
		gp.add(fpButtons, 0, 1);

		setCenter(gp);
		layoutChildren();
		setPadding(new Insets(15));
	}
	public void setAccount(Account a) {
		lblAccName.setText(a.getAccountName());
		lblItemCount.setText("Items: " + a.getItemCount());
		main = a;
	}
}
class FirstPane extends BorderPane {
	protected Label lblEnterName = new Label("Enter Name: ");
	protected Label lblEnterPassword = new Label("Enter Password");
	protected TextField tfName = new TextField();
	protected TextField tfPass = new TextField();
	protected Button btOk = new Button("Ok");
	protected Button btCancel = new Button("Cancel");

	public FirstPane() {
		FlowPane fpName = new FlowPane();
		fpName.setHgap(15);
		fpName.setAlignment(Pos.CENTER);
		FlowPane fpPass = new FlowPane();
		fpPass.setHgap(15);
		fpPass.setAlignment(Pos.CENTER);
		FlowPane fpButtons = new FlowPane();
		fpButtons.setHgap(15);
		fpButtons.setAlignment(Pos.CENTER);

		fpName.getChildren().addAll(lblEnterName, tfName);
		fpPass.getChildren().addAll(lblEnterPassword, tfPass);
		fpButtons.getChildren().addAll(btOk, btCancel);

		setTop(fpName);
		setCenter(fpPass);
		setBottom(fpButtons);
		layoutChildren();
		setPadding(new Insets(15));
	}
}
class AddItemPane extends BorderPane {
	protected Label lblAdd = new Label("Item Name: ");
	protected TextField tfItemName = new TextField();
	protected Label lblPrice = new Label("Item Price: ");
	protected TextField tfPrice = new TextField();
	protected Label lblImage = new Label("Image Path: ");
	protected TextField tfImage = new TextField();
	protected Label lblDescription = new Label("Item Description: ");
	protected TextArea taDescription = new TextArea();
	protected Button btAdd = new Button("Add");
	protected Button btCancel = new Button("Cancel");

	public AddItemPane() {
		FlowPane fp1 = new FlowPane();
		fp1.setAlignment(Pos.CENTER);
		fp1.setHgap(15);
		FlowPane fp2 = new FlowPane();
		fp2.setAlignment(Pos.CENTER);
		fp2.setHgap(15);
		FlowPane fp3 = new FlowPane();
		fp3.setAlignment(Pos.CENTER);
		fp3.setHgap(15);
		FlowPane fp4 = new FlowPane();
		fp4.setAlignment(Pos.CENTER);
		fp4.setHgap(15);
		FlowPane fp5 = new FlowPane();
		fp5.setAlignment(Pos.CENTER);
		fp5.setHgap(15);
		FlowPane fp6 = new FlowPane(Orientation.VERTICAL);
		fp6.setAlignment(Pos.CENTER);
		fp6.setVgap(15);

		fp1.getChildren().addAll(lblAdd, tfItemName);
		fp2.getChildren().addAll(lblPrice, tfPrice);
		fp3.getChildren().addAll(lblImage, tfImage);
		fp4.getChildren().addAll(lblDescription, taDescription);
		taDescription.setPrefSize(150, 25);
		taDescription.setWrapText(true);
		fp5.getChildren().addAll(btAdd, btCancel);

		fp6.getChildren().addAll(fp1, fp2, fp3, fp4, fp5);
		setCenter(fp6);
		setPadding(new Insets(10));
		layoutChildren();

	}
}
