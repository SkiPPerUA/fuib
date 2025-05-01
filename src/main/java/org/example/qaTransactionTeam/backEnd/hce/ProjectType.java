package org.example.qaTransactionTeam.backEnd.hce;

public enum ProjectType {
    MASTERCARD("mdes"),
    VISA("vts");
    private String value;
    ProjectType(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
    @Override
    public String toString() {
        return "ProjectType{" +
                "value='" + value + '\'' +
                '}';
    }
}

