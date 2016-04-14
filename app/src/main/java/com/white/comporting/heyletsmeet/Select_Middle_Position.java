package com.white.comporting.heyletsmeet;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

/**
 * Created by Baek on 2016-04-11.
 */
public class Select_Middle_Position extends AppCompatActivity {
    DrawerLayout drawer_Layout;
    ActionBarDrawerToggle toggle;
    FrameLayout Select_Position;
    ListView Select_Position_Menu;
    private String[] navItems = {"Brown", "Cadet Blue", "Dark Olive Green",
            "Dark Orange", "Golden Rod"};


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_middle_position);
        drawer_Layout = (DrawerLayout) findViewById(R.id.Drawer_Layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toggle = new ActionBarDrawerToggle(this, drawer_Layout, R.string.open_bar, R.string.close_Bar) {
        };
        drawer_Layout.addDrawerListener(toggle);
        toggle.syncState();

        Select_Position_Menu = (ListView) findViewById(R.id.SelectPositionMenu);
        Select_Position = (FrameLayout) findViewById(R.id.SelectPosition);

        Select_Position_Menu.setAdapter(
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, navItems));
        Select_Position_Menu.setOnItemClickListener(new MenuItemClickListener());


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private class MenuItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position,
                                long id) {
            switch (position) {
                case 0:
                    Select_Position.setBackgroundColor(Color.parseColor("#A52A2A"));
                    break;
                case 1:
                    Select_Position.setBackgroundColor(Color.parseColor("#5F9EA0"));
                    break;
                case 2:
                    Select_Position.setBackgroundColor(Color.parseColor("#556B2F"));
                    break;
                case 3:
                    Select_Position.setBackgroundColor(Color.parseColor("#FF8C00"));
                    break;
                case 4:
                    Select_Position.setBackgroundColor(Color.parseColor("#DAA520"));
                    break;
            }

            drawer_Layout.closeDrawer(Select_Position_Menu);


        }

    }
}
