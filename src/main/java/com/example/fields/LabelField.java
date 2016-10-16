package com.example.fields;

/**
 * Created by upgundecha on 16/10/16.
 */
public class LabelField {

    private int row;
    private int column;
    private String text;

    public LabelField(final int rowNum, final int columnNum,
                      final String labelText) {
        this.row = rowNum;
        this.column = columnNum;
        this.text = labelText;
    }

    /**
     * Get row number of the label.
     * @return row number
     */
    public final int getRow() {
        return row;
    }

    /**
     * Get column number of the label.
     * @return column number
     */
    public final int getColumn() {
        return column;
    }

    /**
     * Get label text.
     * @return label text
     */
    public final String getText() {
        return text;
    }
}
