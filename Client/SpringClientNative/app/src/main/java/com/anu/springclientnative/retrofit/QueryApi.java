package com.anu.springclientnative.retrofit;

import com.anu.springclientnative.model.Demo;
import com.anu.springclientnative.model.Student;
import com.anu.springclientnative.model.UpdateStudentReq;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface QueryApi {

    @GET("/load-data")
    Call<List<Response>> loadData();

    @POST("/mysql/student/create")
    Call<Object> createStudentRelational(@Body Student student);

    @POST("/neo4j/student/create")
    Call<Object> createStudentGraph(@Body Student student);


    @GET("/mysql/student/getAllStudents")
    Call<Object> getAllStudentsRelational();

    @GET("/neo4j/student/getAllStudents")
    Call<Object> getAllStudentsGraph();

    @GET("/mysql/student/getStudentById/{id}")
    Call<Object> getStudentByIdRelational(@Path("id") int id);

    @GET("/neo4j/student/getStudentById/{id}")
    Call<Object> getStudentByIdGraph(@Path("id") int id);

    @PUT("/mysql/student/update")
    Call<Object> updateStudentRelational(@Body UpdateStudentReq updateStudentReq);

    @PUT("/neo4j/student/update")
    Call<Object> updateStudentGraph(@Body UpdateStudentReq updateStudentReq);

    @DELETE("/mysql/student/delete/{id}")
    Call<Object> deleteStudentByIdRelational(@Path("id") int id);

    @DELETE("/neo4j/student/delete/{id}")
    Call<Object> deleteStudentByIdGraph(@Path("id") int id);

    @GET("/mysql/student/getStudentByNameOrBirthYear/{name}/{year}")
    Call<Object> getStudentByNameOrBirthYearRelational(@Path("name") String name, @Path("year") int year);

    @GET("/neo4j/student/getStudentByNameOrBirthYear/{name}/{year}")
    Call<Object> getStudentByNameOrBirthYearGraph(@Path("name") String name, @Path("year") int year);


    @GET("/random")
    Call<Demo> getDemoData();

}
