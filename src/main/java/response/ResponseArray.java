package response;

import models.Mentor;

import java.util.List;

public class ResponseArray {
    private int status;
    private String message;
    private List<Mentor> data;

    public ResponseArray(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Mentor> getData() {
        return data;
    }

    public void setData(List<Mentor> data) {
        this.data = data;
    }
}
