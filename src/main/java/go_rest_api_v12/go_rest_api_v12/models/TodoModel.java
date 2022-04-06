package go_rest_api_v12.go_rest_api_v12.models;

public class TodoModel {

    private int id;
    private int user_id;
    private String title;

    private String due_on;

    private String status;

    public TodoModel() {
    }

    public TodoModel(String title, String dueDate, String status) {
        this.title = title;
        this.due_on = dueDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDue_on() {
        return due_on;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "TodoModel{" +
                "id=" + id +
                ", userId=" + user_id +
                ", title='" + title + '\'' +
                ", dueDate=" + due_on +
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
