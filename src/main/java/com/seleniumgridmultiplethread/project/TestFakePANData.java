package main.java.com.seleniumgridmultiplethread.project;

import com.github.javafaker.Faker;

public class TestFakePANData {

    private void insertNamesOnCardData(CardData cardData) {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();

        System.out.println("firstName->"+firstName);

        String lastName = faker.name().lastName();
        System.out.println("lastName->"+lastName);

        String fullName = firstName + " " + lastName;
        cardData.getPan().setFirstName(firstName); // Card Data will get call get Pan function as Card Data //has Pan info, then after that call setFirstName function of PAN class


        cardData.getPan().setLastName(lastName);
        cardData.getPan().setFullName(fullName);
        String bin=faker.idNumber().valid();
        cardData.getPan().setBIN(bin);
        System.out.println("PAN ->"+cardData.getPan());
    }

    public static void main(String[] args) {

        TestFakePANData testFakePANData=new TestFakePANData();
        CardData cardData=new CardData();

        cardData.setPan(new Pan());
        testFakePANData.insertNamesOnCardData(cardData);


    }
}
