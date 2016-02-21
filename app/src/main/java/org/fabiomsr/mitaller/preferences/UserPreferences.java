package org.fabiomsr.mitaller.preferences;

public interface UserPreferences {

    int getLastReceiptNumber();

    int getLastRepairOrderNumber();

    void incrementReceiptNumber();

    void incrementRepairOrderNumber();

}
