package com.example.absentees;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView tvDetails = findViewById(R.id.tvDetails);
        Button btnCopy = findViewById(R.id.btnCopy);

        // Retrieve the data passed from MainActivity
        String date = getIntent().getStringExtra("date");
        String subject = getIntent().getStringExtra("subject");
        ArrayList<String> absentees = getIntent().getStringArrayListExtra("absentees");
        int count = getIntent().getIntExtra("count", 0);  // Default count to 0 if not passed

        // Prepare the details string
        StringBuilder details = new StringBuilder();
        details.append("Date: ").append(date).append("\n");
        details.append("Subject: ").append(subject).append("\n");
        details.append("Absentees:\n");

        // Loop through the absentee list and append to the details
        for (String absentee : absentees) {
            details.append("- ").append(absentee).append("\n");
        }

        details.append("Total Absentees: ").append(count);

        // Set the text to display in the TextView
        tvDetails.setText(details.toString());

        // Set up the Copy button
        btnCopy.setOnClickListener(v -> {
            // Get the text from tvDetails
            String textToCopy = tvDetails.getText().toString();

            // Get the clipboard manager system service
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboard != null) {
                // Create a ClipData object with the text to be copied
                android.content.ClipData clip = android.content.ClipData.newPlainText("Absentee Details", textToCopy);

                // Set the clip data to the clipboard
                clipboard.setPrimaryClip(clip);

                // Ensure the Toast is shown after the copy operation
                Toast.makeText(getApplicationContext(), "Absentees copied to clipboard!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
