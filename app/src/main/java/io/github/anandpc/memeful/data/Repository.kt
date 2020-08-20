package io.github.anandpc.memeful.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import io.github.anandpc.memeful.data.model.BaseResponse
import io.github.anandpc.memeful.data.model.Data
import io.github.anandpc.memeful.data.remote.ImgurService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject


class Repository @Inject constructor(
    private val mImgurService: ImgurService
) {

    companion object {
        private const val TAG = "Repository"
    }

    fun fetchMemes(): MutableLiveData<List<Data>> {
        val memesListLD: MutableLiveData<List<Data>> = MutableLiveData()
        GlobalScope.launch(Dispatchers.IO) {

            // first check for db if present then show that
            // also fetch new data and update db

            val response: ResponseBody = mImgurService.fetchViralMemes()
            val baseResponse: BaseResponse =
                Gson().fromJson(response.string(), BaseResponse::class.java)
            Log.d(TAG, "getMemes: $baseResponse")
            if (baseResponse.success != null && baseResponse.success) {
                memesListLD.postValue(baseResponse.data)
            } else {
                memesListLD.postValue(emptyList())
                Log.e(TAG, "getMemes: Error ${baseResponse.status}")
            }
        }
        return memesListLD
    }
}