package com.sruthi.thiranx.features.alert;

import com.sruthi.thiranx.data.dto.Alert;
import com.sruthi.thiranx.data.repository.ThiranXDB;

import java.util.List;

class AlertModel {

    private final AlertView alertView;

    AlertModel(AlertView alertView) {
        this.alertView = alertView;
    }

    List<Alert> getNotifications(Long employeeId) {
        return ThiranXDB.getInstance().getNotificationsFor(employeeId);
    }

    int markAllRead(Long employeeId) {
        return ThiranXDB.getInstance().markNotificationsRead(employeeId);
    }
}
