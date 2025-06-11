package org.example.super_demo.script;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import org.example.super_demo.script.exception.ScriptCompileException;
import org.example.super_demo.script.utils.LogUtil;
import javax.script.*;

public final class ScriptEngineUtil {
    private static NashornScriptEngineFactory SCRIPT_ENGINE_FACTORY = null;
    private static final String[] GLOBAL_CONFIG = new String[]{"-doe", "--global-per-engine"};

    static {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        for (ScriptEngineFactory f : scriptEngineManager.getEngineFactories()) {
            if (f.getEngineName().equalsIgnoreCase("Oracle Nashorn")) {
                SCRIPT_ENGINE_FACTORY = (NashornScriptEngineFactory) f;
                break;
            }
        }
        assert SCRIPT_ENGINE_FACTORY != null;
    }

    private ScriptEngineUtil() {
    }

    /**
     * 编译脚本
     */
    public static CompiledScript compile(String expression) throws ScriptCompileException {
        try {
            ScriptEngine SCRIPT_ENGINE = SCRIPT_ENGINE_FACTORY.getScriptEngine(GLOBAL_CONFIG);
            assert SCRIPT_ENGINE != null;
            return ((Compilable) SCRIPT_ENGINE).compile(expression);
        } catch (ScriptException e) {
            throw new ScriptCompileException("脚本编译失败", e.getMessage(), e);
        }
    }

    /**
     * 构建包含 Java 工具类的脚本环境
     */
    public static CompiledScript buildWithJavaUtils(String expression) throws ScriptCompileException {
        try {
            String randomFunName = "script_" + System.currentTimeMillis();
            String code = String.format(
                "function %s() {\n" +
                "    var CastUtil = Java.type('org.example.super_demo.script.utils.CastUtil');\n" +
                "    var LogUtil = Java.type('org.example.super_demo.script.utils.LogUtil');\n" +
                "    %s\n" +
                "}\n" +
                "%s();\n",
                randomFunName, expression, randomFunName
            );
            
            LogUtil.info("生成的脚本代码: \n%s", code);
            return compile(code);
        } catch (Exception e) {
            throw new ScriptCompileException("构建脚本环境失败", e.getMessage(), e);
        }
    }
}
