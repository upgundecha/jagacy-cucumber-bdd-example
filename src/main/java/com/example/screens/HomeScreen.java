package com.example.screens;

import com.example.fields.EntryField;
import com.example.session.Session;
import com.example.fields.LabelField;
import com.jagacy.Key;
import com.jagacy.util.JagacyException;

/**
 * Created by upgundecha on 14/10/16.
 */
public class HomeScreen {

    private Session session;
    private String screenCrc = "0xb0c10358";

    // Screen fields
    private LabelField waitForLabel =
            new LabelField(17, 6, "TEXAS A & M UNIVERSITY");
    private EntryField applicationEntryField = new EntryField(23, 1);

    public HomeScreen(final Session s) throws JagacyException {
        this.session = s;
        if (!session.waitForTextLabel(waitForLabel)) {
            throw new IllegalStateException("Not Home screen!");
        }

        if (session.getCrc32() != Long.decode(screenCrc)) {
            throw new IllegalStateException("Home Screen has been changed!");
        }
    }

    /**
     * Open Phonbook Menu screen.
     * @return Phonbook Menu Screen
     * @throws JagacyException JagacyException
     */
    public final PhonbookMenuScreen openPhonbook() throws JagacyException {
        session.setEntryFieldValue(applicationEntryField, "PHONBOOK");
        session.writeKey(Key.ENTER);
        session.waitForChange(session.DEFAULT_TIMEOUT);
        return new PhonbookMenuScreen(session);
    }
}
