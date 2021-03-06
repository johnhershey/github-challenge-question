package net.johnhershey.ghchallengeq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

const val BASE_URL = "https://api.github.com/repos/"

class MainActivity : AppCompatActivity() {

    var linearLayoutManager: LinearLayoutManager
    var the Adapter: CommitsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerview_commits.layoutManager = linearLayoutManager

        getCommitList()
    }

    private fun getCommitList() {

    }
}
