package com.anu.springclientnative;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.anu.springclientnative.model.Demo;
import com.anu.springclientnative.model.Department;
import com.anu.springclientnative.model.Student;
import com.anu.springclientnative.model.Subject;
import com.anu.springclientnative.model.UpdateStudentReq;
import com.anu.springclientnative.retrofit.QueryApi;
import com.anu.springclientnative.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG_1 = "LOAD_DATA";
    private static final String TAG_2 = "CREATE_STUDENT";
    private static final String TAG_3 = "GET_ALL_STUDENTS";
    private static final String TAG_4 = "GET_STUDENT_BY_ID";
    private static final String TAG_5 = "UPDATE_STUDENT";
    private static final String TAG_6 = "DELETE_STUDENT";
    private static final String TAG_7 = "GET_BY_NAME_OR_BIRTH";
    private Long start, end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
    }

    private void initializeComponents() {
        Button loadDataBtn = findViewById(R.id.load);
        Button queryOneBtn = findViewById(R.id.q1);
        Button queryTwoBtn = findViewById(R.id.q2);
        Button queryThreeBtn = findViewById(R.id.q3);
        Button queryFourBtn = findViewById(R.id.q4);
        Button queryFiveBtn = findViewById(R.id.q5);
        Button querySixBtn = findViewById(R.id.q6);
        Button demoBtn = findViewById(R.id.demo);

        RetrofitService retrofitService = new RetrofitService();
        QueryApi queryApi = retrofitService.getRetrofit().create(QueryApi.class);

        QueryApi queryApiDemo = retrofitService.getRetrofitDemoInstance().create(QueryApi.class);

        QueryApi relationalQuery = retrofitService.getRetrofitRelational().create(QueryApi.class);
        QueryApi graphQuery = retrofitService.getRetrofitGraph().create(QueryApi.class);

        demoRetrofitCheck(queryApiDemo, demoBtn);

//        loadData(queryApi, loadDataBtn);
        createStudent(relationalQuery, graphQuery, queryOneBtn);
        getAllStudents(relationalQuery, graphQuery, queryTwoBtn);
        getStudentById(relationalQuery, graphQuery, queryThreeBtn);
        updateStudent(relationalQuery, graphQuery, queryFourBtn);
        deleteStudent(relationalQuery, graphQuery, queryFiveBtn);
        getStudentByNameOrBirthYear(relationalQuery, graphQuery, querySixBtn);
    }

    /**
     * Get all students
     **/
    private void getAllStudents(QueryApi relationalQuery, QueryApi graphQuery, Button queryTwoBtn) {
        queryTwoBtn.setOnClickListener(view -> {
            String result = "Relational: ";
            // RELATIONAL RESPONSE
            start = System.currentTimeMillis();
            relationalQuery.getAllStudentsRelational()
                    .enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                            Log.d(TAG_3, response.message() + response.body() + response.raw());
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {
                            Log.d(TAG_3, t.getMessage());
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
            end = System.currentTimeMillis();

            result += ((end - start) + " Graph: ");

            // GRAPH RESPONSE
            start = System.currentTimeMillis();
            graphQuery.getAllStudentsGraph()
                    .enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                            Log.d(TAG_3, response.toString());
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {
                            Log.d(TAG_3, t.getMessage());
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
            end = System.currentTimeMillis();
            result += String.valueOf(end - start);
            alertDialog(result, "Info");
        });
    }

    /**
     * Get students By Id
     **/
    private void getStudentById(QueryApi relationalQuery, QueryApi graphQuery, Button queryThreeBtn) {
        queryThreeBtn.setOnClickListener(view -> {
            String result = "Relational: ";
            // RELATIONAL RESPONSE
            start = System.currentTimeMillis();
            relationalQuery.getStudentByIdRelational(4)
                    .enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                            Log.d(TAG_4, response.toString());
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {
                            Log.d(TAG_4, t.getMessage());
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
            end = System.currentTimeMillis();

            result += ((end - start) + " Graph: ");

            // GRAPH RESPONSE
            start = System.currentTimeMillis();
            graphQuery.getStudentByIdGraph(11)
                    .enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                            Log.d(TAG_4, response.toString());
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {
                            Log.d(TAG_4, t.getMessage());
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
            end = System.currentTimeMillis();
            result += String.valueOf(end - start);
            alertDialog(result, "Info");
        });
    }

    /**
     * Delete Student By Id
     **/
    private void deleteStudent(QueryApi relationalQuery, QueryApi graphQuery, Button queryFiveBtn) {
        queryFiveBtn.setOnClickListener(view -> {
            String result = "Relational: ";
            // RELATIONAL RESPONSE
            start = System.currentTimeMillis();
            relationalQuery.deleteStudentByIdRelational(16)
                    .enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                            Log.d(TAG_6, response.toString());
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {
                            Log.d(TAG_6, t.getMessage());
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
            end = System.currentTimeMillis();

            result += ((end - start) + " Graph: ");

            // GRAPH RESPONSE
            start = System.currentTimeMillis();
            graphQuery.deleteStudentByIdGraph(65)
                    .enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                            Log.d(TAG_6, response.toString());
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {
                            Log.d(TAG_6, t.getMessage());
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
            end = System.currentTimeMillis();
            result += String.valueOf(end - start);
            alertDialog(result, "Info");
        });
    }

    /**
     * Get students by name or birth year
     **/
    private void getStudentByNameOrBirthYear(QueryApi relationalQuery, QueryApi graphQuery, Button querySixBtn) {
        querySixBtn.setOnClickListener(view -> {
            String result = "Relational: ";
            // RELATIONAL RESPONSE
            start = System.currentTimeMillis();
            relationalQuery.getStudentByNameOrBirthYearRelational("Anupam", 1999)
                    .enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                            Log.d(TAG_7, response.toString());
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {
                            Log.d(TAG_7, t.getMessage());
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
            end = System.currentTimeMillis();

            result += ((end - start) + " Graph: ");

            // GRAPH RESPONSE
            start = System.currentTimeMillis();
            graphQuery.getStudentByNameOrBirthYearGraph("Anupam", 1999)
                    .enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                            Log.d(TAG_7, response.toString());
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {
                            Log.d(TAG_7, t.getMessage());
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
            end = System.currentTimeMillis();
            result += String.valueOf(end - start);
            alertDialog(result, "Info");
        });
    }


    /**
     * Update student
     **/
    private void updateStudent(QueryApi relationalQuery, QueryApi graphQuery, Button queryFourBtn) {
        queryFourBtn.setOnClickListener(view -> {

            UpdateStudentReq updateStudentReq = new UpdateStudentReq(18L, "test", "India", 2000);

            String result = "Relational: ";
            // RELATIONAL RESPONSE
            start = System.currentTimeMillis();
            relationalQuery.updateStudentRelational(updateStudentReq)
                    .enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                            Log.d(TAG_5, response.toString());
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {
                            Log.d(TAG_5, t.getMessage());
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
            end = System.currentTimeMillis();

            result += ((end - start) + " Graph: ");

            // GRAPH RESPONSE
            start = System.currentTimeMillis();
            graphQuery.updateStudentGraph(updateStudentReq)
                    .enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                            Log.d(TAG_5, response.toString());
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {
                            Log.d(TAG_5, t.getMessage());
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
            end = System.currentTimeMillis();
            result += String.valueOf(end - start);
            alertDialog(result, "Info");
        });
    }

    /**
     * Create Student
     **/
    private void createStudent(QueryApi relationalQuery, QueryApi graphQuery, Button queryOneBtn) {
        queryOneBtn.setOnClickListener(view -> {
            List<Subject> subjects = new ArrayList<>();
            subjects.add(new Subject("Object modelling", 89));
            subjects.add(new Subject("Geofencing", 50));
            Student student = new Student("hello-test", 1999, "India", subjects, new Department("CSE"));

            String result = "Relational: ";
            // RELATIONAL RESPONSE
            start = System.currentTimeMillis();
            relationalQuery.createStudentRelational(student)
                    .enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                            Log.d(TAG_2, response.toString());
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                            alertDialog(response.toString(), "Info");

                        }

                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {
                            Log.d(TAG_2, t.getMessage());
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            alertDialog(t.getMessage(), "Info");

                        }
                    });
            end = System.currentTimeMillis();

            result += ((end - start) + " Graph: ");

            // GRAPH RESPONSE
            start = System.currentTimeMillis();
            graphQuery.createStudentGraph(student)
                    .enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                            Log.d(TAG_2, response.toString());
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {
                            Log.d(TAG_2, t.getMessage());
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
            end = System.currentTimeMillis();
            result += String.valueOf(end - start);
            alertDialog(result, "Info");
        });
    }

    /**
     * Testing demo api
     **/
    private void demoRetrofitCheck(QueryApi queryApiDemo, Button demoBtn) {
        demoBtn.setOnClickListener(view -> {
            queryApiDemo.getDemoData()
                    .enqueue(new Callback<Demo>() {
                        @Override
                        public void onResponse(Call<Demo> call, Response<Demo> response) {
                            Log.d(TAG_1, "Data loaded successfully!!" + response);
                            Toast.makeText(MainActivity.this, response + "", Toast.LENGTH_LONG).show();
                            alertDialog(response.toString(), "Info");
                        }

                        @Override
                        public void onFailure(Call<Demo> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
//                            alertDialog("Failed to load data!!", "Info");
                        }
                    });
        });
    }


    //    private void loadData(QueryApi queryApi, Button loadDataBtn) {
//        loadDataBtn.setOnClickListener(view -> {
//            queryApi.loadData()
//                    .enqueue(new Callback<List<retrofit2.Response>>() {
//                        @Override
//                        public void onResponse(Call<List<retrofit2.Response>> call, retrofit2.Response<List<retrofit2.Response>> response) {
//                            Log.d(TAG_1, "Data loaded successfully!!" + response);
//                            Toast.makeText(MainActivity.this, response + "", Toast.LENGTH_LONG).show();
//                            alertDialog("Data loaded successfully!!", "Info");
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<retrofit2.Response>> call, Throwable t) {
//                            Toast.makeText(MainActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
//                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
//                            alertDialog("Failed to load data!!", "Info");
//                        }
//                    });
//        });
//    }
//
//    private void createStudent(QueryApi queryApi, Button queryOneBtn) {
//        queryOneBtn.setOnClickListener(view -> {
//            queryApi.getQueryOneResponse()
//                    .enqueue(new Callback<List<retrofit2.Response>>() {
//                        @Override
//                        public void onResponse(Call<List<retrofit2.Response>> call, retrofit2.Response<List<retrofit2.Response>> response) {
//                            Log.d(TAG_2, "Query 1 ran successfully!!" + response);
//                            Toast.makeText(MainActivity.this, response + "", Toast.LENGTH_LONG).show();
//                            alertDialog("Data loaded successfully!!", "Info");
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<retrofit2.Response>> call, Throwable t) {
//                            Toast.makeText(MainActivity.this, "Failed to run query 1", Toast.LENGTH_SHORT).show();
//                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
//                            alertDialog("Data loaded successfully!!", "Info");
//                        }
//                    });
//        });
//    }
//
//    private void getAllStudents(QueryApi queryApi, Button queryTwoBtn) {
//        queryTwoBtn.setOnClickListener(view -> {
//            queryApi.getQueryTwoResponse()
//                    .enqueue(new Callback<List<retrofit2.Response>>() {
//                        @Override
//                        public void onResponse(Call<List<retrofit2.Response>> call, retrofit2.Response<List<retrofit2.Response>> response) {
//                            Log.d(TAG_3, "Query 2 ran successfully!!" + response);
//                            Toast.makeText(MainActivity.this, response + "", Toast.LENGTH_LONG).show();
//                            alertDialog("Data loaded successfully!!", "Info");
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<retrofit2.Response>> call, Throwable t) {
//                            Toast.makeText(MainActivity.this, "Failed to run query 2", Toast.LENGTH_SHORT).show();
//                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
//                            alertDialog("Data loaded successfully!!", "Info");
//                        }
//                    });
//        });
//    }
//
//    private void getStudentById(QueryApi queryApi, Button queryThreeBtn) {
//        queryThreeBtn.setOnClickListener(view -> {
//            queryApi.getQueryThreeResponse()
//                    .enqueue(new Callback<List<retrofit2.Response>>() {
//                        @Override
//                        public void onResponse(Call<List<retrofit2.Response>> call, retrofit2.Response<List<retrofit2.Response>> response) {
//                            Log.d(TAG_4, "Query 3 ran successfully!!" + response);
//                            Toast.makeText(MainActivity.this, response + "", Toast.LENGTH_LONG).show();
//                            alertDialog("Data loaded successfully!!", "Info");
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<retrofit2.Response>> call, Throwable t) {
//                            Toast.makeText(MainActivity.this, "Failed to run query 3", Toast.LENGTH_SHORT).show();
//                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
//                            alertDialog("Data loaded successfully!!", "Info");
//                        }
//                    });
//        });
//    }
//
//    private void updateStudent(QueryApi queryApi, Button queryFourBtn) {
//        queryFourBtn.setOnClickListener(view -> {
//            queryApi.getQueryFourResponse()
//                    .enqueue(new Callback<List<retrofit2.Response>>() {
//                        @Override
//                        public void onResponse(Call<List<retrofit2.Response>> call, retrofit2.Response<List<retrofit2.Response>> response) {
//                            Log.d(TAG_5, "Query 4 ran successfully!!" + response);
//                            Toast.makeText(MainActivity.this, response + "", Toast.LENGTH_LONG).show();
//                            alertDialog("Data loaded successfully!!", "Info");
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<retrofit2.Response>> call, Throwable t) {
//                            Toast.makeText(MainActivity.this, "Failed to run query 4", Toast.LENGTH_SHORT).show();
//                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
//                            alertDialog("Data loaded successfully!!", "Info");
//                        }
//                    });
//        });
//    }
//
//    private void deleteStudent(QueryApi queryApi, Button queryFiveBtn) {
//        queryFiveBtn.setOnClickListener(view -> {
//            queryApi.getQueryFiveResponse()
//                    .enqueue(new Callback<List<retrofit2.Response>>() {
//                        @Override
//                        public void onResponse(Call<List<retrofit2.Response>> call, retrofit2.Response<List<retrofit2.Response>> response) {
//                            Log.d(TAG_6, "Query 5 ran successfully!!" + response);
//                            Toast.makeText(MainActivity.this, response + "", Toast.LENGTH_LONG).show();
//                            alertDialog("Data loaded successfully!!", "Info");
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<retrofit2.Response>> call, Throwable t) {
//                            Toast.makeText(MainActivity.this, "Failed to run query 5", Toast.LENGTH_SHORT).show();
//                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
//                            alertDialog("Data loaded successfully!!", "Info");
//                        }
//                    });
//        });
//    }
//
//    private void getStudentByNameOrBirthYear(QueryApi queryApi, Button querySixBtn) {
//        querySixBtn.setOnClickListener(view -> {
//            queryApi.getQuerySixResponse()
//                    .enqueue(new Callback<List<retrofit2.Response>>() {
//                        @Override
//                        public void onResponse(Call<List<retrofit2.Response>> call, retrofit2.Response<List<retrofit2.Response>> response) {
//                            Log.d(TAG_7, "Query 6 ran successfully!!" + response);
//                            Toast.makeText(MainActivity.this, response + "", Toast.LENGTH_LONG).show();
//                            alertDialog("Data loaded successfully!!", "Info");
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<retrofit2.Response>> call, Throwable t) {
//                            Toast.makeText(MainActivity.this, "Failed to run query 6", Toast.LENGTH_SHORT).show();
//                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
//                            alertDialog("Data loaded successfully!!", "Info");
//                        }
//                    });
//        });
//    }
    private void alertDialog(String message, String title) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(message);
        dialog.setTitle(title);
        dialog.setPositiveButton("OK",
                (dialog1, which) -> Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show());
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }
}