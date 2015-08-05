package matrimony.foodninja;

import android.support.v7.app.ActionBarActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

public class MainActivity extends ActionBarActivity {
    ListView mListView;
    ArrayList<Boolean> mArrayboolean;
    ArrayAdapter mArrayAdapter;
    ArrayList<String> mFoodList;
    ArrayList<Integer> mFoodImageList;
    ArrayList<Integer> mFoodQty;
    LayoutInflater mInflater;
    MyAdapter myAdapter;
    Integer [] quantity={0,0,0,0,0,0,0,0,0,0};
    String [] food = {"Starter", "Main Course", "Launch"};
    Boolean[] foodselected = {false, false, false};
    Spinner mDyanamicSpinner;
    SpinnerAdapter mSpinnerAdapter;
    ArrayList<String> mSpinnerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView= (ListView) findViewById(R.id.list_view_1);
        mFoodImageList = new ArrayList<Integer>(Arrays.asList(DataClass.imageData));
        mFoodList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.food_starter)));
        mDyanamicSpinner = (Spinner)findViewById(R.id.spinner1);
        mSpinnerList = new ArrayList<String>(Arrays.asList(food));
        mSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,mSpinnerList);
        mDyanamicSpinner.setAdapter(mSpinnerAdapter);

        mDyanamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                /*Log.v("item selected", mSpinnerList.get(position).toString());*/
                foodselected[position] = true;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Log.v("nothing", "nothing");
            }
        });

        //mFoodPrice = new ArrayList<Integer>(Arrays.asList(getResources().getIntArray(R.array.foodquantity)));

        if(foodselected[0]) {
            mFoodQty = new ArrayList<Integer>(Arrays.asList(quantity));
            mInflater = getLayoutInflater();
            mListView = (ListView) findViewById(R.id.list_view_1);
            myAdapter = new MyAdapter();
            mListView.setAdapter(myAdapter);
        }

    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // get size of array to populate from DataClass, no initializations required because
            // ARRAY_LENGTH is a static variable
            return DataClass.ARRAY_LENGTH;
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

            if(convertView == null){

                convertView = mInflater.inflate(R.layout.starter,parent,false);

            }


            ImageView imageView = (ImageView)convertView.findViewById(R.id.foodimage);
            imageView.setImageResource(mFoodImageList.get(position));


            TextView fooditem = (TextView)convertView.findViewById(R.id.fooditem);
            fooditem.setText(mFoodList.get(position));

            TextView qty =  (TextView) convertView.findViewById(R.id.quantity);
            qty.setText(mFoodQty.get(position).toString());

            return convertView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
