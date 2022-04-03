package go_rest_api_v12.go_rest_api_v12.models;

import java.util.Date;

public class TodoModel {

    private int id;
    private int userId;
    private String title;
    private Date dueDate;
    private String status;

    public TodoModel() {
    }

    public TodoModel(String title, Date dueDate, String status) {
        this.title = title;
        this.dueDate = dueDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "TodoModel{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", dueDate=" + dueDate +
                ", status='" + status + '\'' +
                '}';
    }

    /*

id: 1833,
user_id: 3661,
title: "Tergiversatio minima venio voveo crebro verus adhaero aptus sum.",
due_on: "2022-04-06T00:00:00.000+05:30",
status: "completed"

    */
}
