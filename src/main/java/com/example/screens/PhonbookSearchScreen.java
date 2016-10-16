package com.example.screens;

import com.example.Fields.Field;
import com.example.session.Session;
import com.example.Fields.TextLabel;
import com.jagacy.Key;
import com.jagacy.util.JagacyException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by upgundecha on 15/10/16.
 */
public class PhonbookSearchScreen {

    private Session session;
    private String screenCrc = "0x4b7c5511";

    // Screen fields
    private TextLabel waitForLabel = new TextLabel(1, 15, "Faculty/Staff");
    private Field firstOrMiddleNameField = new Field(19, 47);
    private Field lastNameField = new Field(19, 6);

    public PhonbookSearchScreen(final Session s) throws JagacyException {
        this.session = s;
        if (!session.waitForTextLabel(waitForLabel)) {
            throw new IllegalStateException("Not Phonbook "
                    + "Search screen!");
        }

        if (session.getCrc32() != Long.decode(screenCrc)) {
            throw new IllegalStateException("Phonebook Search"
                    + " screen has been changed!");
        }
    }

    /**
     * Search the directory using last name.
     * @param lastName last name
     * @throws JagacyException JagacyException
     */
    public final void searchByLastname(final String lastName)
            throws JagacyException {
        session.setFieldValue(lastNameField, lastName);
        session.writeKey(Key.ENTER);
        session.waitForChange(10000);
    }

    /**
     * Search the directory using First or Middle name.
     * @param firstOrMiddleName first or middle name
     * @throws JagacyException JagacyException
     */
    public final void searchByFirstOrMiddleName(final String firstOrMiddleName)
            throws JagacyException {
        session.setFieldValue(firstOrMiddleNameField, firstOrMiddleName);
        session.writeKey(Key.ENTER);
        session.waitForChange(10000);
    }

    /**
     * Gets displayed rows into a list of string.
     * @return recounds found
     * @throws JagacyException JagacyException
     */
    public final List<String> getResults() throws JagacyException {
        List<String> results = new ArrayList<>();
        for (int row = 5; row <= 15; row++) {
            if (!session.readPosition(row, 2, 1).trim().equals("")) {
                results.add(session.readPosition(row, 2, 75).trim());
            }
        }
        return results;
    }
}
