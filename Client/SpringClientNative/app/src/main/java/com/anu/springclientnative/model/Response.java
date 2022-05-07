package com.anu.springclientnative.model;

public class Response {
    private String dbName;
    private Long timeTaken;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public Long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(Long timeTaken) {
        this.timeTaken = timeTaken;
    }

    @Override
    public String toString() {
        return "Response{" +
                "dbName='" + dbName + '\'' +
                ", timeTaken=" + timeTaken +
                '}';
    }
}
