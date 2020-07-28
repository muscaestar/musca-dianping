package xyz.muscaestar.muscadianping.common;

import org.springframework.validation.BindingResult;

/**
 * Created by muscaestar on 7/28/20
 *
 * @author muscaestar
 */
public class CommonUtil {
    public static String processErrString(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        bindingResult.getFieldErrors()
                .forEach(b -> sb.append(b.getDefaultMessage()).append(","));
        return sb.substring(0, sb.length() - 1);
    }
}
