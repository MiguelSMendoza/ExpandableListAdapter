/*
 * Copyright (c) 2015, Miguel S. Mendoza
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package es.netrunners.expandablelistadapter.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import es.netrunners.expandablelistadapter.R;
import es.netrunners.expandablelistadapter.adapter.items.Child;
import es.netrunners.expandablelistadapter.adapter.items.Item;
import es.netrunners.expandablelistadapter.adapter.items.Parent;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private static final String PREFS_NAME = "APP";
    private LayoutInflater inflater;
    private ArrayList<Parent> mParent;
    private Context context;

    public ExpandableListAdapter(Context context, ArrayList<Parent> parent) {
        mParent = parent;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    private class ViewHolder {
        TextView text;
        CheckBox check;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        Parent parent = mParent.get(i);
        if (view == null) {
            view = inflater
                    .inflate(R.layout.list_item_parent, viewGroup, false);
            holder = new ViewHolder();
            holder.text = (TextView) view
                    .findViewById(R.id.list_item_text_view);
            holder.check = (CheckBox) view.findViewById(R.id.list_item_check);
            holder.check.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    Parent item = (Parent) cb.getTag();
                    putBoolean(item.getID(), cb.isChecked());
                    for (Child elem : item.getArrayChildren()) {
                        putBoolean(elem.getID(), cb.isChecked());
                    }
                    notifyDataSetChanged();
                }
            });
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Boolean selected = getBoolean(parent.getID());
        holder.text.setText(parent.getTitle());
        holder.check.setChecked(selected);

        holder.check.setTag(parent);
        notifyDataSetChanged();
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view,
                             ViewGroup viewGroup) {
        ViewHolder holder;
        Item child = mParent.get(i).getArrayChildren().get(i1);
        if (view == null) {
            view = inflater.inflate(R.layout.list_item_child, viewGroup, false);
            holder = new ViewHolder();
            holder.text = (TextView) view
                    .findViewById(R.id.list_item_text_child);
            holder.check = (CheckBox) view
                    .findViewById(R.id.list_item_check_child);
            holder.check.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    Child item = (Child) cb.getTag();
                    putBoolean(item.getID(), cb.isChecked());
                    if (!cb.isChecked())
                        putBoolean(item.getParent().getID(), false);
                    notifyDataSetChanged();
                }
            });
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        boolean selected = getBoolean(
                mParent.get(i).getArrayChildren().get(i1)
                        .getID());
        holder.text.setText(mParent.get(i).getArrayChildren().get(i1)
                .getTitle());
        holder.check.setChecked(selected);
        holder.check.setTag(child);

        return view;
    }

    private boolean getBoolean(int index) {
        return this.context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE).getBoolean(String.valueOf(index), true);
    }

    private void putBoolean(int index, boolean value) {
        SharedPreferences.Editor editor = this.context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE).edit();
        editor.putBoolean(String.valueOf(index), value);
        editor.apply();
    }

    @Override
    public int getGroupCount() {
        return mParent.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mParent.get(i).getArrayChildren().size();
    }

    @Override
    public Object getGroup(int i) {
        return mParent.get(i).getTitle();
    }

    @Override
    public Object getChild(int i, int i1) {
        return mParent.get(i).getArrayChildren().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

}
