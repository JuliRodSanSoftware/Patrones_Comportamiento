package co.edu.unicauca.openmarket.presentation.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OMInvokerTest {
    private OMInvoker invoker;
    private MockCommand mockCommand;

    @Test
    public void testExecuteCommand() {
        invoker = new OMInvoker();
        mockCommand = new MockCommand();
        invoker.addCommand(mockCommand);
        invoker.execute();

        Assertions.assertTrue(mockCommand.isMakeCalled());
        Assertions.assertFalse(mockCommand.isUnmakeCalled());
        Assertions.assertTrue(invoker.hasUndoStack());
        Assertions.assertTrue(invoker.hasRedoStack());
    }

    @Test
    public void testUndoCommand() {
        invoker = new OMInvoker();
        mockCommand = new MockCommand();
        invoker.addCommand(mockCommand);
        invoker.execute();
        invoker.unexecute();

        Assertions.assertTrue(mockCommand.isUnmakeCalled());
        Assertions.assertFalse(invoker.hasRedoStack());
        Assertions.assertFalse(invoker.hasUndoStack());
    }

    @Test
    public void testRedoCommand() {
        invoker = new OMInvoker();
        mockCommand = new MockCommand();
        invoker.addCommand(mockCommand);
        invoker.execute();
        invoker.unexecute();
        invoker.reexecute();

        Assertions.assertTrue(mockCommand.isMakeCalled());
        Assertions.assertTrue(mockCommand.isUnmakeCalled());
        Assertions.assertTrue(invoker.hasUndoStack());
        Assertions.assertTrue(invoker.hasRedoStack());
    }

    private static class MockCommand extends OMCommand {
        private boolean makeCalled;
        private boolean unmakeCalled;

        @Override
        public void make() {
            makeCalled = true;
        }

        @Override
        public void unmake() {
            unmakeCalled = true;
        }

        public boolean isMakeCalled() {
            return makeCalled;
        }

        public boolean isUnmakeCalled() {
            return unmakeCalled;
        }
    }
}