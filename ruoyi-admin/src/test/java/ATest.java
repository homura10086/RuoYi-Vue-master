import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.entity.SysUser;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ATest {

    @Test
    void name() throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        SysUser sysUser = new SysUser();
        Class<? extends SysUser> claszz1 = sysUser.getClass();

        Class<SysUser> clazz = SysUser.class;
        System.out.println(clazz == claszz1);

        Field field = clazz.getDeclaredField("userId");
        field.setAccessible(true);
        field.set(sysUser, 1L);
        System.out.println(field.get(sysUser));

//        注解只有生命周期为运行时才能获取
        Excel excel = field.getAnnotation(Excel.class);
        System.out.println(excel.name());

        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors){
            System.out.println(constructor);
            if(constructor.getParameterCount() == 0){
                Object object = constructor.newInstance();
                SysUser sysUser1 = (SysUser) object;
                System.out.println(sysUser1);
            }
            else {
                Object o = constructor.newInstance(22222L);
                SysUser s = (SysUser) o;
                System.out.println(s);
            }
        }

        Method method = clazz.getDeclaredMethod("getUserId");
        Object o = method.invoke(sysUser);
        Long l = (Long) o;
        System.out.println(l);
    }
}