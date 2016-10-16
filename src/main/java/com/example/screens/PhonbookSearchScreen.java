package com.example.screens;

import com.example.fields.EntryField;
import com.example.session.Session;
import com.example.fields.LabelField;
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
    private LabelField waitForLabel = new LabelField(1, 15, "Faculty/Staff");
    private EntryField firstOrMiddleNameEntryField = new EntryField(19, 47);
    private EntryField lastNameEntryField = new EntryField(19, 6);

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
        session.setEntryFieldValue(lastNameEntryField, lastName);
        session.writeKey(Key.ENTER);
        session.waitForChange(session.DEFAULT_TIMEOUT);
    }

    /**
     * Search the directory using First or Middle name.
     * @param firstOrMiddleName first or middle name
     * @throws JagacyException JagacyException
     */
    public final void searchByFirstOrMiddleName(final String firstOrMiddleName)
            throws JagacyException {
        session.setEntryFieldValue(firstOrMiddleNameEntryField, firstOrMiddleName);
        session.writeKey(Key.ENTER);
        session.waitForChange(session.DEFAULT_TIMEOUT);
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
