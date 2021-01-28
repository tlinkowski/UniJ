/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2018-2021 Tomasz Linkowski.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.tlinkowski.unij.service.collect.jdk8;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.google.auto.service.AutoService;
import lombok.NonNull;

import pl.tlinkowski.annotation.basic.NullOr;
import pl.tlinkowski.unij.service.api.UniJService;
import pl.tlinkowski.unij.service.api.collect.UnmodifiableMapFactory;

/**
 * Implementation of {@link UnmodifiableMapFactory} following the JDK 11
 * <a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Map.html#unmodifiable">unmodifiable
 * maps</a> specification (e.g. no {@code null} keys nor values allowed, throws {@link IllegalArgumentException} on
 * duplicate keys).
 * <p>
 * This implementation returns either a {@link HashMap} wrapped by {@link Collections#unmodifiableMap} or a {@link
 * Collections#singletonMap}.
 *
 * @author Tomasz Linkowski
 * @implNote To preserve good JVM behavior of this class (no <a href="https://shipilev.net/jvm/anatomy-quarks/16-megamorphic-virtual-calls/">megamorphic
 * calls</a>, only two implementations classes are ever returned from all of the methods. It aligns with JDK 9/10, which
 * returns only {@code MapN} or {@code Map1} implementation classes.
 */
@UniJService(priority = 40)
@AutoService(UnmodifiableMapFactory.class)
public final class Jdk8UnmodifiableMapFactory implements UnmodifiableMapFactory {

  private static final Map<Object, Object> EMPTY_MAP = new Builder<>(0).build();

  //region COLLECTOR

  /**
   * Based on {@link Collectors#toUnmodifiableMap(Function, Function)}.
   */
  @Override
  public <T, K, V> Collector<T, ?, Map<K, V>> collector(Function<? super T, ? extends K> keyMapper,
          Function<? super T, ? extends V> valueMapper) {
    return Collectors.collectingAndThen(Collectors.<T, K, V>toMap(keyMapper, valueMapper), this::ofTrustedHashMap);
  }

  /**
   * Based on {@link Collectors#toUnmodifiableMap(Function, Function, BinaryOperator)}.
   */
  @Override
  public <T, K, V> Collector<T, ?, Map<K, V>> collector(Function<? super T, ? extends K> keyMapper,
          Function<? super T, ? extends V> valueMapper, BinaryOperator<V> mergeFunction) {
    return Collectors.collectingAndThen(
            Collectors.<T, K, V>toMap(keyMapper, valueMapper, mergeFunction), this::ofTrustedHashMap
    );
  }
  //endregion

  //region COPY OF

  /**
   * @implNote Copies {@param map} even if it already is unmodifiable (no way to verify this).
   */
  @Override
  public <K, V> Map<K, V> copyOf(Map<? extends K, ? extends V> map) {
    return ofTrustedHashMap(new HashMap<>(map));
  }
  //endregion

  //region ENTRIES

  /**
   * Based on {@link Map#ofEntries(Map.Entry[])}.
   */
  @SafeVarargs
  @Override
  public final <K, V> Map<K, V> ofEntries(Map.Entry<? extends K, ? extends V>... entries) {
    switch (entries.length) {
      case 0:
        return of();
      case 1:
        Map.Entry<? extends K, ? extends V> entry = entries[0];
        return of(entry.getKey(), entry.getValue());
      default:
        return ofAtLeastTwoEntries(entries);
    }
  }

  /**
   * Based on {@link Map#entry}.
   */
  @Override
  public <K, V> Map.Entry<K, V> entry(@NonNull K k, @NonNull V v) {
    return new AbstractMap.SimpleImmutableEntry<>(k, v);
  }
  //endregion

  //region OF
  @SuppressWarnings("unchecked")
  @Override
  public <K, V> Map<K, V> of() {
    return (Map<K, V>) EMPTY_MAP;
  }

  @Override
  public <K, V> Map<K, V> of(@NonNull K k1, @NonNull V v1) {
    return Collections.singletonMap(k1, v1);
  }

  @Override
  public <K, V> Map<K, V> of(@NonNull K k1, @NonNull V v1, @NonNull K k2, @NonNull V v2) {
    return new Builder<K, V>(2)
            .put(k1, v1)
            .put(k2, v2)
            .build();
  }

  @Override
  public <K, V> Map<K, V> of(@NonNull K k1, @NonNull V v1, @NonNull K k2, @NonNull V v2, @NonNull K k3, @NonNull V v3) {
    return new Builder<K, V>(3)
            .put(k1, v1)
            .put(k2, v2)
            .put(k3, v3)
            .build();
  }

  @Override
  public <K, V> Map<K, V> of(@NonNull K k1, @NonNull V v1, @NonNull K k2, @NonNull V v2, @NonNull K k3, @NonNull V v3,
          @NonNull K k4, @NonNull V v4) {
    return new Builder<K, V>(4)
            .put(k1, v1)
            .put(k2, v2)
            .put(k3, v3)
            .put(k4, v4)
            .build();
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <K, V> Map<K, V> of(@NonNull K k1, @NonNull V v1, @NonNull K k2, @NonNull V v2, @NonNull K k3, @NonNull V v3,
          @NonNull K k4, @NonNull V v4, @NonNull K k5, @NonNull V v5) {
    return new Builder<K, V>(5)
            .put(k1, v1)
            .put(k2, v2)
            .put(k3, v3)
            .put(k4, v4)
            .put(k5, v5)
            .build();
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <K, V> Map<K, V> of(@NonNull K k1, @NonNull V v1, @NonNull K k2, @NonNull V v2, @NonNull K k3, @NonNull V v3,
          @NonNull K k4, @NonNull V v4, @NonNull K k5, @NonNull V v5, @NonNull K k6, @NonNull V v6) {
    return new Builder<K, V>(6)
            .put(k1, v1)
            .put(k2, v2)
            .put(k3, v3)
            .put(k4, v4)
            .put(k5, v5)
            .put(k6, v6)
            .build();
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <K, V> Map<K, V> of(@NonNull K k1, @NonNull V v1, @NonNull K k2, @NonNull V v2, @NonNull K k3, @NonNull V v3,
          @NonNull K k4, @NonNull V v4, @NonNull K k5, @NonNull V v5, @NonNull K k6, @NonNull V v6, @NonNull K k7,
          @NonNull V v7) {
    return new Builder<K, V>(7)
            .put(k1, v1)
            .put(k2, v2)
            .put(k3, v3)
            .put(k4, v4)
            .put(k5, v5)
            .put(k6, v6)
            .put(k7, v7)
            .build();
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <K, V> Map<K, V> of(@NonNull K k1, @NonNull V v1, @NonNull K k2, @NonNull V v2, @NonNull K k3, @NonNull V v3,
          @NonNull K k4, @NonNull V v4, @NonNull K k5, @NonNull V v5, @NonNull K k6, @NonNull V v6, @NonNull K k7,
          @NonNull V v7, @NonNull K k8, @NonNull V v8) {
    return new Builder<K, V>(8)
            .put(k1, v1)
            .put(k2, v2)
            .put(k3, v3)
            .put(k4, v4)
            .put(k5, v5)
            .put(k6, v6)
            .put(k7, v7)
            .put(k8, v8)
            .build();
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <K, V> Map<K, V> of(@NonNull K k1, @NonNull V v1, @NonNull K k2, @NonNull V v2, @NonNull K k3, @NonNull V v3,
          @NonNull K k4, @NonNull V v4, @NonNull K k5, @NonNull V v5, @NonNull K k6, @NonNull V v6, @NonNull K k7,
          @NonNull V v7, @NonNull K k8, @NonNull V v8, @NonNull K k9, @NonNull V v9) {
    return new Builder<K, V>(9)
            .put(k1, v1)
            .put(k2, v2)
            .put(k3, v3)
            .put(k4, v4)
            .put(k5, v5)
            .put(k6, v6)
            .put(k7, v7)
            .put(k8, v8)
            .put(k9, v9)
            .build();
  }

  @SuppressWarnings("PMD.ExcessiveParameterList")
  @Override
  public <K, V> Map<K, V> of(@NonNull K k1, @NonNull V v1, @NonNull K k2, @NonNull V v2, @NonNull K k3, @NonNull V v3,
          @NonNull K k4, @NonNull V v4, @NonNull K k5, @NonNull V v5, @NonNull K k6, @NonNull V v6, @NonNull K k7,
          @NonNull V v7, @NonNull K k8, @NonNull V v8, @NonNull K k9, @NonNull V v9, @NonNull K k10, @NonNull V v10) {
    return new Builder<K, V>(10)
            .put(k1, v1)
            .put(k2, v2)
            .put(k3, v3)
            .put(k4, v4)
            .put(k5, v5)
            .put(k6, v6)
            .put(k7, v7)
            .put(k8, v8)
            .put(k9, v9)
            .put(k10, v10)
            .build();
  }
  //endregion

  private <K, V> Map<K, V> ofTrustedHashMap(Map<K, V> map) {
    switch (map.size()) {
      case 0:
        return of();
      case 1:
        Map.Entry<K, V> entry = map.entrySet().iterator().next();
        return of(entry.getKey(), entry.getValue());
      default:
        map.forEach((key, value) -> {
          Objects.requireNonNull(key, "key");
          Objects.requireNonNull(value, "value");
        });
        return Collections.unmodifiableMap((HashMap<K, V>) map);
    }
  }

  private <K, V> Map<K, V> ofAtLeastTwoEntries(Map.Entry<? extends K, ? extends V>[] entries) {
    Builder<K, V> builder = new Builder<>(entries.length);
    for (Map.Entry<? extends K, ? extends V> entry : entries) {
      builder.putWithNullChecks(entry.getKey(), entry.getValue());
    }
    return builder.build();
  }

  /**
   * Based on {@code java.util.ImmutableCollections.MapN} constructor.
   * <p>
   * Throws {@link IllegalArgumentException} on duplicate elements.
   */
  private static class Builder<K, V> {

    private final Map<K, V> map;

    Builder(int size) {
      this.map = new HashMap<>((int) (size / 0.75));
    }

    Builder<K, V> putWithNullChecks(@NonNull K key, @NonNull V value) {
      return put(key, value);
    }

    Builder<K, V> put(K key, V value) {
      @NullOr V prevValue = map.put(key, value);
      if (prevValue != null) {
        throw new IllegalArgumentException(String.format(
                "Duplicate key: %s (attempted merging values %s and %s)", key, prevValue, value
        ));
      }
      return this;
    }

    Map<K, V> build() {
      return Collections.unmodifiableMap(map);
    }
  }
}
