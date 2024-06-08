package api

import model.TeacherResponse
import retrofit2.http.GET

interface ApiService {
    @GET("list/teacher")
    suspend fun getTeachers(): TeacherResponse
}