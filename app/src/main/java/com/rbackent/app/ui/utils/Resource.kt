package com.rbackent.app.ui.utils
import com.app.rbakent.utils.Status


data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    companion object{
        fun <T> success(data: T, message: String)=
            Resource(status = Status.SUCCESS, data = data, message = message)

        fun <T> error(data: T?, message: String?) =
            Resource(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> =
            Resource(status = Status.LOADING, data = data, message = null)
    }

}