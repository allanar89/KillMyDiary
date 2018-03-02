package cu.slam.killhdiary;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.xml.sax.XMLReader;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	File presets;
	String ruta = "/data/data/com.aiguo.handydiary/shared_prefs/";
	String file = "com.aiguo.handydiary_preferences.xml";
	String contenido="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void Destruccion(){//para casos extremos
		presets = new File(ruta, file);
		presets.delete();
	}
	public void Liberar(){//el ideal 
		try{				
			presets = new File(ruta, file);
			if(presets.exists()){
				FileReader fr= new FileReader(presets);
				int c;
				while((c=fr.read())!=-1){
					contenido+=(char)c;
				}
				//a procesar el contenido
				Scanner in = new Scanner(contenido);
				while(true){
					String ln = in.nextLine();
					if(ln.contains("settings_key_password"))
						break;
				}
			}			
		}catch(IOException e){
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.action_settings:
			AlertDialog msg = new AlertDialog.Builder(getApplicationContext()).create();
			msg.setTitle("Acerca de KillHDiary");
			msg.setMessage("Versión 1.0 desarrollada por Slam (2017)");
			msg.show();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
