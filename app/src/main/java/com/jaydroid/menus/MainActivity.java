package com.jaydroid.menus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    androidx.appcompat.view.ActionMode mActionMode;

    private ActionMode.Callback mActionModeCallBack = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.actionmode_context_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.actionmodemenu1:
                    Toast.makeText(MainActivity.this, "Action Mode 1 Clicked", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                case R.id.actionmodemenu2:
                    Toast.makeText(MainActivity.this, "Action Mode 2 Clicked", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView) findViewById(R.id.textTitle);
        this.registerForContextMenu(tv);

        Button button = (Button) findViewById(R.id.btnActionModeMenu);
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mActionMode != null) {
                    return false;
                }
                mActionMode = startSupportActionMode(mActionModeCallBack);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // handling options menu item click
        switch (item.getItemId()) {
            case R.id.menuItem1:
                Toast.makeText(this, "Menu Item 1 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuItem2:
                Toast.makeText(this, "Menu Item 2 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuItem3:
                Toast.makeText(this, "Menu Item 3 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.floating_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        // handle contextual floating menu item click
        switch (item.getItemId()) {
            case R.id.contextmenu1:
                Toast.makeText(this, "Context Menu 1 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.contextmenu2:
                Toast.makeText(this, "Context Menu 2 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    public void showPopMenu(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popupmenu1:
                Toast.makeText(this, "Popup Menu 1 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.popupmenu2:
                Toast.makeText(this, "Popup Menu 2 Clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }
}