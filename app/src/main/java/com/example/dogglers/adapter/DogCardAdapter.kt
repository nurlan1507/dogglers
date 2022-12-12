/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.data.DataSource
import com.example.dogglers.model.Dog

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int,
    private val dataset : List<Dog>
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        var dogImage: ImageView? = view?.findViewById(R.id.dog_image)
        var dogName:TextView? = view?.findViewById(R.id.dog_name)
        var dogAge:TextView? = view?.findViewById(R.id.dog_age)
        var dogHobbies:TextView? = view?.findViewById(R.id.dog_hobbies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        val itemListType = when(layout){
            1->R.layout.vertical_horizontal_list_item
            2->R.layout.vertical_horizontal_list_item
            else -> {
                R.layout.grid_list_item
            }
        }
        val layout = LayoutInflater.from(parent.context).inflate(itemListType, parent, false)
        return DogCardViewHolder(layout)
    }

    override fun getItemCount():Int{
        return dataset.size
    }

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {

        val resources = context?.resources
        holder.dogImage?.setImageResource(dataset[position].imageResourceId)
        holder.dogName?.text = dataset[position].name
        holder.dogAge?.text = resources?.getString(R.string.dog_age)
            ?.let { String.format(it,dataset[position].age) }
        holder.dogHobbies?.text = resources?.getString(R.string.dog_hobbies)
            ?.let {String.format(it,dataset[position])}
        // TODO: Set the text for the current dog's hobbies by passing the hobbies to the
        //  R.string.dog_hobbies string constant.
        //  Passing an argument to the string resource looks like:
        //  resources?.getString(R.string.dog_hobbies, dog.hobbies)
    }
}
