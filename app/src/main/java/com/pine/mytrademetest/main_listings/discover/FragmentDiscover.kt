package com.pine.mytrademetest.main_listings.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pine.mytrademetest.R
import com.pine.mytrademetest.app
import com.pine.mytrademetest.n
import com.pine.mytrademetest.net.beans.ListingItemBean
import com.pine.mytrademetest.net.require
import kotlinx.android.synthetic.main.main_listing_discover.*


class FragmentDiscover : Fragment() {

    var lists = ArrayList<ListingItemBean>();
    var searchListAdapter = ViewAdapterDiscover(this);

    lateinit var search_list: RecyclerView;


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.main_listing_discover, container, false)
        search_list = view.findViewById(R.id.search_list)

        initSearchList();

        reloadLatest()

        return view;
    }

    private fun initSearchList() {
        var layoutManager = LinearLayoutManager(app())

        search_list.setLayoutManager(layoutManager)
        search_list.adapter = searchListAdapter;


    }

    private fun reloadLatest(){
        n().latest().require {
            it.let {
                Toast.makeText(app(), it, Toast.LENGTH_LONG).show()

                searchListAdapter.notifyDataSetChanged()
            }
        }
    }
}