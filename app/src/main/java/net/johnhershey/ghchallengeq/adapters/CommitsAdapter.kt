package net.johnhershey.ghchallengeq.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_commit.view.*
import net.johnhershey.ghchallengeq.R
import net.johnhershey.ghchallengeq.models.ResponseItem

class CommitsAdapter(private val context: Context, private val commitList: List<ResponseItem>): RecyclerView.Adapter<CommitsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //TextView vars
        var hash: TextView = itemView.txtCommitHash
        var author: TextView = itemView.txtAuthor
        var message: TextView = itemView.txtMessage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.row_commit, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return commitList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Remove extra characters from author's name
        val originalAuthorString = commitList[position].commit.author.toString()
        val cleanAuthorString = originalAuthorString.substring(12 until originalAuthorString.length - 1)

        //Set text to new values
        holder.author.text = cleanAuthorString
        holder.hash.text = commitList[position].sha
        holder.message.text = commitList[position].commit.message

        //Log.d("COMMIT: $position", cleanAuthorString + " " + commitList[position].sha + " " + commitList[position].commit.message)
    }
}