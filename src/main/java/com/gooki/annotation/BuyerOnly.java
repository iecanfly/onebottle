package com.gooki.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Methods annotated with this can only be accessed by buyers
 *
 * Created by iecanfly on 5/14/14.
 */
@Retention(value = RetentionPolicy.RUNTIME)
public @interface BuyerOnly {
}
