package sunday.resi.util;

/**
 * A console that prints messages to system out.
 * 
 * @author Peter H&auml;nsgen
 */
public class SystemConsole implements Console
{
    @Override
    public void println(String line)
    {
        System.out.println(line);
    }

    @Override
    public void clear()
    {
    }
}
