package org.example.super_demo.script;

import org.example.super_demo.script.exception.ScriptCompileException;
import org.example.super_demo.script.utils.LogUtil;

import javax.script.CompiledScript;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.Map;

public class ScriptDemo {
    
    public static void main(String[] args) {
        try {
            // 示例1：简单的数学计算
            String mathScript = "x + y * 2";
            Map<String, Object> mathParams = new HashMap<>();
            mathParams.put("x", 10);
            mathParams.put("y", 5);
            
//            Object mathResult = ScriptInvoker.compileAndInvoke(mathScript, mathParams);
//            LogUtil.info("数学计算示例 - 表达式: %s, 参数: %s, 结果: %s",
//                    mathScript, mathParams, mathResult);

            // 示例2：使用 Java 工具类
            String javaUtilsScript = 
                "var result = x + y;\n" +
                "LogUtil.info('内部-计算结果: %s', result);\n" +
                "return result;";
            
            CompiledScript compiledScript = ScriptEngineUtil.buildWithJavaUtils(javaUtilsScript);
            Object javaUtilsResult = ScriptInvoker.invoke(compiledScript, mathParams);
//            LogUtil.info("Java工具类示例 - 表达式: %s, 参数: %s, 结果: %s",
//                    javaUtilsScript, mathParams, javaUtilsResult);

//            // 示例2：使用函数
//            String functionScript =
//                "function calculate(a, b) { " +
//                "    return a * b + 10; " +
//                "} " +
//                "calculate(x, y);";
//
//            Object functionResult = ScriptInvoker.compileAndInvoke(functionScript, mathParams);
//            LogUtil.info("函数计算示例 - 表达式: %s, 参数: %s, 结果: %s",
//                    functionScript, mathParams, functionResult);

//            // 示例3：使用类型转换
//            CompiledScript compiledScript = ScriptEngineUtil.compile("x * y");
//            Integer typedResult = ScriptInvoker.invokeWithType(compiledScript, mathParams, Integer.class);
//            LogUtil.info("类型转换示例 - 表达式: %s, 参数: %s, 结果: %s",
//                    "x * y", mathParams, typedResult);
//
//            // 示例4：使用字符串操作
//            String stringScript = "name + ' 的年龄是 ' + age + ' 岁'";
//            Map<String, Object> stringParams = new HashMap<>();
//            stringParams.put("name", "张三");
//            stringParams.put("age", 25);
//
//            Object stringResult = ScriptInvoker.compileAndInvoke(stringScript, stringParams);
//            LogUtil.info("字符串操作示例 - 表达式: %s, 参数: %s, 结果: %s",
//                    stringScript, stringParams, stringResult);

        } catch (ScriptCompileException e) {
            LogUtil.error("脚本编译失败: %s", e.getMessage());
        } catch (ScriptException e) {
            LogUtil.error("脚本执行失败: %s", e.getMessage());
        }
    }
} 