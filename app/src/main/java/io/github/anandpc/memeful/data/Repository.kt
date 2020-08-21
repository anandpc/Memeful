package io.github.anandpc.memeful.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import io.github.anandpc.memeful.data.local.dao.MemesDao
import io.github.anandpc.memeful.data.model.BaseResponse
import io.github.anandpc.memeful.data.model.Data
import io.github.anandpc.memeful.data.remote.ImgurService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject


class Repository @Inject constructor(
    private val mImgurService: ImgurService,
    private val memesDao: MemesDao
) {

    private var mPage: Int = 0
    private var mMemesListLD: MutableLiveData<List<Data>?> = MutableLiveData()

    fun fetchMemes(): LiveData<List<Data>?> {
        GlobalScope.launch(Dispatchers.IO) {
            // fetch from remote and insert in database
            val response: ResponseBody = mImgurService.fetchViralMemes(page = mPage)
            val baseResponse: BaseResponse =
                Gson().fromJson(response.string(), BaseResponse::class.java)
            Log.d(TAG, "getMemes: $baseResponse")
            if (baseResponse.success != null && baseResponse.success) {
                if (mPage == 0) {
                    memesDao.deleteAllMemes()
                }
                // inserted in table
                memesDao.insertMemes(baseResponse.data)
                mPage++
                // fetch from table
                mMemesListLD.postValue(memesDao.getAllMemes())
            } else {
                // handle error scenarios
                Log.e(TAG, "getMemes: Error ${baseResponse.status}")
            }
        }
        return mMemesListLD
    }

    companion object {
        private const val TAG = "Repository"
    }
}