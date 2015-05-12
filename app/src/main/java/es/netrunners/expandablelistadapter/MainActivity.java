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

package es.netrunners.expandablelistadapter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ExpandableListView;

import java.util.ArrayList;

import es.netrunners.expandablelistadapter.adapter.ExpandableListAdapter;
import es.netrunners.expandablelistadapter.adapter.items.Child;
import es.netrunners.expandablelistadapter.adapter.items.Parent;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExpandableListView mExpandableList = (ExpandableListView) findViewById(R.id.expandable_list);

        ArrayList<Parent> arrayParents = getParents();

        ExpandableListAdapter listAdapter = new ExpandableListAdapter(
                this, arrayParents);

        mExpandableList.setAdapter(listAdapter);
    }

    private ArrayList<Parent> getParents() {
        ArrayList<Parent> arrayParents = new ArrayList<>();

        Parent parent1 = new Parent(1, "Castilla-La Mancha");

        ArrayList<Child> arrayChildren1 = new ArrayList<>();

        Child child1 = new Child(11, "Albacete");
        child1.setParent(parent1);
        arrayChildren1.add(child1);

        Child child2 = new Child(12, "Ciudad Real");
        child2.setParent(parent1);
        arrayChildren1.add(child2);
        Child child3 = new Child(13, "Cuenca");
        child3.setParent(parent1);
        arrayChildren1.add(child3);
        Child child4 = new Child(14, "Guadalajara");
        child4.setParent(parent1);
        arrayChildren1.add(child4);
        Child child5 = new Child(15, "Toledo");
        child5.setParent(parent1);
        arrayChildren1.add(child5);

        parent1.setArrayChildren(arrayChildren1);
        arrayParents.add(parent1);

        Parent parent2 = new Parent(2, "Madrid");
        ArrayList<Child> arrayChildren2 = new ArrayList<>();
        Child child6 = new Child(21, "Madrid");
        child6.setParent(parent2);
        arrayChildren2.add(child6);
        parent2.setArrayChildren(arrayChildren2);
        arrayParents.add(parent2);
        Parent parent3 = new Parent(3, "Extremadura");
        ArrayList<Child> arrayChildren3 = new ArrayList<>();
        Child child7 = new Child(31, "Cáceres");
        child7.setParent(parent3);
        arrayChildren3.add(child7);
        Child child8 = new Child(32, "Badajoz");
        child8.setParent(parent3);
        arrayChildren3.add(child8);
        parent3.setArrayChildren(arrayChildren3);
        arrayParents.add(parent3);
        return arrayParents;
    }

}
