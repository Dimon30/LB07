package Commands;

import java.io.Serial;

public class TestCommand extends Command{
    @Serial
    private static final long serialVersionUID = 14130070678758251L;
    private static final String name = "testCommand";
    private final String description = "description";
    public TestCommand getTestCommand(){
        TestCommand t = new TestCommand();
        return t;
    }

    public static String getName(){return name;}
}
