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

    private var mPage: Int = 0

    fun fetchMemes(mMemesListLD: MutableLiveData<List<Data>>) {
        GlobalScope.launch(Dispatchers.IO) {

            // first check for db if present then show that
            // also fetch new data and update db

            val response: ResponseBody = mImgurService.fetchViralMemes(page = mPage)
            val baseResponse: BaseResponse =
                Gson().fromJson(response.string(), BaseResponse::class.java)
            Log.d(TAG, "getMemes: $baseResponse")
            if (baseResponse.success != null && baseResponse.success) {
                mMemesListLD.postValue(baseResponse.data)
                mPage++
            } else {
                mMemesListLD.postValue(emptyList())
                Log.e(TAG, "getMemes: Error ${baseResponse.status}")
            }
        }
    }

    companion object {
        private const val TAG = "Repository"
    }
}