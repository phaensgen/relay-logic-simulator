package sunday.resi.util.console;

import java.awt.TextArea;

/**
 * This is a console implementation which prints all output to a text area component.
 * 
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
