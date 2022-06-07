import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

@Slf4j
public class LoggingHandler implements InvocationHandler {
    private final Object target;


    //Всички логове може да се намерят в хашмапа calls ######

    public Map<String, List<String>> calls = new HashMap<>();

    //#########


    public LoggingHandler(Object target) {
        this.target = target;
    }

    // инвоке метод
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        Class<?>[] methodParameterTypes = method.getParameterTypes();
        Class<?> methodReturnType = method.getReturnType();
        Class<?>[] methodExceptionTypes = method.getExceptionTypes();
        int methodModifiers = method.getModifiers();


        if (methodName.contains("toString")){
            return "####" + calls.toString();
        }
        //използване на лог инфо за изписване на конзолата


        log.info("--------> " + "Invoked method: {} | {} | {}", methodName, methodParameterTypes, methodReturnType + " <--------");

        // със лог.дебъг с допълнителна иформация от методите за хвърллящ exception и т.н
        log.debug("--------> " + "Invoked method: {} | {} | {} | {} | {}", methodName, methodParameterTypes, methodReturnType, Arrays.toString(methodExceptionTypes), methodModifiers + " <--------");

// Вписвам всички извиквания за методи в мап по име на метод.
        calls.put(methodName, new ArrayList<>());
        calls.get(methodName).add(Arrays.toString(methodParameterTypes));
        calls.get(methodName).add(String.valueOf(methodReturnType));

        return method.invoke(target, args);
    }



    //    метода за  прохи GENERIC за да се използва лесно
    @SuppressWarnings("unchecked")
    public static <T> T withLogging(T target, Class<T> interfacee) {
        return (T) Proxy.newProxyInstance(
                interfacee.getClassLoader(),
                new Class<?>[]{interfacee},
                new LoggingHandler(target)
        );
    }



}