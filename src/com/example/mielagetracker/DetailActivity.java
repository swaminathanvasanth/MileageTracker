package com.example.mielagetracker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		final EditText startkm = (EditText) findViewById(R.id.estartingkm);
		final EditText endkm = (EditText) findViewById(R.id.eendingkm);
		final EditText fuel = (EditText) findViewById(R.id.efuel);

		final String startFileName = "start";
		final String endFileName = "end";
		final String fuelFileName = "fuel";

		Button savebtn = (Button) findViewById(R.id.bsave);
		Button calculatebtn = (Button) findViewById(R.id.bcalculate);
		Button resetbtn = (Button) findViewById(R.id.reset);

		String fstartkm = null;
		String fendkm = null;
		String fpetrolkm = null;

		try {
			FileInputStream fis = openFileInput(startFileName);
			byte[] dataArray = new byte[fis.available()];
			while (fis.read(dataArray) != -1) {
				fstartkm = new String(dataArray);
			}
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Log.e("FileNotFoundException", "FileNotFoundException");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("IOException", "IOException");
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			Log.e("NullPointerException", "NullPointerException");
		}

		try {
			FileInputStream fis = openFileInput(endFileName);
			byte[] dataArray = new byte[fis.available()];
			while (fis.read(dataArray) != -1) {
				fendkm = new String(dataArray);
			}
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Log.e("FileNotFoundException", "FileNotFoundException");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("IOException", "IOException");
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			Log.e("NullPointerException", "NullPointerException");
		}

		try {
			FileInputStream fis = openFileInput(fuelFileName);
			byte[] dataArray = new byte[fis.available()];
			while (fis.read(dataArray) != -1) {
				fpetrolkm = new String(dataArray);
			}
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Log.e("FileNotFoundException", "FileNotFoundException");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("IOException", "IOException");
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			Log.e("NullPointerException", "NullPointerException");
		}

		startkm.setText(fstartkm);
		endkm.setText(fendkm);
		fuel.setText(fpetrolkm);

		savebtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View save) {
				// TODO Auto-generated method stub

				String startingkm = startkm.getText().toString();
				String endingkm = endkm.getText().toString();
				String petrol = fuel.getText().toString();

				if (startingkm.length() > 0) {
					try {
						FileOutputStream fos = openFileOutput(startFileName,
								Context.MODE_PRIVATE);
						fos.write(startingkm.getBytes());
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (endingkm.length() > 0) {
					try {
						FileOutputStream fos = openFileOutput(endFileName,
								Context.MODE_PRIVATE);
						fos.write(endingkm.getBytes());
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (petrol.length() > 0) {
					try {
						FileOutputStream fos = openFileOutput(fuelFileName,
								Context.MODE_PRIVATE);
						fos.write(petrol.getBytes());
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				Log.e("After Saving", startingkm + "-" + endingkm + "-"
						+ petrol);
				Toast.makeText(getApplicationContext(), "Saved",
						Toast.LENGTH_LONG).show();
			}
		});

		calculatebtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View calculate) {
				// TODO Auto-generated method stub
				String startingkm = startkm.getText().toString();
				String endingkm = endkm.getText().toString();
				String petrol = fuel.getText().toString();

				float start = Float.parseFloat(startingkm);
				float end = Float.parseFloat(endingkm);
				float fuel = Float.parseFloat(petrol);

				if (end < start) {
					Toast.makeText(getApplicationContext(), "Check the KMs Value",
							Toast.LENGTH_LONG).show();
				} else if (fuel == 0) {
					Toast.makeText(getApplicationContext(),
							"Check Petrol Value", Toast.LENGTH_LONG).show();
				} else {
					float mielage = (end - start) / fuel;
					TextView tvmielage = (TextView) findViewById(R.id.mielage);
					tvmielage.setText("Mielage is : " + mielage + "km/l");
					Toast.makeText(getApplicationContext(),
							"" + mielage + " km/l", Toast.LENGTH_LONG).show();
					Button resetbtn = (Button) findViewById(R.id.reset);
					resetbtn.setVisibility(View.VISIBLE);
				}
			}
		});

		resetbtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View reset) {
				// TODO Auto-generated method stub
				startkm.setText("0");
				endkm.setText("0");
				fuel.setText("0");
				TextView tvmielage = (TextView) findViewById(R.id.mielage);
				tvmielage.setText("");
				Button resetbtn = (Button) findViewById(R.id.reset);
				resetbtn.setVisibility(View.INVISIBLE);
			}
		});

	}
}
