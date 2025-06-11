package org.example.super_demo.script.exception;

public class ScriptCompileException extends RuntimeException {
    
    public ScriptCompileException(String message) {
        super(message);
    }

    public ScriptCompileException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScriptCompileException(String message, String detail, Throwable cause) {
        super(String.format("%s: %s", message, detail), cause);
    }
} 