package jobblett.ui;

import javafx.scene.control.Button;
import jobblett.core.User;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import java.io.IOException;

public class GroupHomeController extends AbstractController{

    @FXML
    Text groupName;

    @FXML
    ListView<Text> members;

    @FXML
    Text groupID;

    @FXML
    Button backToHome;
    
    @FXML
    Button goToShifts;


    @Override
    public void update() {
        // Sets GroupName on top of the screen
        groupName.setText(activeGroup.getGroupName());

        // Shows GroupID
        groupID.setText("GroupID: " + activeGroup.getGroupID());

        // Lists all members
        for (User user : activeGroup) {
            Text text = new Text(user.toString());
            members.getItems().add(text);
        }
    }

    @FXML
    public void backButton() throws IOException {
        changeScreen("UserHome.fxml", backToHome, main);
    }
    
    @FXML
    public void viewShifts() throws IOException {
        changeScreen("ShiftView.fxml", goToShifts, main);
    }
}
