package com.example.screens;

import com.example.Fields.Field;
import com.example.session.Session;
import com.example.Fields.TextLabel;
import com.jagacy.Key;
import com.jagacy.util.JagacyException;

/**
 * Created by upgundecha on 14/10/16.
 */
public class PhonbookMenuScreen {

    private Session session;
    private String screenCrc = "0x3acc869";

    // Screen fields
    private TextLabel waitForLabel =
            new TextLabel(1, 27, "TAMU  System  Directory");
    private Field actionField = new Field(11, 33);

    public PhonbookMenuScreen(final Session s) throws JagacyException {
        this.session = s;
        if (!session.waitForTextLabel(waitForLabel)) {
            throw new IllegalStateException("Not Phonbook Main Menu screen!");
        }

        if (session.getCrc32() != Long.decode(screenCrc)) {
            throw new IllegalStateException("Phonebook Menu "
                    + "Screen has been changed!");
        }
    }

    /**
     * Open Phonbook Search screen by calling the action.
     * @return Phonbook Search screen
     * @throws JagacyException Jagacy Exception
     */
    public final PhonbookSearchScreen openFacultyStaffListing()
            throws JagacyException {
        session.setFieldValue(actionField, "F");
        session.writeKey(Key.ENTER);
        session.waitForChange(10000);
        return new PhonbookSearchScreen(session);
    }
}
