/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.openmarket.presentation.commands;

import java.util.Stack;

/**
 *
 * @author ahurtado
 */
public class OMInvoker {
    
    private Stack<OMCommand> undoStack;
    private Stack<OMCommand> redoStack;
    
    private OMCommand currentCommand;
    
    
    
    public OMInvoker(){
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        currentCommand=null;
    }
    
    public void addCommand(OMCommand actualCommand){
        currentCommand = actualCommand;
        
    }
    
    public void execute(){
        this.addCommand(currentCommand);
        currentCommand.make();
        undoStack.push(currentCommand);
    }
    
    public void unexecute(){
        if (!undoStack.isEmpty()){
            OMCommand command = undoStack.pop();
            command.unmake();
            redoStack.push(command);
           
        }
        
    }
    
    public void reexecute(){
        if (!redoStack.isEmpty()) {
            OMCommand command = redoStack.pop();
            command.make();
            undoStack.push(command);
        }
    }
    
    public boolean hasUndoStack(){
        return !undoStack.isEmpty();
    }
    
        public boolean hasRedoStack(){
        return !undoStack.isEmpty();
    }
    
    
}
