package com.example.demo2;

import java.sql.SQLException;

public class loansData {
    private String idloans;
    private String client;
    private String empl;
    private String loan;
    private String date_b;
    private String date_e;

    DB db = new DB();
    public loansData(String idloans, String client, String empl, String loan, String date_b, String date_e) throws SQLException, ClassNotFoundException {

        this.idloans=idloans;
        client = String.valueOf(db.client(client));
        this.client=client;
        empl = String.valueOf(db.empl(empl));
        this.empl=empl;
        loan=String.valueOf(db.serv(loan));
        this.loan=loan;
        this.date_b=date_b;
        this.date_e=date_e;
    }
    public loansData() {
    }
    public String getIdloans() {
        return idloans;
    }

    public void setIdloans(String idloans) {
        this.idloans = idloans;
    }

    public String getEmpl() {
        return empl;
    }

    public void setEmpl(String empl) {
        this.empl = empl;
    }

    public String getDate_e() {
        return date_e;
    }

    public void setDate_b(String date_b) {
        this.date_b = date_b;
    }

    public String getDate_b() {
        return date_b;
    }

    public void setDate_e(String date_e) {
        this.date_e = date_e;
    }

    public String getLoan() {
        return loan;
    }

    public void setLoan(String loan) {
        this.loan = loan;
    }


    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
