package de.flerbuster.jobsearcherandroid.api

import de.flerbuster.jobsearcherandroid.R
import de.flerbuster.jobsearcherandroid.api.results.jobDetails.JobDetailResponse
import de.flerbuster.jobsearcherandroid.api.results.jobs.NewJobSearchResponse
import io.ktor.client.request.*

object ArbeitsApi : API(
    defaultKtorClient,
    "https://rest.arbeitsagentur.de/jobboerse/jobsuche-service",
    {
        header("User-Agent", "Jobsuche/2.9.2 (de.arbeitsagentur.jobboerse; build:1077; iOS 15.1.0) Alamofire/5.4.4")
        header("Host", "rest.arbeitsagentur.de")
        header("OAuthAccessToken", R.string.authToken)
        header("Connection", "keep-alive")
    }
) {
    suspend fun getJobs(
        was: String?,
        wo: String?,
        berufsfeld: String? = null,
        page: Int? = null,
        size: Int? = null,
        arbeitgeber: String? = null,
        zeitarbeit: Boolean? = null,
        angebotsart: Int? = null,
        befristung: Int? = null,
        arbeitszeit: String? = null,
        behinderung: Boolean? = null,
        corona: Boolean? = null,
        umkreis: Int? = null
    ) = subRequest<NewJobSearchResponse>("/pc/v4/jobs") {
        parameter("was", was)
        parameter("wo", wo)
        parameter("berufsfeld", berufsfeld)
        parameter("page", page)
        parameter("size", size)
        parameter("arbeitgeber", arbeitgeber)
        parameter("zeitarbeit", zeitarbeit)
        parameter("angebotsart", angebotsart)
        parameter("befristung", befristung)
        parameter("arbeitszeit", arbeitszeit)
        parameter("behinderung", behinderung)
        parameter("corona", corona)
        parameter("umkreis", umkreis)
    }

    suspend fun getJob(
        hashId: String = ""
    ) = subRequest<JobDetailResponse>("/pc/v2/jobdetails/$hashId")

}