package com.minotor.desktop_minotor.model;

import java.time.LocalDateTime;

public class PageVisit {
    private String pageName;
    private LocalDateTime visitDate;

    public PageVisit() {
    }

    public PageVisit(String pageName, LocalDateTime visitDate) {
        this.pageName = pageName;
        this.visitDate = visitDate;
    }

    public String getPageName() {
        return this.pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public LocalDateTime getVisitDate() {
        return this.visitDate;
    }

    public void setVisitDate(LocalDateTime visitDate) {
        this.visitDate = visitDate;
    }
}
