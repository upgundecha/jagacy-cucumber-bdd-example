package com.example.screens;

import com.example.Fields.EntryField;
import com.example.session.Session;
import com.example.Fields.LabelField;
import com.jagacy.Key;
import com.jagacy.util.JagacyException;

/**
 * Created by upgundecha on 14/10/16.
 */
public class PhonbookMenuScreen {

    private Session session;
    private String screenCrc = "0x3acc869";

    // Screen fields
    private LabelField waitForLabel =
            new LabelField(1, 27, "TAMU  System  Directory");
    private EntryField actionEntryField = new EntryField(11, 33);

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
        session.setFieldValue(actionEntryField, "F");
        session.writeKey(Key.ENTER);
        session.waitForChange(10000);
        return new PhonbookSearchScreen(session);
    }
}
