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

package es.netrunners.expandablelistadapter.adapter.items;

import java.util.ArrayList;

public class Parent implements Item {
    private String title;
    private int ID;
    private ArrayList<Child> arrayChildren;

    public Parent(int id, String title) {
        setID(id);
        setTitle(title);
    }

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String mTitle) {
        this.title = mTitle;
    }

    public ArrayList<Child> getArrayChildren() {
        return arrayChildren;
    }

    public void setArrayChildren(ArrayList<Child> mArrayChildren) {
        this.arrayChildren = mArrayChildren;
    }

}
