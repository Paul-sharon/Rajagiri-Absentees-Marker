package com.example.absentees;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Spinner subjectSpinner;
    private LinearLayout checkboxContainer;
    private HashMap<Integer, String> rollNumberToName;
    private HashMap<Integer, Boolean> selectedRollNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = findViewById(R.id.datePicker);
        subjectSpinner = findViewById(R.id.subjectSpinner);
        checkboxContainer = findViewById(R.id.checkboxContainer);
        Button btnSend = findViewById(R.id.btnSend);

        // Set up subjects in spinner
        String[] subjects = {"Parallel Programming", "Advanced Web Security", "Seminar"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subjects);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(adapter);

        // Roll number to name mapping
        rollNumberToName = new HashMap<>();
        rollNumberToName.put(1, "Abey Thomson Kozhippat");
        rollNumberToName.put(2, "Abhijith Suresh");
        rollNumberToName.put(3, "Abhinav M S");
        rollNumberToName.put(4, "Adithya K L");
        rollNumberToName.put(5, "ALBIN JOSEPH");
        rollNumberToName.put(6, "ALFIA A H");
        rollNumberToName.put(7, "Anjaleena Sarah K R");
        rollNumberToName.put(8, "Annie Susan Jennings");
        rollNumberToName.put(9, "Antony Jees T J");
        rollNumberToName.put(10, "ARYAMOL U");
        rollNumberToName.put(11, "Ashna maria");
        rollNumberToName.put(12, "Aswin k s");
        rollNumberToName.put(13, "Bhagya Bijoy");
        rollNumberToName.put(14, "Deepak K V");
        rollNumberToName.put(15, "Devana Rose Emmanuel");
        rollNumberToName.put(16, "Dhaivath Lal");
        rollNumberToName.put(17, "Dhilna M. D");
        rollNumberToName.put(18, "Dilsha c p");
        rollNumberToName.put(19, "Gracen K Shaji");
        rollNumberToName.put(20, "Hanock P Mani");
        rollNumberToName.put(21, "Haripriya S Nair");
        rollNumberToName.put(22, "Helan Mariya M B");
        rollNumberToName.put(23, "HRISHIKESH U");
        rollNumberToName.put(24, "JIBIN SABU JOHN");
        rollNumberToName.put(25, "JOYCE MATHEWS");
        rollNumberToName.put(26, "KAVYA NAIR");
        rollNumberToName.put(27, "Khadeeja Beevi C N");
        rollNumberToName.put(28, "Kwizera Mugara Gentil");
        rollNumberToName.put(29, "Leo Thomas");
        rollNumberToName.put(30, "Majo Augustine");
        rollNumberToName.put(31, "Manu Sankar U");
        rollNumberToName.put(32, "MEGHA P VARGHESE");
        rollNumberToName.put(33, "MERIN JAI");
        rollNumberToName.put(34, "Merrin Mariya Jaison");
        rollNumberToName.put(35, "Minu Rose Vazhappilly");
        rollNumberToName.put(36, "Muhammad Anshad P A");
        rollNumberToName.put(37, "Muhammed Nihal");
        rollNumberToName.put(38, "NDIKUMANA Mugara Steven");
        rollNumberToName.put(39, "Neema Varghese");
        rollNumberToName.put(40, "Nintu Varughese");
        rollNumberToName.put(41, "Nivya Varghese");
        rollNumberToName.put(42, "P V Anjali");
        rollNumberToName.put(43, "Paul Sharon Simenthy");
        rollNumberToName.put(44, "R Nandakishore");
        rollNumberToName.put(45, "Revathi J");
        rollNumberToName.put(46, "Ridha Ansar");
        rollNumberToName.put(47, "Saalim T S");
        rollNumberToName.put(48, "Sam V S");
        rollNumberToName.put(49, "Sanjay Benoy");
        rollNumberToName.put(50, "Sarath Chandran M");
        rollNumberToName.put(51, "Sarath Prasad T S");
        rollNumberToName.put(52, "Sharissa Marian Hurtis");
        rollNumberToName.put(53, "Shelvin Sunil Philip");
        rollNumberToName.put(54, "Silpa Chandrika Anil");
        rollNumberToName.put(55, "Sivanand M Prabhu");
        rollNumberToName.put(56, "Sneha Chandrika Anil");
        rollNumberToName.put(57, "Sreekesh K Prabhu");
        rollNumberToName.put(58, "Sreekumar M S");
        rollNumberToName.put(59, "SUDHA CHANDRIKA ANIL");
        rollNumberToName.put(60, "Urmila Chaudhary");
        rollNumberToName.put(61, "Varghese P Roy");

        // Add checkboxes dynamically
        selectedRollNumbers = new HashMap<>();
        for (int i = 1; i <= 61; i++) {
            CheckBox checkBox = new CheckBox(this);
            String name = rollNumberToName.get(i);
            checkBox.setText("Roll No. " + i + " (" + name + ")");
            checkboxContainer.addView(checkBox);

            int rollNumber = i; // For use in lambda
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                selectedRollNumbers.put(rollNumber, isChecked);
            });
        }

        // Send button click
        btnSend.setOnClickListener(v -> {
            ArrayList<String> absenteeDetails = new ArrayList<>();
            for (int rollNumber : selectedRollNumbers.keySet()) {
                if (selectedRollNumbers.get(rollNumber)) {
                    String name = rollNumberToName.get(rollNumber);
                    absenteeDetails.add("Roll No " + rollNumber + " - " + name);
                }
            }

            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("date", getSelectedDate());  // Ensure this method is working correctly
            intent.putExtra("subject", subjectSpinner.getSelectedItem().toString());
            intent.putStringArrayListExtra("absentees", absenteeDetails);  // Passing absentee details as a list of strings
            intent.putExtra("count", absenteeDetails.size());  // Passing the count of absentees
            startActivity(intent);
        });
    }

    private String getSelectedDate() {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1; // Months are 0-indexed
        int year = datePicker.getYear();
        return day + "/" + month + "/" + year;
    }
}
