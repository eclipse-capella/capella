package org.polarsys.capella.core.data.helpers.cache;

import java.util.function.Function;

@FunctionalInterface
public interface CachedFunction<T, R> extends Function<T, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    R withoutCache(T t);
    
    @Override
    default R apply(T t) {
      return ModelCache.getCache(this, t);
    }
    
    default R get(T t) {
      return apply(t);
    }
    
    default void clearCache() {
       ModelCache.clearCache(this);
    }
    
}
