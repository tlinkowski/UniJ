/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2018-2019 Tomasz Linkowski.
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
package pl.tlinkowski.unij.service.collect.eclipse;

import java.util.Map;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.google.auto.service.AutoService;
import lombok.NonNull;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.factory.Maps;
import org.eclipse.collections.impl.tuple.ImmutableEntry;

import pl.tlinkowski.annotation.basic.NullOr;
import pl.tlinkowski.unij.service.api.UniJService;
import pl.tlinkowski.unij.service.api.collect.UnmodifiableMapFactory;

/**
 * Implementation of {@link UnmodifiableMapFactory} that returns Eclipse's {@link ImmutableMap}.
 *
 * @author Tomasz Linkowski
 */
@UniJService(priority = 30)
@AutoService(UnmodifiableMapFactory.class)
public final class EclipseUnmodifiableMapFactory implements UnmodifiableMapFactory {

  //region COLLECTOR
  @Override
  public <T, K, V> Collector<T, ?, Map<K, V>> collector(Function<? super T, ? extends K> keyMapper,
          Function<? super T, ? extends V> valueMapper) {
    return collector(keyMapper, valueMapper, (prevValue, value) -> {
      throw new IllegalArgumentException(String.format(
              "Duplicate key (attempted merging values %s and %s)", prevValue, value
      ));
    });
  }

  @Override
  public <T, K, V> Collector<T, ?, Map<K, V>> collector(Function<? super T, ? extends K> keyMapper,
          Function<? super T, ? extends V> valueMapper, BinaryOperator<V> mergeFunction) {
    return Collectors.collectingAndThen(
            Collectors.toMap(keyMapper, valueMapper, mergeFunction, Maps.mutable::empty),
            this::copyOf
    );
  }
  //endregion

  //region COPY OF
  @SuppressWarnings("unchecked")
  @Override
  public <K, V> Map<K, V> copyOf(Map<? extends K, ? extends V> map) {
    Map<K, V> copy = (Map<K, V>) Maps.immutable.ofAll(map).castToMap();
    copy.forEach((key, value) -> {
      Objects.requireNonNull(key, "key");
      Objects.requireNonNull(value, "value");
    });
    return copy;
  }
  //endregion

  //region ENTRIES
  @SafeVarargs
  @Override
  public final <K, V> Map<K, V> ofEntries(Map.Entry<? extends K, ? extends V>... entries) {
    Builder<K, V> builder = new Builder<>(entries.length);
    for (Map.Entry<? extends K, ? extends V> entry : entries) {
      builder.putWithNullCheckes(entry.getKey(), entry.getValue());
    }
    return builder.build();
  }

  @Override
  public <K, V> Map.Entry<K, V> entry(@NonNull K k, @NonNull V v) {
    return ImmutableEntry.of(k, v);
  }
  //endregion

  //region OF
  @Override
  public <K, V> Map<K, V> of() {
    return Maps.immutable.<K, V>of().castToMap();
  }

  @Override
  public <K, V> Map<K, V> of(@NonNull K k1, @NonNull V v1) {
    return Maps.immutable.of(k1, v1).castToMap();
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

  /**
   * A special {@link MutableMap}-based builder that throws for duplicate keys (Eclipse doesn't throw).
   * <p>
   * Throws {@link IllegalArgumentException} on duplicate elements.
   */
  private static class Builder<K, V> {

    private final MutableMap<K, V> map;

    Builder(int size) {
      this.map = Maps.mutable.ofInitialCapacity(size);
    }

    Builder<K, V> putWithNullCheckes(@NonNull K key, @NonNull V value) {
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
      return map.toImmutable().castToMap();
    }
  }
}
