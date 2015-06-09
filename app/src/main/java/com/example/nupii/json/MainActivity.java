package com.example.nupii.json;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ListActivity {
    private Context context;
    private static String url = "http://resources.260mb.net/alumnos.json";


    static final String ID_ALUMNO = "id_alumno";
    static final String KEY_NOMBRE = "nombre";
    static final String KEY_APELLIDO_P = "apellido_p";
    static final String KEY_APELLIDO_M = "apellido_m";
    static final String KEY_CARRERA = "carrera";


    ArrayList<HashMap<String, String>> jsonlist = new ArrayList<HashMap<String, String>>();

    ListView lv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ProgressTask(MainActivity.this).execute();
    }

    private class ProgressTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog dialog;

        private ListActivity activity;

        // private List<Message> messages;
        public ProgressTask(ListActivity activity) {
            this.activity = activity;
            context = activity;
            dialog = new ProgressDialog(context);
        }

        /** progress dialog to show user that the backup is processing. */

        /** application context. */
        private Context context;

        protected void onPreExecute() {
            this.dialog.setMessage("Progress start");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            ListAdapter adapter = new SimpleAdapter(context, jsonlist,
                    R.layout.list_item,new String[] {ID_ALUMNO, KEY_NOMBRE, KEY_APELLIDO_P, KEY_APELLIDO_M, KEY_CARRERA}, new int[] {
                    R.id.txt_id_alumno, R.id.txt_nombre,R.id.txt_apellido_p, R.id.txt_apellido_m, R.id.txt_carrera});

            setListAdapter(adapter);

            // selecting single ListView item
            lv = getListView();
            lv.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // getting values from selected ListItem
                    String idalumno = ((TextView) view.findViewById(R.id.txt_id_alumno)).getText().toString();
                    String nombre = ((TextView) view.findViewById(R.id.txt_nombre)).getText().toString();
                    String apellidopaterno=((TextView) view.findViewById(R.id.txt_apellido_p)).getText().toString();
                    String apellidomaterno=((TextView) view.findViewById(R.id.txt_apellido_m)).getText().toString();
                    String carrera=((TextView) view.findViewById(R.id.txt_carrera)).getText().toString();


                    // Starting new intent
                    Intent in = new Intent(getApplicationContext(), vistaindividual.class);
                    in.putExtra(ID_ALUMNO, idalumno);
                    in.putExtra(KEY_NOMBRE, nombre);
                    in.putExtra(KEY_APELLIDO_P, apellidopaterno);
                    in.putExtra(KEY_APELLIDO_M, apellidomaterno);
                    in.putExtra(KEY_CARRERA, carrera);


                    startActivity(in);

                }
            });

        }


        protected Boolean doInBackground(final String... args) {

            JSONParser jParser = new JSONParser();

            // getting JSON string from URL
            JSONArray json = jParser.getJSONFromUrl(url);

            for (int i = 0; i < json.length(); i++) {

                try {
                    JSONObject c = json.getJSONObject(i);
                    String id = c.getString(ID_ALUMNO);

                    String nom = c.getString(KEY_NOMBRE);
                    String ap = c.getString(KEY_APELLIDO_P);
                    String am = c.getString(KEY_APELLIDO_M);
                    String ca = c.getString(KEY_CARRERA);


                    HashMap<String, String> map = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    map.put(ID_ALUMNO, id);
                    map.put(KEY_NOMBRE, nom);
                    map.put(KEY_APELLIDO_P, ap);
                    map.put(KEY_APELLIDO_M, am);
                    map.put(KEY_CARRERA, ca);
                    jsonlist.add(map);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            return null;

        }

    }
}