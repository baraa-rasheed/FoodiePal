package com.example.foodiepal

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton

import java.io.Serializable

// RecipesFragment.kt
class RecipesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeList: ArrayList<Recipe>
    private lateinit var adapter: RecipeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recipes, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recipeList = ArrayList()
        adapter = RecipeAdapter(recipeList)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        recipeList.add(Recipe("Burger","1.Meat 2.Tomato 3.Bread","Put all together and enjoy","https://www.chicken.ca/wp-content/uploads/2013/05/Moist-Chicken-Burgers.jpg","30 minutes",3f))
        recipeList.add(Recipe("Pizza","1.Meat 2.Tomato 3.Bread","Put all together and enjoy","https://t3.ftcdn.net/jpg/00/27/57/96/360_F_27579652_tM7V4fZBBw8RLmZo0Bi8WhtO2EosTRFD.jpg","40 minutes",5f))
        recipeList.add(Recipe("Chicken","1.Meat 2.Tomato 3.Bread","Put all together and enjoy","https://assets.bonappetit.com/photos/62f5674caf9bae430097be0f/3:2/w_2793,h_1862,c_limit/0810-no-fail-roast-chicken-v2.jpg","50 minutes",2f))

        // Add a new recipe
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            // Show dialog to add new recipe
            showAddRecipeDialog()
        }

        return view
    }

    @SuppressLint("MissingInflatedId")
    private fun showAddRecipeDialog() {
        val dialogBuilder = AlertDialog.Builder(context)
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_recipe, null)

        val titleEditText = dialogView.findViewById<EditText>(R.id.edit_text_title)
        val ingredientsEditText = dialogView.findViewById<EditText>(R.id.edit_text_ingredients)
        val instructionsEditText = dialogView.findViewById<EditText>(R.id.edit_text_instructions)
        val ratingBarView = dialogView.findViewById<RatingBar>(R.id.ratingBar)
        val durationEditText = dialogView.findViewById<EditText>(R.id.recipe_duration)
        val imageEditText = dialogView.findViewById<EditText>(R.id.recipe_image)

        dialogBuilder.setView(dialogView)
            .setTitle("Add New Recipe")
            .setPositiveButton("Save") { dialog, _ ->
                val title = titleEditText.text.toString().trim()
                val ingredients = ingredientsEditText.text.toString().trim()
                val instructions = instructionsEditText.text.toString().trim()
                val duration = durationEditText.text.toString().trim()
                val rating = ratingBarView.rating
                val image = imageEditText.text.toString().trim()

                recipeList.add(Recipe(title,ingredients,instructions,image,duration,rating))
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .create()

        dialogBuilder.show()
    }

}

class RecipeAdapter(private val recipeList: ArrayList<Recipe>) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.title.text = recipe.title
        holder.ingredients.text = recipe.ingredients
        holder.instructions.text = recipe.instructions
        holder.duration.text = recipe.duration
        holder.ratingBar.rating = recipe.rating

        Glide.with(holder.itemView.context)
            .load(recipe.image)
            .placeholder(R.drawable.recipe_book)
            .into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val ingredients: TextView = itemView.findViewById(R.id.ingrediants)
        val duration: TextView = itemView.findViewById(R.id.duration)
        val instructions: TextView = itemView.findViewById(R.id.instructions)
        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
        val imageView: ImageView = itemView.findViewById(R.id.recipe_image)
    }
}

data class Recipe(
    val title: String,
    val ingredients: String,
    val instructions: String,
    val image:String,
    val duration:String,
    val rating: Float
): Serializable

