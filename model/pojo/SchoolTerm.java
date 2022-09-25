package educationaloffermanagement.model.pojo;

import java.util.Date;

public class SchoolTerm {

    private String schoolTermCode;
    private Date startDate;
    private Date endDate;
    private int responseCode;

    public SchoolTerm() {
    }

    public SchoolTerm(String schoolTermCode, Date startDate, Date endDate) {
        this.schoolTermCode = schoolTermCode;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getSchoolTermCode() {
        return schoolTermCode;
    }

    public void setSchoolTermCode(String schoolTermCode) {
        this.schoolTermCode = schoolTermCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public boolean equals(Object object) {
        boolean isEqual = false;
        if (object == this) {
            isEqual = true;
        }
        if ((object != null) && (object instanceof SchoolTerm)) {
            SchoolTerm schoolTerm = (SchoolTerm) object;
            isEqual = ((this.getSchoolTermCode().equals(schoolTerm.schoolTermCode))
                    && (this.getStartDate().equals(schoolTerm.startDate))
                    && this.getEndDate().equals(getEndDate()));
        }
        return isEqual;
    }

}