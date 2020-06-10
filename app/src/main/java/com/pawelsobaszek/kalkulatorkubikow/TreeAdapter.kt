package com.pawelsobaszek.kalkulatorkubikow

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class TreeAdapter(var context: Context, var tree: ArrayList<TreeItem>) : BaseAdapter() {

    private class ViewHolder(row: View?) {
        var txt1: TextView
        var txt2: TextView
        var txt3: TextView
        var txt4: TextView

        init {
            this.txt1 = row?.findViewById(R.id.tree_id) as TextView
            this.txt2 = row?.findViewById(R.id.tree_srednica) as TextView
            this.txt3 = row?.findViewById(R.id.tree_dlugosc) as TextView
            this.txt4 = row?.findViewById(R.id.tree_wynik) as TextView
        }
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.tree_list, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val tree: TreeItem = getItem(position) as TreeItem
        viewHolder.txt1.text = tree.id.toString()
        viewHolder.txt2.text = tree.srednica
        viewHolder.txt3.text = tree.dlugosc
        viewHolder.txt4.text = tree.wynik

        return view as View
    }

    override fun getItem(position: Int): Any {
        return tree.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return tree.count()
    }

}