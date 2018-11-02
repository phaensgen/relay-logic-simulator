package sunday.resi.util.awt;

import java.awt.TextArea;

import sunday.resi.util.Console;

/**
 * @author Peter H&auml;nsgen
 */
public class TextAreaConsole extends TextArea implements Console
{
    private static final long serialVersionUID = 1L;

    /**
     * Prints a line of text to the console text area.
     */
    @Override
    public void println(String line)
    {
        String text = getText();
        text += line + '\n';
        setText(text);
    }

    @Override
    public void clear()
    {
        setText("");
    }
}
