package com.example.readfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	// GUI controls
	EditText txtData;
	Button btnWriteSDFile;
	Button btnReadSDFile;
	Button btnClearScreen;
	Button btnClose;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//bind gui elememt with local controls
		txtData = (EditText) findViewById(R.id.txtData);
		txtData.setHint("Enter some lines of data here...");

		btnWriteSDFile = (Button) findViewById(R.id.btnWriteSDFile);
		btnWriteSDFile.setOnClickListener(new OnClickListener() {

		public void onClick(View v) {
			// write on SD card file data in the text box
			try {
				File myFile = new File("/sdcard/YO!/YO! Received Audios/hangouts_message_1425535867135.ogg");
				myFile.createNewFile();
				FileOutputStream fOut = new FileOutputStream(myFile);
				OutputStreamWriter myOutWriter = 
										new OutputStreamWriter(fOut);
				myOutWriter.append(txtData.getText());
				myOutWriter.close();
				fOut.close();
				Toast.makeText(getBaseContext(),
						"Done writing SD 'mysdfile.txt'",
						Toast.LENGTH_SHORT).show();
			} catch (Exception e) {
				Toast.makeText(getBaseContext(), e.getMessage(),
						Toast.LENGTH_SHORT).show();
			}
		}// onClick
		}); // btnWriteSDFile
		
		
		btnReadSDFile = (Button) findViewById(R.id.btnReadSDFile);
		btnReadSDFile.setOnClickListener(new OnClickListener() {

		public void onClick(View v) {
			// write on SD card file data in the text box
		try {
			File myFile = new File("/sdcard/mysdfile.txt");
			FileInputStream fIn = new FileInputStream(myFile);
			BufferedReader myReader = new BufferedReader(
					new InputStreamReader(fIn));
			String aDataRow = "";
			String aBuffer = "";
			while ((aDataRow = myReader.readLine()) != null) {
				aBuffer += aDataRow + "\n";
			}
			txtData.setText(aBuffer);
			myReader.close();
			Toast.makeText(getBaseContext(),
					"Done reading SD 'files'",
					Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(getBaseContext(), e.getMessage(),
					Toast.LENGTH_SHORT).show();
		}
		}// onClick
		}); // btnReadSDFile
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
