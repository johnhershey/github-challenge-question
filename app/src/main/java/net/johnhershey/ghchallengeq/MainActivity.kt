package net.johnhershey.ghchallengeq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import net.johnhershey.ghchallengeq.adapters.CommitsAdapter
import net.johnhershey.ghchallengeq.interfaces.GitHubAPI
import net.johnhershey.ghchallengeq.models.ResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.github.com/repos/"

class MainActivity : AppCompatActivity() {

    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var theAdapter: CommitsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerview_commits.layoutManager = linearLayoutManager

        getCommitList()
    }

    private fun getCommitList() {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubAPI::class.java)

        val retrofitData = retrofitBuilder.getCommitList()

        //Make API call
        retrofitData.enqueue(object : Callback<List<ResponseItem>?> {
            override fun onResponse(
                call: Call<List<ResponseItem>?>,
                response: Response<List<ResponseItem>?>
            ) {
                val responseBody = response.body()!!

                theAdapter = CommitsAdapter(baseContext, responseBody)
                theAdapter.notifyDataSetChanged()
                recyclerview_commits.adapter = theAdapter

                Log.d("MainActivity", "API Call Successful!")
            }

            override fun onFailure(call: Call<List<ResponseItem>?>, t: Throwable) {
                //Log and display error message
                Log.d("MainActivity", "getCommitList: onFailure: " + t.message)

                Toast.makeText(this@MainActivity, "Data Error:\n" + t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
