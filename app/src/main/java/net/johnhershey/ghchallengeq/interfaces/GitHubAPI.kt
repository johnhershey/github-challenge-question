package net.johnhershey.ghchallengeq.interfaces

import net.johnhershey.ghchallengeq.models.ResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface GitHubAPI {
    @GET("brianchandotcom/liferay-portal/commits?per_page=30")

    fun getCommitList(): Call<List<ResponseItem>>
}