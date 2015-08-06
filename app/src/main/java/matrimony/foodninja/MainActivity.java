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
import java.util.HashMap;
import java.util.InputMismatchException;

public class MainActivity extends ActionBarActivity {
    ListView mListView;
    ArrayList<Boolean> mArrayboolean;
    ArrayAdapter mArrayAdapter;
    ArrayList<String> mMianCourseList;
    ArrayList<String> mStarterList;
    ArrayList<Integer> mFoodImageList;
    ArrayList<Integer> mFoodQty;
    LayoutInflater mInflater;
    MyAdapter myAdapter;
    ArrayList<Integer> quantity;
    ArrayList<Boolean> foodselected;
    Spinner mDyanamicSpinner;
    SpinnerAdapter mSpinnerAdapter;
    ArrayList<String> mSpinnerList;
    //HashMap<Boolean, HashMap<>>;
    int size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView= (ListView) findViewById(R.id.list_view_1);


      /*  foodselected = new Boolean[3];
        for(int i=0;i<3;i++)
        {
            foodselected[i] = false;
        }*/
        quantity = new ArrayList<Integer>();
        for(int i=0;i<10;i++)
        {
            quantity.add(0);
        }

        mFoodImageList = new ArrayList<Integer>(Arrays.asList(DataClass.imageData));
        mMianCourseList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.food_starter)));
        mStarterList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.food_starter)));
        mDyanamicSpinner = (Spinner)findViewById(R.id.spinner1);
        mSpinnerList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.array_menu)));
        mSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,mSpinnerList);
        mDyanamicSpinner.setAdapter(mSpinnerAdapter);
        foodselected = new ArrayList<Boolean>();
        size = mSpinnerList.size();
        
        for(int i=0;i<size;i++)
        {
            foodselected.add(false);
        }

        mDyanamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item selected", mSpinnerList.get(position).toString());
                Log.v("position is", Integer.toString(position));
             /*   foodselected.set(position, true);
                for(int i=0;i<size;i++)
                {
                    if(i!=position)
                    {
                        foodselected.set(i,false);
                    }
                }
*/
                if (position==1) {
                    Log.v("inside00", "Inside starter");

                    mInflater = getLayoutInflater();
                    mListView = (ListView) findViewById(R.id.list_view_1);
                    myAdapter = new MyAdapter();
                    mListView.setAdapter(myAdapter);
                    Log.v("inside0", "Inside starter");
                }
                else if(position==0)
                {
                    
                }
                else
                {

                }

            //    myAdapter.notifyDataSetChanged();

                //   myDynamic.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Log.v("nothing", "nothing");
            }
        });

        //mFoodPrice = new ArrayList<Integer>(Arrays.asList(getResources().getIntArray(R.array.foodquantity)));



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
            fooditem.setText(mMianCourseList.get(position));

            TextView qty =  (TextView) convertView.findViewById(R.id.quantity);
            qty.setText(quantity.get(position).toString());

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
