package matrimony.foodninja;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.zip.Inflater;


public class DrawerFilter extends ActionBarActivity {
    ListView drawerListVeiw;
    LayoutInflater layoutInflater;
    MyAdaptor myAdaptor;

    ArrayList<String> drawerArray;
    ArrayList<String> arrayList;
    DrawerLayout drawerLayout;
    EditText editTextDrawer;


    ArrayList<String> spinnerList;
    Spinner spinnerMenu;
    SpinnerAdapter spinnerAdapter;

    Button orderButton;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_filter);

        String[] array= getResources().getStringArray(R.array.food_starter);

        drawerListVeiw= (ListView)findViewById(R.id.drawer_list);

        List<String> list_array = Arrays.asList(array);
        // List<String> list_veiw_copy =Arrays.asList(arraycopy);

        arrayList =new ArrayList<String>(list_array);
        drawerArray =new ArrayList<String>(list_array);

        myAdaptor =new MyAdaptor();
        drawerListVeiw.setAdapter(myAdaptor);

        drawerLayout =(DrawerLayout)findViewById(R.id.drawer_layout);
        editTextDrawer = (EditText)findViewById(R.id.drawer_edit_text);
        editTextDrawer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String temp = editTextDrawer.getText().toString();
                String variable;
                Log.v("EditText", temp);
                Iterator itr = arrayList.iterator();
                drawerArray.clear();
                while (itr.hasNext()) {
                    variable = itr.next().toString();
                    if (variable.toLowerCase().contains(temp.toLowerCase())) {
                        drawerArray.add(variable);
                        Log.v("CHANGED", variable);
                    }
                }

                Log.v("DRAWERARRAY", drawerArray.toString());

                myAdaptor.notifyDataSetChanged();

            }
        });



        spinnerList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.array_menu)));
        spinnerMenu = (Spinner)findViewById(R.id.spinner);
        spinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,spinnerList);
        spinnerMenu.setAdapter(spinnerAdapter);

        spinnerMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected_course = spinnerList.get(position);
                Log.v("spiner", selected_course);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.v("spiner", "nothing selected");
            }
        });

        orderButton =(Button)findViewById(R.id.order);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), FinalOrder.class);
                intent.putExtra("total", 200);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drawer_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MyAdaptor extends BaseAdapter {

        @Override
        public int getCount() {
            return drawerArray.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.v("position getveiw", Integer.toString(position));
            if(convertView == null)
            {
                convertView=layoutInflater.inflate(R.layout.drawer_list_element,parent,false);
            }

            TextView textView = (TextView)convertView.findViewById(R.id.draw_text);
            textView.setText(drawerArray.get(position));




            return convertView;
        }
    }

}
