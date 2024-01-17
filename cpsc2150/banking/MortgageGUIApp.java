package cpsc2150.banking;

import cpsc2150.banking.controllers.*;
import cpsc2150.banking.views.*;
public class MortgageGUIApp {
    public static void main(String[] args) {
        IMortgageView view = new MortgageGUIView();
        IMortgageController controller = new MortgageGUIController(view);
        view.setController(controller);
    }
}