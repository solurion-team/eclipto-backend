package com.solurion.eclipto.common.annotation;

import com.solurion.eclipto.common.config.ResourceServerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ResourceServerConfig.class})
public @interface EcliptoResourceServer {
}
