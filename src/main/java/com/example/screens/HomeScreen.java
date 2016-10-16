package com.example.screens;

import com.example.Fields.Field;
import com.example.session.Session;
import com.example.Fields.TextLabel;
import com.jagacy.Key;
import com.jagacy.util.JagacyException;

/**
 * Created by upgundecha on 14/10/16.
 */
public class HomeScreen {

    private Session session;
    private String screenCrc = "0xb0c10358";

    // Screen fields
    private TextLabel waitForLabel =
            new TextLabel(17, 6, "TEXAS A & M UNIVERSITY");
    private Field applicationEntryField = new Field(23, 1);

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
        session.setFieldValue(applicationEntryField, "PHONBOOK");
        session.writeKey(Key.ENTER);
        session.waitForChange(10000);
        return new PhonbookMenuScreen(session);
    }
}
