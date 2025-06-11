package org.example.super_demo.script;

import org.example.super_demo.script.exception.ScriptCompileException;
import org.example.super_demo.script.utils.LogUtil;

import javax.script.*;
import java.util.Map;

public final class ScriptInvoker {
    
    private ScriptInvoker() {
        // 私有构造函数，防止实例化
    }

    /**
     * 执行脚本并返回结果
     *
     * @param script 编译后的脚本
     * @param params 脚本参数
     * @return 脚本执行结果
     * @throws ScriptException 如果脚本执行出错
     */
    public static Object invoke(CompiledScript script, Map<String, Object> params) throws ScriptException {
        try {
            ScriptContext context = new SimpleScriptContext();
            Bindings bindings = context.getBindings(ScriptContext.ENGINE_SCOPE);
            
            // 设置脚本参数
            if (params != null) {
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    bindings.put(entry.getKey(), entry.getValue());
                }
            }
            
            return script.eval(bindings);
        } catch (ScriptException e) {
            LogUtil.error("脚本执行失败: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * 编译并执行脚本
     *
     * @param expression 脚本表达式
     * @param params 脚本参数
     * @return 脚本执行结果
     * @throws ScriptCompileException 如果脚本编译失败
     * @throws ScriptException 如果脚本执行失败
     */
    public static Object compileAndInvoke(String expression, Map<String, Object> params) 
            throws ScriptCompileException, ScriptException {
        CompiledScript script = ScriptEngineUtil.compile(expression);
        return invoke(script, params);
    }
} 