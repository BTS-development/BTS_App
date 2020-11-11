package com.example.bluetoothtemperaturesubmitter.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.bluetoothtemperaturesubmitter.API.RetrofitHelper
import com.example.bluetoothtemperaturesubmitter.DTO.Groups
import com.example.bluetoothtemperaturesubmitter.R
import com.example.bluetoothtemperaturesubmitter.group.*
import kotlinx.android.synthetic.main.fragment_group.*
import kotlinx.android.synthetic.main.fragment_group.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GroupFragment : Fragment() {

    var arrayList = ArrayList<Groups>()
    var arrayList1 = ArrayList<Groups>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_group, container, false)

        val token : String? = arguments!!.getString("token")
        val pk = arguments?.getInt("pk")

        // Inflate the layout for this fragment
        view.group_create.setOnClickListener {
            if(activity != null) {
                val intent = Intent(activity, Group_create::class.java)
                intent.putExtra("token",token)
                intent.putExtra("pk", pk)
                this.startActivityForResult(intent,1)
            } else {
                Log.d("ERROR", "ERROR")
            }
        }
        view.group_join.setOnClickListener {
            if (activity != null){
                val intent = Intent(activity, Group_join::class.java)
                intent.putExtra("token",token)
                intent.putExtra("pk",pk)
                this.startActivityForResult(intent,1)
            }else {
                Log.d("ERROR", "ERROR")
            }
        }



        if (token != null) {
            refreshList(token)
        }

        view.group_listview.setOnItemClickListener{ adapterView: AdapterView<*>, view: View, position: Int, l: Long ->
            val intent = Intent(context, Group_manage_notion::class.java)
            intent.putExtra("pk",pk)
            intent.putExtra("token", token)
            intent.putExtra("group_id",arrayList[position].id)
            intent.putExtra("group_name",arrayList[position].name)
            intent.putExtra("group_code",arrayList[position].code)
            intent.putExtra("group_date",arrayList[position].created_at)
            intent.putExtra("group_owner",arrayList[position].owner)
            activity!!.startActivity(intent)
        }
        view.group_listview_member.setOnItemClickListener{ adapterView: AdapterView<*>, view: View, position: Int, long: Long ->
            val intent = Intent(context, Group_manage_notion::class.java)
            intent.putExtra("pk",pk)
            intent.putExtra("token", token)
            intent.putExtra("group_id",arrayList1[position].id)
            intent.putExtra("group_name",arrayList1[position].name)
            intent.putExtra("group_code",arrayList1[position].code)
            intent.putExtra("group_date",arrayList1[position].created_at)
            intent.putExtra("group_owner",arrayList1[position].owner)
            activity!!.startActivity(intent)
        }




        return view
    }

    private fun refreshList(token : String){
        RetrofitHelper().getGroupAPI().getMyGroup(token)
            .enqueue(object : Callback<List<Groups>> {
                override fun onResponse(
                    call: Call<List<Groups>>,
                    response: Response<List<Groups>>
                ) {
                    arrayList1 = (response.body() as ArrayList<Groups>?)!!

                    group_listview_member.adapter =
                        context?.let { GroupListAdapter(it, arrayList1, token) }
                }

                override fun onFailure(call: Call<List<Groups>>, t: Throwable) {
                    Log.d("ERROR", t.toString())
                }

            })

        RetrofitHelper().getGroupAPI().getGroup(token)
            .enqueue(object : Callback<List<Groups>> {
                override fun onResponse(
                    call: Call<List<Groups>>,
                    response: Response<List<Groups>>
                ) {
                    if(response.isSuccessful){
                        arrayList = (response.body() as ArrayList<Groups>?)!!
                        group_listview.adapter =
                            context?.let { GroupListAdapter(it, arrayList, token) }
                    }

                }

                override fun onFailure(call: Call<List<Groups>>, t: Throwable) {
                    Log.d("ERROR", t.toString())
                }


            })
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data != null){
            refreshList(data.getStringExtra("token"))
        }
    }

}