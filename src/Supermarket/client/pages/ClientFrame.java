package Supermarket.client.pages;

import javax.swing.*;

public class ClientFrame extends JFrame {
    public MainPage mainPage;
    public LoginPage loginPage;
    public RegisterPage registerPage;
    public ClientSocket clientSocket;
    public AdminPage adminPage;
    public DrinksPage drinksPage;
    public DairyPPage dairyPPage;
    public IncomePage incomePage;
    public UserPage userPage;
    public BuyDrinkPage buyDrinkPage;
    public BuyDairyPPage buyDairyPPage;
    public HistoryPage historyPage;

    public ClientFrame(){
        setSize(600,700);
        setTitle("Supermarket application");
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        clientSocket=new ClientSocket();

        mainPage=new MainPage(this);
        mainPage.setVisible(true);
        add(mainPage);

        loginPage=new LoginPage(this);
        loginPage.setVisible(false);
        add(loginPage);

        registerPage=new RegisterPage(this);
        registerPage.setVisible(false);
        add(registerPage);

        adminPage=new AdminPage(this);
        adminPage.setVisible(false);
        add(adminPage);

        drinksPage=new DrinksPage(this);
        drinksPage.setVisible(false);
        add(drinksPage);

        dairyPPage=new DairyPPage(this);
        dairyPPage.setVisible(false);
        add(dairyPPage);

        incomePage=new IncomePage(this);
        incomePage.setVisible((false));
        add(incomePage);

        userPage=new UserPage(this);
        userPage.setVisible((false));
        add(userPage);

        buyDrinkPage=new BuyDrinkPage(this);
        buyDrinkPage.setVisible((false));
        add(buyDrinkPage);

        buyDairyPPage=new BuyDairyPPage(this);
        buyDairyPPage.setVisible((false));
        add(buyDairyPPage);

        historyPage=new HistoryPage(this);
        historyPage.setVisible((false));
        add(historyPage);

    }
}
