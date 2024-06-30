package com.rbackent.app.ui.viewmodel
import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rbackent.app.ui.model.final_api.EmploymentDetailResponse
import com.rbackent.app.ui.network.Repository
import com.rbackent.app.ui.utils.Resource
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

open class ViewModel(
    private val application: Application = Application(),
    private val repository: Repository = Repository()
) : ViewModel() {
    val userFormDetail = MutableLiveData<Resource<EmploymentDetailResponse>>()
    fun getUserFormDetail(
        context: Context,
        ivCo: CircleImageView,
        ivTick: CircleImageView,
        ivTicked: CircleImageView,
        type: String
    ) {
        userFormDetail.value = Resource.loading(null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.userFormData(context, ivCo, ivTick, ivTicked,type)
                withContext(Dispatchers.Main) {
                    if (response?.code()==200) {
                        if (response.body()?.code == 200) {
                            userFormDetail.value = Resource.success(
                                response.body()!!,
                                response.body()?.message.toString()
                            )
                        } else {
                            userFormDetail.value =
                                Resource.error(
                                    null,
                                    response.body()?.message.toString()
                                )
                        }
                    } else {
                        userFormDetail.value =
                            Resource.error(null, response?.message())
                    }
                }
            } catch (t: Throwable) {
                withContext(Dispatchers.Main) {
                    if (t !is UnknownHostException) {
                        userFormDetail.value =
                            Resource.error(null, t.message.toString())
                    } else {
                        userFormDetail.value = Resource.error(null, null)
                    }
                }
            }
        }
    }
}